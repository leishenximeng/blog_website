<template>
	<div class="identity-wrapper">
		<div class="identity">
			<img class="avatar" :src="avatarUrl" alt="avatar" />
			<span class="name">CJ</span>
		</div>
	</div>
</template>

<script setup>
const avatarUrl = '/assets/avatar.jpg'
</script>

<style scoped>
.identity-wrapper {
	display: flex;
	justify-content: center;
	margin: 1.5rem auto 1rem;
}

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