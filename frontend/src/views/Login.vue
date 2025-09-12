<template>
	<div class="auth-container">
		<h2>登录</h2>
		<form @submit.prevent="login">
			<label>用户名：</label>
			<input v-model="username" required />

			<label>密码：</label>
			<input type="password" v-model="password" required />

			<button type="submit">{{ loading ? '登录中...' : '登录' }}</button>
		</form>
		<p v-if="errorMsg" class="error">{{ errorMsg }}</p>
		<p>还没有账号？ <router-link to="/register">注册</router-link></p>
	</div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import api from '../utils/axios'

const router = useRouter()
const username = ref('')
const password = ref('')
const errorMsg = ref('')
const loading = ref(false)

async function login() {
	loading.value = true
	errorMsg.value = ''
	try {
		const res = await api.post('/users/login', {
			username: username.value,
			password: password.value
		})
		alert(res.message || '登录成功')
		// 登录成功，存个标记（简单示例）
		localStorage.setItem('token', 'logged_in')
		router.push({ name: 'Blog' })
	} catch (err) {
		console.error(err)
		errorMsg.value = err.response?.data?.message || '登录失败'
	} finally {
		loading.value = false
	}
}
</script>

<style scoped>
.auth-container {
	max-width: 400px;
	margin: 3rem auto;
	padding: 2rem;
	background: rgba(255, 255, 255, 0.9);
	border-radius: 10px;
	text-align: center;
}

input {
	width: 100%;
	margin-top: 0.5rem;
	padding: 0.5rem;
}

button {
	margin-top: 1rem;
	padding: 0.6rem 1.2rem;
	background-color: #2575fc;
	color: white;
	border: none;
	border-radius: 6px;
	cursor: pointer;
}

button:disabled {
	opacity: 0.6;
	cursor: not-allowed;
}

.error {
	color: red;
	margin-top: 1rem;
}
</style>
