<template>
  <div class="machine-grid">
    <button
      v-for="m in machines"
      :key="m.machineId"
      class="square-btn"
      @click="goMes(m.machineId)"
    >
      {{ m.machineName }}
    </button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()

const goMes = (id) => {
  router.push(`/mes/${id}`)
}

const machineURL = '/api/machine';
const machines = ref([]);
const machineFetch = async () => {
  try {
    const response = await fetch(machineURL, {
      method: "GET",
      headers: {
        "Content-Type": "application/json", // JSON 전송
      },
      // credentials: "include", // 쿠키 인증 필요 시
    });

    if (!response.ok) {
      throw new Error("서버 오류: " + response.status);
    }
    const jsonData = await response.json();
    machines.value = jsonData.sort((a, b) => a.machineId - b.machineId);
    console.log("조회 성공:", machines.value);
  } catch (error) {
    console.error("조회 실패:", error);
  }
};


machineFetch();



// const handleClick = (m) => {
//   // TODO: 원하는 동작 (라우팅/모달/상태변경 등)
//   console.log('clicked:', m)
// }
</script>

<style scoped>
.machine-grid {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 12px;
  max-width: 600px;
}

/* 정사각형 버튼 */
.square-btn {
  width: 100%;
  aspect-ratio: 1 / 1;          /* 핵심: 정사각형 유지 */
  border: 1px solid #e5e7eb;
  border-radius: 12px;
  background: #fff;
  font-weight: 600;
  font-size: 18px;
  cursor: pointer;
  transition: transform .08s ease, box-shadow .15s ease, background .15s ease;
}

/* 가운데 정렬 */
.square-btn {
  display: flex;
  align-items: center;
  justify-content: center;
}

/* 호버/액티브 효과 */
.square-btn:hover {
  box-shadow: 0 6px 16px rgba(0,0,0,.08);
}
.square-btn:active {
  transform: translateY(1px);
  background: #f9fafb;
}

/* 반응형: 화면 좁아지면 2열→1열 */
@media (max-width: 640px) {
  .machine-grid { grid-template-columns: repeat(2, minmax(0, 1fr)); }
}
@media (max-width: 400px) {
  .machine-grid { grid-template-columns: 1fr; }
}
</style>