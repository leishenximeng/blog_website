<template>
	<div class="page-container">
	  <h1>{{ title }}</h1>
	  <p class="content">{{ content }}</p>
	</div>
  </template>
  
  <script setup>
  import { useRoute } from 'vue-router'
  
  const route = useRoute()
  const title = route.params.title || '博客详情'
  const content = route.params.content || '暂无内容'
  </script>
  
  <style scoped>
  .content {
	font-size: 1.2rem;
	line-height: 1.8;
	color: #333;
	text-align: left;
	margin-top: 1rem;
  }
  </style>
  