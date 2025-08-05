<template>
  <div class="bg-blue-100 flex justify-between items-center px-4 py-2 rounded shadow">
    <span class="font-semibold text-gray-800">{{ nome }}</span>
    <span class="text-xl">🏆</span>
  </div>
</template>

<script setup>
defineProps(['nome'])
</script>
