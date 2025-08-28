<template>
  <div class="mes-dashboard p-6 grid grid-cols-12 gap-6">
    <!-- 설비 상태 카드 -->
    <div class="col-span-4 bg-white shadow rounded-2xl p-4">
      <h2 class="text-xl font-bold mb-4">설비명</h2>
      <ul>
        <li class="flex justify-between py-1">
          <span>Press-01</span>
          <span class="text-green-600 font-semibold">가동중</span>
        </li>
        <li class="flex justify-between py-1">
          <span>Lathe-02</span>
          <span class="text-red-600 font-semibold">정지</span>
        </li>
        <li class="flex justify-between py-1">
          <span>Robot-03</span>
          <span class="text-yellow-600 font-semibold">대기</span>
        </li>
      </ul>
      <h2 class="text-xl font-bold mb-4">작업자명</h2>
    </div>

    <!-- 생산 실적 카드 -->
    <div class="col-span-8 bg-white shadow rounded-2xl p-4">
      <h2 class="text-xl font-bold mb-4">생산 실적</h2>
      <div class="grid grid-cols-3 gap-4 text-center">
        <div class="bg-gray-50 p-4 rounded-lg">
          <p class="text-gray-600">금일 목표</p>
          <p class="text-2xl font-bold">1,000 EA</p>
        </div>
        <div class="bg-gray-50 p-4 rounded-lg">
          <p class="text-gray-600">현재 실적</p>
          <p class="text-2xl font-bold text-green-600">720 EA</p>
        </div>
        <div class="bg-gray-50 p-4 rounded-lg">
          <p class="text-gray-600">달성률</p>
          <p class="text-2xl font-bold text-blue-600">72%</p>
        </div>
      </div>
    </div>

    <!-- 알림 패널 -->
    <div class="col-span-12 bg-white shadow rounded-2xl p-4">
      <h2 class="text-xl font-bold mb-4">알림</h2>
      <ul class="space-y-2">
        <li class="p-3 rounded bg-red-50 border border-red-200 text-red-700">
          ⚠️ Lathe-02 설비 이상 발생
        </li>
        <li class="p-3 rounded bg-yellow-50 border border-yellow-200 text-yellow-700">
          ⏸ Robot-03 자재 투입 대기
        </li>
        <li class="p-3 rounded bg-green-50 border border-green-200 text-green-700">
          ✅ Press-01 정상 가동 중
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
/* 추후 API 연동 시 fetch/axios로 데이터 가져오면 됨 */
import { useRoute } from "vue-router";
import { ref } from 'vue';
const route = useRoute();
const machineId = route.params.machineId; // "123"

const URL = '/api/worksheet';
const worksheet = ref([]);

const worksheetFetch = async () => {
  try {
    const response = await fetch(URL + `/${machineId}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });
    const jsonData = await response.json();
    if (!response.ok) {
      throw new Error("서버 오류: " + jsonData.message);
    }
    
    worksheet.value = jsonData;
    console.log("조회 성공:", worksheet.value);
  } catch (error) {
    console.error("조회 실패:", error);
  }
};
worksheetFetch();
</script>

<style scoped>
.mes-dashboard {
  background-color: #f9fafb; /* Tailwind gray-50 */
  min-height: 100vh;
}
</style>

