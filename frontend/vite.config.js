import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
	plugins: [vue()],
	server: {
		port: 5175, // ✅ 显式指定端口
		// host: 'localhost', // 👈️ 添加这一行更保险
		proxy: {
			'/api': 'http://localhost:8081',
		},
	},
})
