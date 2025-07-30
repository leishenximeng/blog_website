<template>
	<div>
		<nav class="nav-bar">
			<router-link to="/" class="nav-button" :class="{ active: isActive('/') }">
				🏠 首页
			</router-link>
			<router-link to="/blog" class="nav-button" :class="{ active: isActive('/blog') }">
				📚 博客
			</router-link>
			<router-link to="/profile" class="nav-button" :class="{ active: isActive('/profile') }">
				🧑 关于
			</router-link>
			<!-- 🧠 想法 -->
			<!-- 💡 灵感 -->
			<!-- ✍️ 写作 -->
		</nav>

		<!-- ✅ 新增身份组件 -->
		<Identity />

		<div class="main-content">
			<router-view />
		</div>

		<footer class="footer">
			Nothing is Proven True, but Everything is constantly challenged.
		</footer>
	</div>
</template>

<script setup>
import { useRoute } from 'vue-router'
import Identity from './components/Identity.vue'


const route = useRoute()

// 判断当前路由是否匹配，用来给按钮加高亮样式
function isActive(path) {
	return route.path === path
}
</script>

<style>
/* ✅ 页面背景图样式（全局作用） */
body {
	margin: 0;
	background-image: url('/images/bg.jpg');
	background-size: cover;
	background-position: center center;
	background-attachment: fixed;
	background-repeat: no-repeat;
	font-family: 'Helvetica', sans-serif;
}

/* ✅ 保持原导航栏样式 */
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
	min-width: 120px;            /* ✅ 最小宽度统一 */
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