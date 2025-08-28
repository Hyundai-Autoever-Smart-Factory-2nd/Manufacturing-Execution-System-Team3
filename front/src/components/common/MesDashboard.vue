<template>
  <div class="mes-dashboard p-6 grid grid-cols-12 grid-rows-4 gap-6">
    <!-- 설비 상태 카드 -->
    <div class="col-span-4 bg-white shadow rounded-2xl p-4">
      <h2 class="text-xl font-bold mb-4">설비 정보</h2>

      <table class="w-full border-collapse">
        <tbody>
          <!-- 설비명 -->
          <tr class="border-b">
            <td class="py-2 px-4 font-semibold text-gray-600 w-1/3">설비명</td>
            <td class="py-2 px-4 flex justify-between items-center">
              <span :class="worksheet.machineName ? 'text-green-600 font-semibold' : 'text-red-600 font-semibold'">{{ worksheet.machineName }}</span>
            </td>
          </tr>

          <!-- 품목명 -->
          <tr class="border-b">
            <td class="py-2 px-4 font-semibold text-gray-600">품목명</td>
            <td class="py-2 px-4">{{ worksheet.productName }}</td>
          </tr>

          <!-- 작업자명 -->
          <tr class="border-b">
            <td class="py-2 px-4 font-semibold text-gray-600">작업자명</td>
            <td class="py-2 px-4">{{ worksheet.workerName }}</td>
          </tr>

          <!-- 이미지 (머지) -->
          <tr>
            <td colspan="2" class="py-4 text-center">
              <img
                :src="'https://picsum.photos/500/200'"
                alt="랜덤 이미지"
                class="mx-auto rounded-lg shadow"
              />
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- 생산 실적 카드 -->
    <div class="col-span-8 bg-white shadow rounded-2xl p-6">
      <h1 class="text-xl font-bold mb-6">생산 현황</h1>

      <!-- 2컬럼 × 3행 -->
      <div class="grid grid-cols-2 gap-6 text-center">
        <!-- 금일 목표 -->
        <div class="bg-gray-50 p-6 rounded-lg shadow-sm flex flex-col justify-center">
          <p class="text-gray-600">금일 목표</p>
          <p class="text-2xl font-bold">{{ worksheet.amount }}</p>
        </div>

        <!-- 달성률 -->
        <div class="bg-gray-50 p-6 rounded-lg shadow-sm flex flex-col justify-center">
          <p class="text-gray-600">달성률</p>
          <p class="text-2xl font-bold text-blue-600">
            {{ (result.passQty / worksheet.amount * 100).toFixed(0) }}%
          </p>
        </div>

        <!-- 현재 양품 수 -->
        <div class="bg-gray-50 p-6 rounded-lg shadow-sm flex flex-col justify-center">
          <p class="text-gray-600">현재 양품 수</p>
          <p class="text-2xl font-bold text-green-600">
            {{ result.passQty ? result.passQty : 0 }}
          </p>
        </div>

        <!-- 현재 불량 수 -->
        <div class="bg-gray-50 p-6 rounded-lg shadow-sm flex flex-col justify-center">
          <p class="text-gray-600">현재 불량 수</p>
          <p class="text-2xl font-bold text-green-600">
            {{ result.failQty ? result.failQty : 0 }}
          </p>
        </div>

        <!-- 양품 등록 -->
        <div class="bg-gray-50 p-6 rounded-lg shadow-sm flex flex-col justify-center" 
              @click="pass">
          <p class="text-gray-600">양품 등록</p>
          <p class="text-2xl font-bold"></p>
        </div>

        <!-- 불량 등록 -->
        <div class="bg-gray-50 p-6 rounded-lg shadow-sm flex flex-col justify-center"
              @click="fail">
          <p class="text-gray-600">불량 등록</p>
          <p class="text-2xl font-bold"></p>
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
const resultURL = '/api/result';
const worksheet = ref([]);
const result = ref({
  worksheetId: null,
  isPass: null,
})
const resultSelect = ref({});

const pass = async () => {
  result.value.worksheetId = worksheet.value.worksheetId;
  result.value.isPass = 'true'
  try {
    const response = await fetch(resultURL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json", // JSON 전송
      },
      // credentials: "include", // 쿠키 인증 필요 시
      body: JSON.stringify(result.value), // ref 값 직렬화
    });
    if (!response.ok) {
      const jsonData = await response.json();
      throw new Error(jsonData.message);
    }
  } catch (error) {
    console.log(error.message)
  }
}

const fail = async () => {
  result.value.worksheetId = worksheet.value.worksheetId;
  result.value.isPass = 'false'
  try {
    const response = await fetch(resultURL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json", // JSON 전송
      },
      // credentials: "include", // 쿠키 인증 필요 시
      body: JSON.stringify(result.value), // ref 값 직렬화
    });
    if (!response.ok) {
      const jsonData = await response.json();
      throw new Error(jsonData.message);
    }
  } catch (error) {
    console.log(error.message)
  }
}

const resultFetch = async () => {
  try {
    const response = await fetch(resultURL + `/${worksheet.value.worksheetId}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });
    const jsonData = await response.json();
    if (!response.ok) {
      throw new Error("서버 오류: " + jsonData.message);
    }
    resultSelect.value = jsonData;
    console.log("조회 성공:", resultSelect.value);
  } catch (error) {
    console.error("조회 실패:", error);
  }
};
resultFetch();


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

