import { createRouter, createWebHistory } from 'vue-router'

// 页面级组件
import Home from '../views/Home.vue'
import Blog from '../views/Blog.vue'
import Profile from '../views/Profile.vue'
import BlogDetail from '../views/BlogDetail.vue'
import AddBlog from '../views/AddBlog.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'

const routes = [
	{ path: '/', name: 'Home', component: Home },

	{ path: '/login', name: 'Login', component: Login },
	{ path: '/register', name: 'Register', component: Register },

	{ path: '/blog', name: 'Blog', component: Blog, meta: { requiresAuth: true } },
	{ path: '/profile', name: 'Profile', component: Profile, meta: { requiresAuth: true } },
	{
		path: '/blog/:id',
		name: 'BlogDetail',
		component: BlogDetail,
		props: true,
		meta: { requiresAuth: true }
	},
	{
		path: '/add-blog',
		name: 'AddBlog',
		component: AddBlog,
		meta: { requiresAuth: true }
	}
]

const router = createRouter({
	history: createWebHistory(),
	routes
})

// 全局导航守卫
router.beforeEach((to, from, next) => {
	const token = localStorage.getItem('token') // 登录成功后存储的 token

	if (to.meta.requiresAuth && !token) {
		// 未登录且访问需要登录的页面 → 重定向到登录页
		next({ name: 'Login' })
	} else if ((to.name === 'Login' || to.name === 'Register') && token) {
		// 已登录访问登录/注册页 → 重定向到首页或博客管理页
		next({ name: 'Blog' })
	} else {
		next()
	}
})

export default router
