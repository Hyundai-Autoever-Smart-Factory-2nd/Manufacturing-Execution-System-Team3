<script setup>
import { ref } from 'vue'

const worksheet = ref({
  amount: null,
  machine_id: null,
  product_id: null,
  worker_id: null
})

const submitForm = async () => {
  try {
    // API 호출 로직 추가 예정
    console.log('제출된 데이터:', worksheet.value)
  } catch (error) {
    console.error('등록 실패:', error)
  }
}
</script>

<template>
  <div class="worksheet">
    <h2>작업지시서 등록</h2>
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label>생산량:</label>
        <input type="number" v-model="worksheet.amount" required>
      </div>

      <div class="form-group">
        <label>기계 ID:</label>
        <input type="number" v-model="worksheet.machine_id" required>
      </div>

      <div class="form-group">
        <label>제품 ID:</label>
        <input type="number" v-model="worksheet.product_id" required>
      </div>

      <div class="form-group">
        <label>작업자 ID:</label>
        <input type="number" v-model="worksheet.worker_id" required>
      </div>

      <button type="submit">등록</button>
    </form>
  </div>

</template>

<style scoped>
.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

button {
  background-color: #4CAF50;
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #45a049;
}

</style>
