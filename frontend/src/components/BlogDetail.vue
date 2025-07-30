<template>
	<div class="page-container">
	  <h1>{{ blog.title }}</h1>
	  <p class="description">发布时间：{{ blog.createdAt }}</p>
	  <div class="blog-content">
		<p v-for="(para, idx) in paragraphs" :key="idx">{{ para }}</p>
	  </div>
  
	  <button class="back-button" @click="goBack">返回博客列表</button>
	</div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue'
  import { useRoute, useRouter } from 'vue-router'
  import axios from 'axios'
  
  const route = useRoute()
  const router = useRouter()
  
  const blog = ref({
	title: '',
	createdAt: '',
	content: ''
  })
  
  // 将 content 分段
  const paragraphs = ref([])
  
  onMounted(async () => {
	const id = route.params.id
	try {
	  const res = await axios.get(`http://localhost:8081/api/posts/${id}`)
	  blog.value = res.data
	  // 假设内容用 \n 分段
	  paragraphs.value = res.data.content.split('\n').filter(line => line.trim())
	} catch (e) {
	  blog.value = {
		title: '文章未找到',
		createdAt: '',
		content: '你请求的文章不存在。'
	  }
	  paragraphs.value = ['你请求的文章不存在。']
	}
  })
  
  function goBack() {
	router.push('/blog')
  }
  </script>
  
  <style scoped>
  .blog-content {
	text-align: left;
	margin-top: 2rem;
	line-height: 1.8;
	color: #444;
  }
  
  .back-button {
	margin-top: 2rem;
	padding: 0.6rem 1.2rem;
	font-size: 1rem;
	background-color: #2575fc;
	color: white;
	border: none;
	border-radius: 8px;
	cursor: pointer;
	transition: background-color 0.3s ease;
  }
  
  .back-button:hover {
	background-color: #1a5ed8;
  }
  </style>
  