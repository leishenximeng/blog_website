<template>
	<div class="page-container">
		<h1>欢迎来到我的主页</h1>
		<p class="description">一个基于 Vue + SpringBoot 的博客系统。</p>

		<div class="homepage-buttons">
			<button @click="goTo({ name: 'Blog' })" class="cool-button">📚 博客</button>
			<button @click="goTo({ name: 'Profile' })" class="cool-button">🧑 关于</button>
		</div>

		<p class="tip">点击下方任一文章卡片，查看博客详情。</p>

		<router-link :to="{ name: 'AddBlog' }">
			<button class="add-button">➕ 新建博客</button>
		</router-link>

		<div class="blog-list">
			<div class="blog-card" v-for="blog in blogs" :key="blog.id" @click="viewBlog(blog)">
				<h3>{{ blog.title }}</h3>
				<p>{{ blog.summary }}</p>
			</div>
		</div>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()
const blogs = ref([])

onMounted(async () => {
	try {
		const response = await axios.get('/api/posts') // ✅ 全局 baseURL
		blogs.value = response.data.data
	} catch (error) {
		console.error('加载博客失败:', error)
	}
})

function viewBlog(blog) {
	router.push({ name: 'BlogDetail', params: { id: blog.id } })
}

function goTo(routeObj) {
	router.push(routeObj)
}
</script>

<style scoped>
.blog-list {
	display: flex;
	flex-direction: column;
	gap: 1rem;
	margin-top: 2rem;
}

.blog-card {
	background: #fff;
	border: 1px solid #ddd;
	border-radius: 12px;
	padding: 1rem;
	cursor: pointer;
	transition: all 0.3s ease;
}

.blog-card:hover {
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	transform: translateY(-3px);
}

.add-button {
	margin: 1.5rem 0;
	padding: 0.6rem 1.2rem;
	font-size: 1rem;
	background-color: #28a745;
	color: white;
	border: none;
	border-radius: 8px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.add-button:hover {
	background-color: #218838;
}

.homepage-buttons {
	display: flex;
	justify-content: center;
	gap: 2rem;
	margin: 2rem 0;
}

.cool-button {
	padding: 0.8rem 2rem;
	font-size: 1.1rem;
	font-weight: bold;
	color: white;
	background: linear-gradient(135deg, #6a11cb, #2575fc);
	border: none;
	border-radius: 12px;
	cursor: pointer;
	box-shadow: 0 6px 16px rgba(0, 0, 0, 0.2);
	transition: all 0.3s ease;
}

.cool-button:hover {
	transform: translateY(-3px);
	box-shadow: 0 10px 24px rgba(0, 0, 0, 0.25);
	background: linear-gradient(135deg, #2575fc, #6a11cb);
}
</style>