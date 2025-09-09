// src/utils/axios.js
import axios from 'axios'

// 创建 Axios 实例
const instance = axios.create({
  baseURL: 'http://localhost:8082/api', // ✅ 后端接口统一前缀
  timeout: 5000, // 请求超时时间（毫秒）
})

// 请求拦截器（可以加 token）
instance.interceptors.request.use(
  config => {
    // 这里可以加上 JWT token，例如：
    // config.headers.Authorization = 'Bearer ' + localStorage.getItem('token')
    return config
  },
  error => Promise.reject(error)
)

// 响应拦截器（统一错误处理）
instance.interceptors.response.use(
  response => response.data, // 直接返回后端 data 字段
  error => {
    console.error('Axios 请求出错:', error)
    return Promise.reject(error)
  }
)

export default instance
