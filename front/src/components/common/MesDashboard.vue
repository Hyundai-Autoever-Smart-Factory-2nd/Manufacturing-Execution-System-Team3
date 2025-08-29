<template>
  <div class="mes-dashboard p-6 grid grid-cols-12 grid-rows-2 gap-6">
    <!-- 설비 상태 카드 -->
    <div class="col-span-4 row-span-1 bg-white shadow rounded-2xl p-4">
      <h2 class="text-xl font-bold mb-4 text-black">설비 정보</h2>

      <table class="w-full border-collapse">
        <tbody>
          <!-- 설비명 -->
          <tr class="border-b">
            <td class="py-2 px-4 font-semibold text-gray-600 w-1/3">설비명:</td>
            <td class="py-2 px-4 flex justify-between items-center">
              <span :class="worksheet.machineName ? 'text-green-600 font-semibold' : 'text-red-600 font-semibold'">{{ worksheet.machineName }}</span>
            </td>
          </tr>

          <!-- 품목명 -->
          <tr class="border-b">
            <td class="py-2 px-4 font-semibold text-gray-600">품목명:</td>
            <td class="py-2 px-4 text-black">{{ worksheet.productName }}</td>
          </tr>

          <!-- 작업자명 -->
          <tr class="border-b">
            <td class="py-2 px-4 font-semibold text-gray-600">작업자명:</td>
            <td class="py-2 px-4 text-black">{{ worksheet.workerName }}</td>
          </tr>

          <!-- 이미지 (머지) -->
          <tr>
            <td colspan="2" class="py-4 text-center">
              <img
                :src="'https://picsum.photos/500/200'"
                alt="랜덤 이미지"
                class="w-full h-auto object-contain rounded-lg shadow"
              />
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- 생산 실적 카드 -->
    <div class="col-span-8 row-span-1 bg-white shadow rounded-2xl p-6">
      <h1 class="text-xl font-bold mb-6 text-black" >생산 현황</h1>

      <!-- 2컬럼 × 3행 -->
      <div class="grid grid-cols-2 gap-6 text-center">
        <!-- 금일 목표 -->
        <div class="bg-gray-50 p-6 rounded-lg shadow-sm flex flex-col justify-center">
          <p class="text-gray-600">금일 목표</p>
          <p class="text-2xl font-bold text-black">{{ worksheet.amount }}</p>
        </div>

        <!-- 달성률 -->
        <div class="bg-gray-50 p-6 rounded-lg shadow-sm flex flex-col justify-center">
          <p class="text-gray-600">달성률</p>
          <p class="text-2xl font-bold text-blue-600">
            {{ ((totalPassQty ? totalPassQty : 0) / worksheet.amount  * 100).toFixed(0) }}%
          </p>
        </div>

        <!-- 현재 양품 수 -->
        <div class="bg-gray-50 p-6 rounded-lg shadow-sm flex flex-col justify-center">
          <p class="text-gray-600">현재 양품 수</p>
          <p class="text-2xl font-bold text-green-600">
            {{ totalPassQty ? totalPassQty : 0 }}
          </p>
        </div>

        <!-- 현재 불량 수 -->
        <div class="bg-gray-50 p-6 rounded-lg shadow-sm flex flex-col justify-center">
          <p class="text-gray-600">현재 불량 수</p>
          <p class="text-2xl font-bold text-red-600">
            {{ totalFailQty ? totalFailQty : 0 }}
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
    <div class="col-span-12 row-span-1 bg-white shadow rounded-2xl p-4">
      <h2 class="text-xl font-bold mb-4 text-black">알림</h2>

      <ul class="space-y-2 max-h-85 overflow-y-auto pr-2">
        <li
          v-for="rs in resultSelect"
          :key="rs.worksheetId"
          class="p-3 rounded border"
          :class="{
            'bg-green-50 border-green-200 text-green-700': rs.passed === true,
            'bg-red-50 border-red-200 text-red-700': rs.passed === false,
            'bg-yellow-50 border-yellow-200 text-yellow-700': rs.passed === null
          }"
        >
          <span v-if="rs.passed === true">✅정상</span>
          <span v-else>⚠️불량</span>
          <br />
          <small class="text-gray-500">{{ rs.completeDatetime }}</small>
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
  passed: null,
})
const resultSelect = ref({});
const totalPassQty = ref({});
const totalFailQty = ref({});

const pass = async () => {
  result.value.worksheetId = worksheet.value.worksheetId;
  result.value.passed = true
  try {
    const response = await fetch(resultURL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json", // JSON 전송
      },
      // credentials: "include", // 쿠키 인증 필요 시
      body: JSON.stringify(result.value), // ref 값 직렬화
    });
    resultFetch();
    if (!response.ok) {
      const jsonData = await response.json();
      throw new Error(jsonData.message);
    }
  } catch (error) {
    resultFetch();
    console.error(error.message)
  }
}

const fail = async () => {
  result.value.worksheetId = worksheet.value.worksheetId;
  result.value.passed = false
  try {
    const response = await fetch(resultURL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json", // JSON 전송
      },
      // credentials: "include", // 쿠키 인증 필요 시
      body: JSON.stringify(result.value), // ref 값 직렬화
    });
    resultFetch();
    if (!response.ok) {
      const jsonData = await response.json();
      throw new Error(jsonData.message);
    }
  } catch (error) {
    resultFetch();
    console.error(error.message)
  }
}

//실적 로그 조회
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
    
    // jsonData 양품 개수 세기
    totalPassQty.value = jsonData.filter(el => el.passed === true).length;
    totalFailQty.value = jsonData.length - totalPassQty.value;
  } catch (error) {
    console.error("조회 실패:", error);
  }
};


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
    resultFetch(); 
  } catch (error) {
    console.error("조회 실패:", error);
  }
};
worksheetFetch();
</script>

<style scoped>
.mes-dashboard {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  min-height: 100vh;
}

</style>

