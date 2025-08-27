<script setup>
import AppHeader from './components/common/AppHeader.vue'
import SideNavigation from './components/common/SideNavigation.vue'
</script>

<template>


  <div class="app-container">
    <AppHeader />
    <div class="main-container">
      <SideNavigation />
      <main class="content">
        <RouterView />
      </main>

    </div>
  </div>
</template>

<style scoped>
.app-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.main-container {
  display: flex;
  flex: 1;
  overflow: hidden;
}

.content {
  flex: 1;
  padding: 2rem;
  overflow-y: auto;
}
</style>
