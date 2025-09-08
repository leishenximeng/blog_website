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
	summary: '',
	content: '',
	createdAt: ''
})

const paragraphs = ref([])

onMounted(async () => {
	const id = route.params.id
	try {
		const res = await axios.get(`/api/posts/${id}`) // ✅ 全局 baseURL
		blog.value = res.data.data
		paragraphs.value = blog.value.content.split('\n').filter(line => line.trim())
	} catch (e) {
		blog.value = {
			title: '文章未找到',
			summary: '',
			content: '你请求的文章不存在。',
			createdAt: ''
		}
		paragraphs.value = ['你请求的文章不存在。']
	}
})

function goBack() {
	router.push({ name: 'Blog' })
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