<template>
	<div>
		<!-- 导航栏 -->
		<nav class="nav-bar">
			<router-link :to="{ name: 'Home' }" class="nav-button" :class="{ active: isActiveRoute('Home') }">
				🏠 首页
			</router-link>
			<router-link :to="{ name: 'Blog' }" class="nav-button" :class="{ active: isActiveRoute('Blog') }">
				📚 博客
			</router-link>
			<router-link :to="{ name: 'Profile' }" class="nav-button" :class="{ active: isActiveRoute('Profile') }">
				🧑 关于
			</router-link>
		</nav>

		<!-- 身份组件 -->
		<Identity />

		<!-- 路由视图 -->
		<div class="main-content">
			<router-view />
		</div>

		<!-- Footer -->
		<footer class="footer">
			Nothing is proven true, but everything is constantly challenged.
		</footer>
	</div>
</template>

<script setup>
import { useRoute } from 'vue-router'
import Identity from './components/Identity.vue'

const route = useRoute()

// 判断当前路由是否匹配，用于导航高亮
function isActiveRoute(name) {
	return route.name === name
}
</script>

<style>
/* 全局背景图样式 */
body {
	margin: 0;
	min-width: 320px;
	min-height: 100vh;
	background-color: #f0f2f5;
	background-image: url('/images/bg.jpg');
	background-size: cover;
	background-position: center center;
	background-attachment: fixed;
	background-repeat: no-repeat;
	font-family: 'Segoe UI', Avenir, Helvetica, Arial, sans-serif;
	font-weight: 400;
	line-height: 1.6;
	color: #2c3e50;
}

/* 导航栏样式 */
.nav-bar {
	display: flex;
	gap: 2rem;
	justify-content: center;
	padding: 1rem;
	background-color: rgba(0, 0, 0, 0.3);
	border-radius: 12px;
}

.nav-button {
	padding: 0.8rem 1.2rem;
	min-width: 120px;
	font-size: 1rem;
	color: #fff;
	background-image: linear-gradient(315deg, #6a11cb 0%, #2575fc 74%);
	border: none;
	border-radius: 8px;
	cursor: pointer;
	text-decoration: none;
	transition: 0.3s ease;
	user-select: none;
	display: flex;
	align-items: center;
	justify-content: center;
}

.nav-button:hover {
	background-image: linear-gradient(315deg, #2575fc 0%, #6a11cb 74%);
}

.nav-button.active {
	box-shadow: 0 0 10px #2575fc;
	font-weight: bold;
}

/* Footer 样式 */
.footer {
	text-align: center;
	padding: 1rem;
	color: #eee;
	font-size: 0.9rem;
	background-color: rgba(0, 0, 0, 0.4);
	border-top: 1px solid rgba(255, 255, 255, 0.2);
	margin-top: 2rem;
	border-radius: 12px 12px 0 0;
	position: fixed;
	bottom: 0;
	left: 0;
	right: 0;
}
</style>