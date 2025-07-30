<template>
	<div class="form-container">
	  <h2>添加博客文章</h2>
	  <form @submit.prevent="submitBlog">
		<label>标题：</label>
		<input v-model="blog.title" required />
  
		<label>内容：</label>
		<textarea v-model="blog.content" rows="10" required></textarea>
  
		<button type="submit">提交</button>
	  </form>
  
	  <p v-if="message">{{ message }}</p>
	</div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  import axios from 'axios'
  
  const blog = ref({
	title: '',
	content: ''
  })
  
  const message = ref('')
  
  async function submitBlog() {
	try {
	  const res = await axios.post('http://localhost:8082/api/posts', blog.value)
	  message.value = res.data
	  blog.value.title = ''
	  blog.value.content = ''
	} catch (e) {
	  message.value = '提交失败：' + (e.response?.data || e.message)
	}
  }
  </script>
  
  <style scoped>
  .form-container {
	max-width: 600px;
	margin: 2rem auto;
	padding: 1rem;
	border: 1px solid #ddd;
	border-radius: 8px;
  }
  label {
	display: block;
	margin-top: 1rem;
  }
  input, textarea {
	width: 100%;
	padding: 0.5rem;
	margin-top: 0.5rem;
  }
  button {
	margin-top: 1rem;
	padding: 0.6rem 1.2rem;
	background-color: #2575fc;
	color: white;
	border: none;
	border-radius: 8px;
  }
  button:hover {
	background-color: #1a5ed8;
  }
  </style>
  