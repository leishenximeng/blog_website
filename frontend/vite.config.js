import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
	plugins: [vue()], // ✅ 启用 Vue 插件
	server: {
		port: 5175, // ✅ 显式指定开发端口
		// host: 'localhost', // （可选）如果需要通过 IP 访问可以加上这一行
		proxy: {
			'/api': 'http://localhost:8082', // ✅ 后端代理配置
		},
	},
})
