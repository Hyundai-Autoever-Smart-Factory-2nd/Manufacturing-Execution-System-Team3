<script setup>
const handleLogout = () => {
  // 로그아웃 로직
  console.log('로그아웃')
}

const openMes = () => {
  window.open('/mes/machine', '_blank', 'width=1200,height=800')
}

</script>

<template>
  <header class="header">
    <div class="logo-container">
      <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="50" height="50" />
      <h1>MES System</h1>
      <button @click="openMes">Work</button>
    </div>
    <div class="header-right">
      <span>사용자명</span>
      <button @click="handleLogout">로그아웃</button>
    </div>
  </header>
</template>



<style scoped>
.header {
  background-color: #2c3e50;
  color: white;
  padding: 1rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 60px;
}

.logo-container {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.logo {
  width: 40px;
  height: 40px;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 1rem;
}

button {
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 4px;
  background-color: #42b983;
  color: white;
  cursor: pointer;
}
</style>
