<template>
  <div>
    <h1>Machine Sensor Dashboard</h1>
    <div class="charts-grid">
      <div v-for="(chart, name) in charts" :key="name" class="chart-container">
        <h2>{{ name }}</h2>
        <v-chart class="chart" :option="chart" autoresize />
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, onMounted } from 'vue'
import { use } from 'echarts/core'
import VChart from 'vue-echarts'
import { LineChart, GaugeChart,BarChart } from 'echarts/charts'
import { TitleComponent, TooltipComponent, GridComponent } from 'echarts/components'
import { CanvasRenderer } from 'echarts/renderers'
import 'echarts-liquidfill'

use([BarChart, LineChart, GaugeChart, TitleComponent, TooltipComponent, GridComponent, CanvasRenderer])

const charts = reactive({})

onMounted(() => {
  const eventSource = new EventSource('http://localhost:8080/stream')
  eventSource.onmessage = (e) => {
    const msg = JSON.parse(e.data)
    const time = new Date().toLocaleTimeString()

    // 차트 없으면 새로 생성
    if (!charts[msg.name]) {
      if (msg.type === 1) {
        // type 1: 게이지 (압력)
        charts[msg.name] = {
          series: [
            {
              type: 'gauge',
              min: 0,
              max: 100,
              detail: { formatter: '{value}' },
              data: [{ value: msg.sensor_value, name: '압력' }]
            }
          ]
        }

      }else if (msg.type === 2) {
        // type 1: 게이지 (압력)
        charts[msg.name] = {
          tooltip: { trigger: 'axis' },
          grid: { left: '3%', right: '4%', bottom: '3%', containLabel: true },
          xAxis: { type: 'category', boundaryGap: false, data: [] },
          yAxis: { type: 'value', name: '온도' },
          series: [{
            name: msg.name,
            type: 'line',
            data: [],
            // areaStyle: {} // 영역 채우기
          }]
        }

      }
      else if (msg.type === 3) {
        // type 3: 밀도 → Liquid Fill
        charts[msg.name] = {
          tooltip: { show: true },
          series: [
            {
              type: 'liquidFill',
              data: [msg.sensor_value / 100], // 0~1 사이 값
              name: msg.name,
              radius: '80%',                 // 차트 크기
              label: {
                fontSize: 20,
                formatter: function() {
                  return msg.sensor_value + '%'; // 센서 값 표시
                }
              }
            }
          ]
        }
      }else if (msg.type === 4) {
        // type 4: progress → bar chart
        charts[msg.name] = {
          tooltip: { formatter: '{b}: {c}%' },
          xAxis: {
            type: 'value',
            max: 100
          },
          yAxis: {
            type: 'category',
            data: [msg.name],
            inverse: true
          },
          series: [
            {
              type: 'bar',
              data: [msg.sensor_value],
              barWidth: 30,
              label: {
                show: true,
                position: 'right',
                formatter: '{c}%'
              },
              itemStyle: {
                color: '#4caf50'
              }
            }
          ]
        }
      }

      else {
        // type 2,3,4: 라인 차트
        charts[msg.name] = {
          tooltip: { trigger: 'axis' },
          grid: { left: '3%', right: '4%', bottom: '3%', containLabel: true },
          xAxis: { type: 'category', boundaryGap: false, data: [] },
          yAxis: { type: 'value', name:  '온도(℃)' },
          series: [{ name: msg.name, type: 'line', data: [] }]
        }
      }
    }

    // 데이터 업데이트
    if (msg.type === 1) {
      charts[msg.name].series[0].data[0].value = msg.sensor_value
    }
    else if (msg.type === 3) {
      charts[msg.name].series[0].data[0] = msg.sensor_value / 100
      charts[msg.name].series[0].label.formatter = () => msg.sensor_value + '%'
    }
    else if (msg.type === 4) {
      const series = charts[msg.name].series[0]
      series.data[0] = msg.sensor_value
      series.label = {
        show: true,
        position: 'right',
        formatter: msg.sensor_value + '%'
      }
    } else {
      charts[msg.name].xAxis.data.push(time)
      charts[msg.name].series[0].data.push(msg.sensor_value)
      if (charts[msg.name].xAxis.data.length > 20) {
        charts[msg.name].xAxis.data.shift()
        charts[msg.name].series[0].data.shift()
      }
    }
  }
})
</script>

<style scoped>
.chart-container {
  margin-bottom: 30px;
}
.chart {
  height: 300px;
  width: 100%;
}
.charts-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
  padding: 16px;
}
</style>
