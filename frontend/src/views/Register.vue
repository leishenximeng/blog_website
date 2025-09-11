<template>
	<div class="register-container">
		<h2>用户注册</h2>

		<form @submit.prevent="register">
			<label>用户名：</label>
			<input v-model="username" type="text" required />

			<label>密码：</label>
			<input v-model="password" type="password" required />

			<label>确认密码：</label>
			<input v-model="confirmPassword" type="password" required />

			<button type="submit" :disabled="loading">
				{{ loading ? '注册中...' : '注册' }}
			</button>
		</form>

		<p class="message" v-if="message">{{ message }}</p>

		<p class="tip">
			已有账号？<router-link to="/login">登录</router-link>
		</p>
	</div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import api from '../utils/axios' // 封装好的 Axios 实例

const router = useRouter()
const username = ref('')
const password = ref('')
const confirmPassword = ref('')
const message = ref('')
const loading = ref(false)

async function register() {
	if (password.value !== confirmPassword.value) {
		message.value = '两次输入的密码不一致'
		return
	}

	loading.value = true
	message.value = ''

	try {
		const res = await api.post('/register', {
			username: username.value,
			password: password.value
		})
		message.value = res.message || '注册成功！'

		// 注册成功后跳转登录页
		router.push({ name: 'Login' })
	} catch (err) {
		console.error('注册失败：', err)
		message.value = err.response?.data?.message || '注册请求失败'
	} finally {
		loading.value = false
	}
}
</script>

<style scoped>
.register-container {
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
	background-color: #28a745;
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
	background-color: #218838;
}

.message {
	color: red;
	margin-top: 1rem;
}

.tip {
	margin-top: 1.5rem;
}
</style>
