import { createRouter, createWebHistory } from 'vue-router'

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
	{ path: '/blog/:id', name: 'BlogDetail', component: BlogDetail, props: true, meta: { requiresAuth: true } },
	{ path: '/add-blog', name: 'AddBlog', component: AddBlog, meta: { requiresAuth: true } }
]

const router = createRouter({
	history: createWebHistory(),
	routes
})

// 导航守卫
router.beforeEach((to, from, next) => {
	const isLoggedIn = !!localStorage.getItem('token')
	if (to.meta.requiresAuth && !isLoggedIn) {
		next({ name: 'Login' })
	} else {
		next()
	}
})

export default router
