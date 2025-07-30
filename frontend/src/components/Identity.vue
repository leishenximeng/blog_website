<template>
	<!-- 外层容器用于居中 -->
	<div class="identity-wrapper">
		<div class="identity">
			<img class="avatar" :src="avatarUrl" alt="avatar" />
			<span class="name">CJ</span>
		</div>
	</div>
</template>

<script setup>
const avatarUrl = '/assets/avatar.jpg' // 放在 public/assets 下
</script>

<style scoped>
/* 外层容器：负责居中布局 */
.identity-wrapper {
	display: flex;
	justify-content: center;
	margin: 1rem auto;
	margin-top: 1.5rem;
	margin-bottom: 1rem;
}

/* 内部身份组件样式 */
.identity {
	display: flex;
	align-items: center;
	gap: 0.6rem;
	background: rgba(255, 255, 255, 0.1);
	padding: 0.5rem 1rem;
	border-radius: 12px;
	color: #fff;
	font-weight: bold;
}

.avatar {
	width: 36px;
	height: 36px;
	border-radius: 50%;
	object-fit: cover;
	border: 2px solid #fff;
}
</style>
