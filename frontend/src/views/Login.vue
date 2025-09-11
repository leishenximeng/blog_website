<template>
	<div class="login-container">
		<h2>用户登录</h2>

		<form @submit.prevent="login">
			<label>用户名：</label>
			<input v-model="username" type="text" required />

			<label>密码：</label>
			<input v-model="password" type="password" required />

			<button type="submit" :disabled="loading">
				{{ loading ? '登录中...' : '登录' }}
			</button>
		</form>

		<p class="message" v-if="message">{{ message }}</p>

		<p class="tip">
			没有账号？<router-link to="/register">注册</router-link>
		</p>
	</div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import api from '../utils/axios' // 你封装好的 Axios 实例

const router = useRouter()
const username = ref('')
const password = ref('')
const message = ref('')
const loading = ref(false)

async function login() {
	loading.value = true
	message.value = ''
	try {
		const res = await api.post('/login', { username: username.value, password: password.value })
		// 假设后端返回 { token: '...' } 或类似
		const token = res.data?.token
		if (token) {
			localStorage.setItem('token', token)
			message.value = '登录成功！'
			router.push({ name: 'Blog' }) // 登录成功跳转博客管理页
		} else {
			message.value = res.message || '登录失败'
		}
	} catch (err) {
		console.error('登录失败：', err)
		message.value = err.response?.data?.message || '登录请求失败'
	} finally {
		loading.value = false
	}
}
</script>

<style scoped>
.login-container {
	max-width: 400px;
	margin: 4rem auto;
	padding: 2rem;
	background-color: rgba(255, 255, 255, 0.9);
	border-radius: 12px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	text-align: center;
}

label {
	display: block;
	margin-top: 1rem;
	text-align: left;
}

input {
	width: 100%;
	padding: 0.5rem;
	margin-top: 0.5rem;
	border-radius: 6px;
	border: 1px solid #ccc;
}

button {
	margin-top: 1.5rem;
	padding: 0.6rem 1.2rem;
	font-size: 1rem;
	background-color: #2575fc;
	color: white;
	border: none;
	border-radius: 8px;
	cursor: pointer;
}

button:disabled {
	opacity: 0.6;
	cursor: not-allowed;
}

button:hover:enabled {
	background-color: #1a5ed8;
}

.message {
	color: red;
	margin-top: 1rem;
}

.tip {
	margin-top: 1.5rem;
}
</style>
