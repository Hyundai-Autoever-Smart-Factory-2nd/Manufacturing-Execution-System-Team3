<script setup>
import { RouterLink } from 'vue-router'
</script>

<template>
  <nav class="side-navigation">
    <RouterLink to="/" class="nav-item">
      <i class="fas fa-home"></i>
      홈
    </RouterLink>
    <RouterLink to="/worksheet" class="nav-item">
      <i class="fas fa-file-alt"></i>
      작업지시서
    </RouterLink>
    <RouterLink to="/about" class="nav-item">
      <i class="fas fa-info-circle"></i>
      About
    </RouterLink>
  </nav>
</template>

<style scoped>
.side-navigation {
  width: 250px;
  background-color: #f8f9fa;
  height: 100%;
  padding: 1rem;
}

.nav-item {
  display: flex;
  align-items: center;
  padding: 0.75rem 1rem;
  text-decoration: none;
  color: #2c3e50;
  margin-bottom: 0.5rem;
  border-radius: 4px;
  gap: 0.5rem;
}

.nav-item:hover {
  background-color: #e9ecef;
}

</style>
