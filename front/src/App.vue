<script setup>
import { ref } from 'vue'
import WorkOrderStats from '@/components/WorkOrderStats.vue'
import WorkOrderList from '@/components/WorkOrderList.vue'
import CreateWorkOrderDialog from '@/components/CreateWorkOrderDialog.vue'
import { Factory, Plus, ClipboardList, TrendingUp } from 'lucide-vue-next'

// Role-based access control could be handled through a composable
const userHasAccess = ref(true) // Replace with actual auth logic
</script>

<template>
  <div v-if="userHasAccess" class="flex min-h-screen">
    <main class="flex-1">
      <div class="min-h-screen bg-background">
        <!-- Header -->
        <header class="border-b bg-card">
          <div class="container mx-auto px-4 py-4">
            <div class="flex items-center justify-between">
              <div class="flex items-center gap-3">
                <Factory class="h-8 w-8 text-primary" />
                <div>
                  <h1 class="text-2xl font-bold text-foreground">Automotive MES</h1>
                  <p class="text-sm text-muted-foreground">Manufacturing Execution System</p>
                </div>
              </div>
              <CreateWorkOrderDialog>
                <button class="btn gap-2">
                  <Plus class="h-4 w-4" />
                  New Work Order
                </button>
              </CreateWorkOrderDialog>
            </div>
          </div>
        </header>

        <!-- Main Content -->
        <div class="container mx-auto px-4 py-6 space-y-6">
          <!-- Stats Overview -->
          <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
            <Suspense>
              <template #default>
                <WorkOrderStats />
              </template>
              <template #fallback>
                <StatsCardSkeleton />
              </template>
            </Suspense>
          </div>

          <!-- Work Orders Section -->
          <div class="card">
            <div class="card-header">
              <div class="flex items-center justify-between">
                <div class="flex items-center gap-2">
                  <ClipboardList class="h-5 w-5 text-primary" />
                  <h3 class="card-title">Work Orders</h3>
                </div>
                <div class="flex items-center gap-2 text-sm text-muted-foreground">
                  <TrendingUp class="h-4 w-4" />
                  Real-time Production Status
                </div>
              </div>
            </div>
            <div class="card-content">
              <Suspense>
                <template #default>
                  <WorkOrderList />
                </template>
                <template #fallback>
                  <WorkOrderListSkeleton />
                </template>
              </Suspense>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
// Skeleton components
const StatsCardSkeleton = {
  template: `
    <div class="card">
      <div class="card-header pb-2">
        <div class="h-4 bg-muted rounded animate-pulse" />
      </div>
      <div class="card-content">
        <div class="h-8 bg-muted rounded animate-pulse mb-2" />
        <div class="h-3 bg-muted rounded animate-pulse w-2/3" />
      </div>
    </div>
  `
}

const WorkOrderListSkeleton = {
  template: `
    <div class="space-y-3">
      <div v-for="i in 5" :key="i" class="h-16 bg-muted rounded animate-pulse" />
    </div>
  `
}
</script>

<style scoped>

</style>
