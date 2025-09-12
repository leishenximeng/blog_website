<template>
	<div class="auth-container">
		<h2>注册</h2>
		<form @submit.prevent="register">
			<label>用户名：</label>
			<input v-model="username" required />

			<label>密码：</label>
			<input type="password" v-model="password" required />

			<button type="submit">{{ loading ? '注册中...' : '注册' }}</button>
		</form>
		<p v-if="errorMsg" class="error">{{ errorMsg }}</p>
		<p>已有账号？ <router-link to="/login">登录</router-link></p>
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

async function register() {
	loading.value = true
	errorMsg.value = ''
	try {
		const res = await api.post('/users/register', {
			username: username.value,
			password: password.value
		})
		alert(res.message || '注册成功')
		router.push({ name: 'Login' })
	} catch (err) {
		console.error(err)
		errorMsg.value = err.response?.data?.message || '注册失败'
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
	background-color: #28a745;
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
