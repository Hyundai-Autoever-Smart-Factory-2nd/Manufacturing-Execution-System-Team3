<script setup>
import { ref } from 'vue'

const isOpen = ref(false)

const openDialog = () => {
  isOpen.value = true
}

const closeDialog = () => {
  isOpen.value = false
}
</script>

<template>
  <div>
    <div @click="openDialog">
      <slot></slot>
    </div>

    <dialog v-if="isOpen" class="modal">
      <!-- Add your dialog content here -->
      <button @click="closeDialog">Close</button>
    </dialog>
  </div>
</template>
