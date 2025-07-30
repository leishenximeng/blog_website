import { createRouter, createWebHistory } from 'vue-router'
import Home from '../components/Home.vue'
import Blog from '../components/Blog.vue'
import Profile from '../components/Profile.vue'
import BlogDetail from '../components/BlogDetail.vue' // 新增的详情页面
import AddBlog from '../components/AddBlog.vue'  // 直接导入组件

const routes = [
	{ path: '/', component: Home },
	{ path: '/blog', component: Blog },
	{ path: '/profile', component: Profile },
	{
	  path: '/blog/:id',
	  name: 'BlogDetail', // ✅ 添加 name 字段
	  component: BlogDetail,
	  props: true
	},
	{
		path: '/add',
		name: 'AddBlog',
		component: AddBlog   // 👈 这里用同步加载
	}
  ]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
