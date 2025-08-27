<script setup>
import { Label } from 'radix-vue'
// import { Input } from "@/components/ui/Input"
import { ref, computed } from "vue";
const worksheet = ref({
  amount: null,
  machineId: null,
  productId: null,
  workerId: null
})
const URL = '/api/worksheet';
const machineURL = '/api/machine';
const workerURL = '/api/worker';
const productURL = '/api/product';


// 작업지시 등록
const submitForm = async () => {
  try {
    const response = await fetch(URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json", // JSON 전송
      },
      // credentials: "include", // 쿠키 인증 필요 시
      body: JSON.stringify(worksheet.value), // ref 값 직렬화
    });

    if (!response.ok) {
      throw new Error("서버 오류: " + response.status);
    }
    // const jsonData = await response.json();
    // console.log("등록 성공:", jsonData);
    dataFetch();
  } catch (error) {
    console.error("등록 실패:", error);
    dataFetch();
  }
};

const rows = ref([]);
// 작업지시 조회
const dataFetch = async () => {
  try {
    const response = await fetch(URL, {
      method: "GET",
      headers: {
        "Content-Type": "application/json", // JSON 전송
      },
      // credentials: "include", // 쿠키 인증 필요 시
    });

    if (!response.ok) {
      throw new Error("서버 오류: " + response.status);
    }
    rows.value = await response.json();
    console.log("조회 성공:", rows.value);
  } catch (error) {
    console.error("조회 실패:", error);
  }
};

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
    machines.value = await response.json();
    console.log("조회 성공:", machines.value);
  } catch (error) {
    console.error("조회 실패:", error);
  }
};

const workers = ref([]);
const workFetch = async () => {
  try {
    const response = await fetch(workerURL, {
      method: "GET",
      headers: {
        "Content-Type": "application/json", // JSON 전송
      },
      // credentials: "include", // 쿠키 인증 필요 시
    });

    if (!response.ok) {
      throw new Error("서버 오류: " + response.status);
    }
    workers.value = await response.json();
    console.log("조회 성공:", workers.value);
  } catch (error) {
    console.error("조회 실패:", error);
  }
};

const products = ref([]);
const productFetch = async () => {
  try {
    const response = await fetch(productURL, {
      method: "GET",
      headers: {
        "Content-Type": "application/json", // JSON 전송
      },
      // credentials: "include", // 쿠키 인증 필요 시
    });

    if (!response.ok) {
      throw new Error("서버 오류: " + response.status);
    }
    products.value = await response.json();
    console.log("조회 성공:", products.value);
  } catch (error) {
    console.error("조회 실패:", error);
  }
};

const changeMachine = (event) => {
  console.log(event.target.value)
  worksheet.value.machineId = event.target.value;
};

const changeProduct = (event) => {
  console.log(event.target.value)
  worksheet.value.productId = event.target.value;
};

const changeWorker = (event) => {
  console.log(event.target.value)
  worksheet.value.workerId = event.target.value;
};

const columns = ['지시수량', '설비명', '품목명', '작업자명'];
dataFetch();
machineFetch();
workFetch();
productFetch();

//페이징
    const currentPage = ref(1); // 현재 페이지
    const pageSize = ref(10); // 한 페이지에 보여줄 항목 수

    // 현재 페이지에 맞는 데이터 계산
    const pagedRows = computed(() => {
      const start = (currentPage.value - 1) * pageSize.value;
      const end = start + pageSize.value;
      return rows.value.slice(start, end);
    });

    // 전체 페이지 수 계산
    const totalPages = computed(() => Math.ceil(rows.value.length / pageSize.value));

    // 페이지 이동 함수
    const goPage = (page) => {
      if (page >= 1 && page <= totalPages.value) {
        currentPage.value = page;
      }
    };
</script>

<template>
  <h1>작업지시서 등록</h1>
  <div class="worksheet">
    <form @submit.prevent="submitForm" class="form">
      <div class="form-group">
        <label>생산량:</label>
        <input type="number" v-model="worksheet.amount" required>
        <label>기계 ID:</label>
        <!-- <input type="number" v-model="worksheet.machineId" required> -->
         <select class="select-box" name="machineId" v-model="worksheet.machineId" v-on:change="changeMachine" required>
            <option v-for="machine in machines" :value="machine.machineId">{{ machine.machineName }}</option>
        </select>
        <label>제품 ID:</label>
        <!-- <input type="number" v-model="worksheet.productId" required> -->
        <select class="select-box" name="productId" v-model="worksheet.productId" v-on:change="changeProduct" required>
            <option v-for="product in products" :value="product.productId">{{ product.productName }}</option>
        </select>
        <label>작업자 ID:</label>
        <!-- <input type="number" v-model="worksheet.workerId" required> -->
         <select class="select-box" name="workerId" v-model="worksheet.workerId" v-on:change="changeWorker" required>
            <option v-for="worker in workers" :value="worker.workerId">{{ worker.workerName }}</option>
        </select>
      </div>

      <button class="button button--primary" type="submit">등록</button>
      <button class="button button--red" @click="del">삭제</button>
    </form>
  </div>

  <div class="data-grid">
    <table>
      <thead>
        <tr>
          <th v-for="col in columns" :key="col">{{ col }}</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="row in pagedRows" :key="row.worksheetId">
          <td>{{ row.amount }}</td>
          <td>{{ row.machineName }}</td>
          <td>{{ row.productName }}</td>
          <td>{{ row.workerName }}</td>
        </tr>
      </tbody>
    </table>
  </div>

  <div class="pagination">
  <button class="button button--page" @click="goPage(currentPage - 1)" v-if="currentPage !== 1" >이전</button>

  <button class="button button--page"
    v-for="page in totalPages"
    :key="page"
    @click="goPage(page)"
    :class="{ active: page === currentPage }"
  >
    {{ page }}
  </button>

  <button class="button button--page" @click="goPage(currentPage + 1)" v-if="currentPage !== totalPages">다음</button>
</div>

</template>

<style scoped>

.worksheet{
  margin: 1em 3em;
}


.form {
  display: flex;
  gap: 16px; /* 행 간격, 열 간격 */
  align-items: center;
  grid-template-columns: repeat(4, 1fr);
}

.form-group {
  display: flex; /* 각 그룹이 grid 칸을 직접 차지하도록 */
}

.select-box {
  background-color: white;
  border: 1px solid #ddd;
}


label {
  display: block;
  margin-bottom: 5px;
}

input {
  width: 100px;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

button {
  /* background-color: #4CAF50; */
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.button--primary {
  background-color: #43ae47;
}

.button--primary:hover {
  background-color: #1de523;
}

.button--page {
  background-color: #64e6ed;
}

.button--page:hover {
  background-color: #0ca5e7;
}

.button--page.active {
  background-color: #3700ff; /* 활성 페이지의 배경색 */
  color: white; /* 활성 페이지의 글자색 */
  border-color: #007bff;
}

.button--red {
  background-color: #b93953;
}

.button--red:hover {
  background-color: #ee0d3a;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  padding: 8px 12px;
  border: 1px solid #ccc;
}

th {
  background-color: #f4f4f4;
  text-align: left;
}

</style>
