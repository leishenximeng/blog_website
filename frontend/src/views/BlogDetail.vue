<template>
	<div class="page-container">
		<!-- 加载中 -->
		<p v-if="loading">加载中...</p>

		<!-- 错误提示 -->
		<p v-else-if="error">{{ error }}</p>

		<!-- 正常内容 -->
		<template v-else>
			<h1>{{ blog.title }}</h1>
			<p class="description">发布时间：{{ blog.createdAt }}</p>
			<div class="blog-content">
				<p v-for="(para, idx) in paragraphs" :key="idx">{{ para }}</p>
			</div>

			<button class="back-button" @click="goBack">返回博客列表</button>
		</template>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import api from '../utils/axios'

const route = useRoute()
const router = useRouter()

const blog = ref({})
const paragraphs = ref([])
const loading = ref(true)
const error = ref('')

onMounted(async () => {
	const id = route.params.id
	try {
		const res = await api.get(`/posts/${id}`)
		// res 已经是 ApiResponse<T>，data 是博客对象
		blog.value = res.data || {}
		paragraphs.value = blog.value.content?.split('\n').filter(line => line.trim()) || []
	} catch (e) {
		error.value = '加载文章失败'
		blog.value = {
			title: '文章未找到',
			summary: '',
			content: '你请求的文章不存在。',
			createdAt: ''
		}
		paragraphs.value = ['你请求的文章不存在。']
	} finally {
		loading.value = false
	}
})

function goBack() {
	router.push({ name: 'Blog' })
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
