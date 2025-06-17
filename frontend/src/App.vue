<template>
	<div>
	  <nav style="display: flex; gap: 2rem; justify-content: center; padding: 1rem; background-color: rgba(0,0,0,0.3);">
		<button @click="current = 'Home'">首页</button>
		<button @click="current = 'Blog'">博客</button>
		<button @click="current = 'Profile'">个人中心</button>
	  </nav>
	  <component :is="currentComponent" />
	</div>
  </template>
  
  <script setup>
  import { ref, computed } from 'vue'
  import Home from './components/Home.vue'
  import Blog from './components/Blog.vue'
  import Profile from './components/Profile.vue'
  
  const current = ref('Home')
  
  const currentComponent = computed(() => {
	return { Home, Blog, Profile }[current.value]
  })
  </script>
  
  <style scoped>
  button {
	padding: 0.6rem 1.2rem;
	font-size: 1rem;
	color: #fff;
	background-color: #6a11cb;
	background-image: linear-gradient(315deg, #6a11cb 0%, #2575fc 74%);
	border: none;
	border-radius: 8px;
	cursor: pointer;
	transition: 0.3s ease;
  }
  
  button:hover {
	background-image: linear-gradient(315deg, #2575fc 0%, #6a11cb 74%);
  }
  </style>
  