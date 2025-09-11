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

		<!-- 加载中 -->
		<p v-if="loading">加载中...</p>
		<!-- 错误提示 -->
		<p v-else-if="error">{{ error }}</p>

		<!-- 博客列表 -->
		<div v-else class="blog-list">
			<div class="blog-card" v-for="blog in blogs" :key="blog.id">
				<div class="blog-content" @click="viewBlog(blog)">
					<h3>{{ blog.title }}</h3>
					<p>{{ blog.summary }}</p>
				</div>

				<!-- 删除按钮 -->
				<button class="delete-btn" @click.stop="deleteBlog(blog.id)">删除</button>
			</div>

			<p v-if="!blogs.length">暂无博客文章</p>
		</div>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import api from '../utils/axios'

const router = useRouter()
const blogs = ref([])
const loading = ref(true)
const error = ref('')

async function fetchBlogs() {
	loading.value = true
	error.value = ''
	try {
		const res = await api.get('/posts')
		blogs.value = res.data || []
	} catch (err) {
		console.error('加载博客失败:', err)
		error.value = '加载博客失败'
	} finally {
		loading.value = false
	}
}

onMounted(fetchBlogs)

function viewBlog(blog) {
	router.push({ name: 'BlogDetail', params: { id: blog.id } })
}

function goTo(routeObj) {
	router.push(routeObj)
}

// 删除博客
async function deleteBlog(id) {
	if (!confirm('确定要删除这篇博客吗？')) return
	try {
		await api.delete(`/posts/${id}`)
		alert('删除成功')
		await fetchBlogs()
	} catch (err) {
		console.error('删除博客失败:', err)
		alert('删除失败')
	}
}
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

.blog-list {
	display: flex;
	flex-direction: column;
	gap: 1rem;
	margin-top: 2rem;
}

.blog-card {
	display: flex;
	align-items: center;
	justify-content: space-between;
	background: #fff;
	border: 1px solid #ddd;
	border-radius: 12px;
	padding: 1rem;
	cursor: default;
	transition: all 0.3s ease;
}

.blog-card:hover {
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	transform: translateY(-3px);
}

.blog-content {
	flex: 1;
	cursor: pointer;
	text-align: left;
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

/* 删除按钮 */
.delete-btn {
	margin-left: 1rem;
	padding: 0.3rem 0.6rem;
	background-color: #dc3545;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.delete-btn:hover {
	background-color: #c82333;
}
</style>
