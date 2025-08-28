<template>
  <div class="mes-container">
    <div class="mes-header">
      <h1 class="mes-title">
        <i class="icon-factory"></i>
        Manufacturing Execution System
      </h1>
      <div class="status-indicator">
        <span class="status-dot online"></span>
        <span class="status-text">System Online</span>
      </div>
    </div>
    
    <div class="machine-section">
      <h2 class="section-title">Equipment Selection</h2>
      <div class="machine-grid">
        <button
          v-for="m in machines"
          :key="m.machineId"
          class="machine-card"
          @click="goMes(m.machineId)"
        >
          <div class="machine-header">
            <!-- <div class="machine-icon">
              <i class="icon-machine"></i>
            </div> -->
            <div class="machine-status">
              <span class="status-dot" :class="getStatusClass(m.machineId)"></span>
            </div>
          </div>
          <div class="machine-info">
            <h3 class="machine-name">{{ m.machineName }}</h3>
            <p class="machine-id">ID: {{ m.machineId }}</p>
            <!-- <div class="machine-stats">
              <span class="stat-item">
                <i class="icon-clock"></i>
                {{ getUptime(m.machineId) }}
              </span>
            </div> -->
          </div>
          <!-- <div class="machine-footer">
            <span class="access-btn">Access →</span>
          </div> -->
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const goMes = (id) => {
  router.push(`/mes/${id}`)
}
const URL = '/api/worksheet';
const machineURL = '/api/machine';
const machines = ref([]);

const machineFetch = async () => {
  try {
    const response = await fetch(machineURL, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error("서버 오류: " + response.status);
    }
    const jsonData = await response.json();
    machines.value = jsonData.sort((a, b) => a.machineId - b.machineId);
    // 모든 머신 상태 순서대로 가져오기
    for (const machine of machines.value) {
      await getStatus(machine.machineId);
    }
    console.log("조회 성공:", machines.value);
  } catch (error) {
    console.error("조회 실패:", error);
  }
};
const statuses = ref({});
const getStatus = async (machineId) => {
  let status = "online"; // 기본값
  try {
    const response = await fetch(URL + `/${machineId}`, {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    });

    if (!response.ok) status = "offline";
  } catch (error) {
    status = "offline";
  }
  statuses.value[machineId] = status; // 객체에 저장
  console.log(statuses.value)
  return status;
};
// // 머신 상태 시뮬레이션
const getStatusClass = (machineId) => {
  const status = statuses.value[machineId]; // 객체에서 machineId로 상태 가져오기
  // 상태 값이 없으면 offline으로 표시
  return status;
};

// 가동 시간 시뮬레이션
// const getUptime = (machineId) => {
//   const hours = 8 + (machineId % 12);
//   return `${hours}h 32m`;
// };

machineFetch();
</script>

<style scoped>
.mes-container {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  min-height: 100vh;
  padding: 24px;
  font-family: 'Segoe UI', system-ui, sans-serif;
}

.mes-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 32px;
  padding: 24px;
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  border-radius: 16px;
  border: 1px solid rgba(255, 255, 255, 0.2);
}

.mes-title {
  color: white;
  font-size: 28px;
  font-weight: 700;
  margin: 0;
  display: flex;
  align-items: center;
  gap: 12px;
}

.status-indicator {
  display: flex;
  align-items: center;
  gap: 8px;
  color: white;
  font-weight: 500;
}

.machine-section {
  background: rgba(255, 255, 255, 0.95);
  padding: 32px;
  border-radius: 20px;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
}

.section-title {
  color: #1f2937;
  font-size: 24px;
  font-weight: 600;
  margin: 0 0 24px 0;
  padding-bottom: 16px;
  border-bottom: 2px solid #e5e7eb;
}

.machine-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 24px;
}

.machine-card {
  background: white;
  border: 2px solid #e5e7eb;
  border-radius: 16px;
  padding: 24px;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  overflow: hidden;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.machine-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 12px 32px rgba(0, 0, 0, 0.15);
  border-color: #3b82f6;
}

.machine-card:active {
  transform: translateY(-2px);
}

.machine-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 16px;
}

.machine-icon {
  width: 48px;
  height: 48px;
  background: linear-gradient(45deg, #3b82f6, #1d4ed8);
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  font-size: 24px;
}

.machine-status {
  display: flex;
  align-items: center;
}

.status-dot {
  width: 12px;
  height: 12px;
  border-radius: 50%;
  position: relative;
}

.status-dot.online {
  background: #10b981;
  box-shadow: 0 0 0 2px rgba(16, 185, 129, 0.3);
}

/* .status-dot.warning {
  background: #f59e0b;
  box-shadow: 0 0 0 2px rgba(245, 158, 11, 0.3);
} */

.status-dot.offline {
  background: #ef4444;
  box-shadow: 0 0 0 2px rgba(239, 68, 68, 0.3);
}

.machine-info {
  margin-bottom: 20px;
}

.machine-name {
  font-size: 20px;
  font-weight: 700;
  color: #1f2937;
  margin: 0 0 4px 0;
}

.machine-id {
  font-size: 14px;
  color: #6b7280;
  margin: 0 0 12px 0;
  font-family: 'Courier New', monospace;
}

.machine-stats {
  display: flex;
  gap: 16px;
}

.stat-item {
  display: flex;
  align-items: center;
  gap: 4px;
  font-size: 13px;
  color: #4b5563;
  font-weight: 500;
}

.machine-footer {
  display: flex;
  justify-content: flex-end;
  padding-top: 16px;
  border-top: 1px solid #f3f4f6;
}

.access-btn {
  font-size: 14px;
  font-weight: 600;
  color: #3b82f6;
  transition: color 0.2s ease;
}

.machine-card:hover .access-btn {
  color: #1d4ed8;
}

/* 아이콘 스타일 (간단한 CSS 아이콘) */
.icon-factory::before { content: "🏭"; }
.icon-machine::before { content: "⚙️"; }
.icon-clock::before { content: "⏱️"; }

/* 반응형 */
@media (max-width: 768px) {
  .mes-container {
    padding: 16px;
  }
  
  .mes-header {
    flex-direction: column;
    gap: 16px;
    text-align: center;
  }
  
  .mes-title {
    font-size: 24px;
  }
  
  .machine-section {
    padding: 24px 16px;
  }
  
  .machine-grid {
    grid-template-columns: 1fr;
  }
  
  .machine-card {
    padding: 20px;
  }
}

@media (max-width: 480px) {
  .machine-grid {
    gap: 16px;
  }
  
  .machine-card {
    padding: 16px;
  }
  
  .machine-icon {
    width: 40px;
    height: 40px;
    font-size: 20px;
  }
  
  .machine-name {
    font-size: 18px;
  }
}
</style>