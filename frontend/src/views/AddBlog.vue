<template>
	<div class="form-container">
		<h2>添加博客文章</h2>
		<form @submit.prevent="submitBlog">
			<label>标题：</label>
			<input v-model="blog.title" required />

			<label>摘要：</label>
			<input v-model="blog.summary" required />

			<label>内容：</label>
			<textarea v-model="blog.content" rows="10" required></textarea>

			<button type="submit" :disabled="loading">
				{{ loading ? '提交中...' : '提交' }}
			</button>
		</form>

		<p v-if="message">{{ message }}</p>
	</div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import api from '../utils/axios'

const router = useRouter()
const blog = ref({
	title: '',
	summary: '',
	content: ''
})

const message = ref('')
const loading = ref(false)

async function submitBlog() {
	loading.value = true
	message.value = ''
	try {
		const res = await api.post('/posts', blog.value)
		message.value = res.message || '提交成功！'

		blog.value.title = ''
		blog.value.summary = ''
		blog.value.content = ''

		router.push({ name: 'Blog' })
	} catch (e) {
		message.value = '提交失败：' + (e.message || '未知错误')
		console.error('提交博客失败：', e)
	} finally {
		loading.value = false
	}
}
</script>

<style scoped>
.form-container {
	max-width: 600px;
	margin: 2rem auto;
	padding: 2rem;
	border-radius: 12px;
	background-color: rgba(255, 255, 255, 0.85);
	/* 半透明白底 */
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

h2,
label,
p {
	text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.4);
	/* 增加文字阴影 */
	color: #333;
	/* 深色文字更清晰 */
}

label {
	display: block;
	margin-top: 1rem;
}

input,
textarea {
	width: 100%;
	padding: 0.5rem;
	margin-top: 0.5rem;
	border: 1px solid #ccc;
	border-radius: 6px;
	background-color: #fff;
	/* 输入框背景白色 */
}

button {
	margin-top: 1rem;
	padding: 0.6rem 1.2rem;
	background-color: #2575fc;
	color: white;
	border: none;
	border-radius: 8px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

button:disabled {
	opacity: 0.6;
	cursor: not-allowed;
}

button:hover:enabled {
	background-color: #1a5ed8;
}
</style>
