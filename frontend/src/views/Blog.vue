<template>
	<div class="page-container">
		<h1>博客列表</h1>

		<!-- 加载中状态 -->
		<p v-if="loading">加载中...</p>

		<!-- 错误提示 -->
		<p v-else-if="error">{{ error }}</p>

		<!-- 没有文章 -->
		<p v-else-if="!posts.length">暂无博客文章</p>

		<!-- 博客列表 -->
		<ul v-else>
			<li v-for="post in posts" :key="post.id">
				<router-link :to="{ name: 'BlogDetail', params: { id: post.id } }">
					{{ post.title }}
				</router-link>
				<p>{{ post.summary }}</p>
			</li>
		</ul>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import api from '../utils/axios'

const posts = ref([])
const loading = ref(true)
const error = ref('')

onMounted(async () => {
	try {
		const res = await api.get('/posts')
		// ✅ res 已经是 ApiResponse
		posts.value = res.data ?? []
	} catch (e) {
		console.error('获取博客列表失败：', e)
		error.value = '获取博客列表失败'
	} finally {
		loading.value = false
	}
})
</script>


<style scoped>
.page-container {
	max-width: 800px;
	margin: 2rem auto;
	background-color: rgba(255, 255, 255, 0.85);
	padding: 2rem;
	border-radius: 12px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	text-align: center;
}

h1,
p {
	text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.4);
	color: #333;
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
