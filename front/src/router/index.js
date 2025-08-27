import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/worksheet',
      name: 'worksheet',

      component: () => import('../views/WorksheetView.vue'),
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/mes/:machineId',
      name: 'MES',
      component: () => import('../components/common/MesDashboard.vue'),
      meta: { layout: 'default' }    // Header/Sidebar 없는 레이아웃
    },
    {
      path: '/mes/machine',
      name: 'machine',
      component: () => import('../components/common/MachineView.vue'),
      meta: { layout: 'default' }    // Header/Sidebar 없는 레이아웃
    },
  ],
})

export default router
