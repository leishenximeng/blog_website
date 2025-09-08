<template>
	<div class="page-container">
		<h1>博客列表</h1>
		<ul>
			<li v-for="post in posts" :key="post.id">
				<router-link :to="{ name: 'BlogDetail', params: { id: post.id } }">{{ post.title }}</router-link>
				<p>{{ post.summary }}</p>
			</li>
		</ul>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const posts = ref([])

onMounted(async () => {
	try {
		const res = await axios.get('/api/posts') // ✅ 全局 baseURL
		posts.value = res.data.data
	} catch (e) {
		console.error('获取博客列表失败：', e)
	}
})
</script>

<style scoped>
.page-container {
	max-width: 800px;
	margin: 2rem auto;
}

ul {
	list-style: none;
	padding: 0;
}

li {
	margin-bottom: 1.5rem;
	border-bottom: 1px solid #ddd;
	padding-bottom: 1rem;
}

p {
	color: #555;
}
</style>