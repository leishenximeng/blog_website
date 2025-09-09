<!-- Profile.vue -->
<template>
	<div class="page-container">
		<h1>个人中心</h1>
		<p class="description">欢迎回来，用户！你可以查看并管理自己的资料。</p>
		<p class="tip">未来还将支持头像上传、密码修改等功能。</p>
	</div>
</template>

<script setup>
</script>

<style scoped>
.page-container {
	max-width: 800px;
	margin: 2rem auto;
	background-color: rgba(255, 255, 255, 0.85);
	/* 半透明白底 */
	padding: 2rem;
	border-radius: 12px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	text-align: center;
}

h1,
p {
	text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.4);
	/* 文字阴影增强可读性 */
	color: #333;
}
</style>
