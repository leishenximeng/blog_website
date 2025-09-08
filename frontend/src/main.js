import { createApp } from 'vue'
import App from './App.vue'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import 'animate.css'
import './assets/style.css'

import router from './router' // 引入路由
import axios from 'axios'

// ✅ 全局配置后端 baseURL
axios.defaults.baseURL = 'http://localhost:8082' // 根据实际端口修改

const app = createApp(App)

app.use(ElementPlus)
app.use(router) // 注册路由
app.mount('#app')
