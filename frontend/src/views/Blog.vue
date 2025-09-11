<template>
	<div class="page-container">
		<h1>博客列表</h1>

		<!-- 加载中状态 -->
		<p v-if="loading">加载中...</p>

		<!-- 错误提示 -->
		<p v-else-if="error">{{ error }}</p>

		<!-- 博客列表 -->
		<div v-else class="blog-list">
			<div class="blog-card" v-for="post in posts" :key="post.id">
				<div class="blog-content" @click="viewBlog(post)">
					<h3>{{ post.title }}</h3>
					<p>{{ post.summary }}</p>
				</div>

				<!-- 删除按钮 -->
				<button class="delete-btn" @click.stop="deleteBlog(post.id)">删除</button>
			</div>

			<p v-if="!posts.length">暂无博客文章</p>
		</div>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import api from '../utils/axios'

const router = useRouter()
const posts = ref([])
const loading = ref(true)
const error = ref('')

async function fetchPosts() {
	loading.value = true
	error.value = ''
	try {
		const res = await api.get('/posts')
		posts.value = res.data ?? []
	} catch (e) {
		console.error('获取博客列表失败：', e)
		error.value = '获取博客列表失败'
	} finally {
		loading.value = false
	}
}

onMounted(fetchPosts)

function viewBlog(post) {
	router.push({ name: 'BlogDetail', params: { id: post.id } })
}

// 删除博客
async function deleteBlog(id) {
	if (!confirm('确定要删除这篇博客吗？')) return
	try {
		await api.delete(`/posts/${id}`)
		alert('删除成功')
		await fetchPosts()
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
