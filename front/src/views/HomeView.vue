<template>
  <div>
    <h2>Real-time Random Data</h2>
    <v-chart :option="chartOption" style="width: 100%; height: 400px;" />
  </div>
</template>

<script setup>
import { reactive, onMounted } from 'vue'
import VChart from 'vue-echarts'
import * as echarts from 'echarts/core'
import {
  LineChart
} from 'echarts/charts'
import {
  TitleComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent
} from 'echarts/components'
import { CanvasRenderer } from 'echarts/renderers'

echarts.use([LineChart, TitleComponent, TooltipComponent, GridComponent, LegendComponent, CanvasRenderer])

const chartData = reactive({
  labels: [],
  values: []
})

const chartOption = reactive({
  title: { text: 'Random Data Stream' },
  tooltip: { trigger: 'axis' },
  xAxis: { type: 'category', data: chartData.labels },
  yAxis: { type: 'value', min: 0, max: 100 },
  series: [{ name: 'Random Value', type: 'line', data: chartData.values }]
})

onMounted(() => {
  const eventSource = new EventSource('http://localhost:8080/stream')
  eventSource.onmessage = (e) => {
    const value = Number(e.data)
    chartData.labels.push(new Date().toLocaleTimeString())
    chartData.values.push(value)

    // 최근 20개만 유지
    if (chartData.labels.length > 20) {
      chartData.labels.shift()
      chartData.values.shift()
    }

    // 옵션 업데이트
    chartOption.xAxis.data = chartData.labels
    chartOption.series[0].data = chartData.values
  }
})
</script>
