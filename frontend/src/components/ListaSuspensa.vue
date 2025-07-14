<template>
  <div class="max-w-md p-4">
    <h2 class="text-xl font-semibold mb-4">Informações:</h2>
    <ul class="space-y-2">
      <li
        v-for="(item, index) in options"
        :key="index"
        class="bg-white rounded-xl shadow"
      >
        <button
          @click="toggle(item.label)"
          class="w-full text-left px-4 py-3 flex justify-between items-center hover:bg-blue-100 rounded-xl transition"
        >
          <span class="font-medium text-lg">{{ item.label }}</span>
          <svg
            class="w-5 h-5 transform transition-transform"
            :class="{ 'rotate-180': isOpen(item.label) }"
            fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24"
          >
            <path d="M19 9l-7 7-7-7" />
          </svg>
        </button>

        <div
          v-if="isOpen(item.label)"
          class="border-t border-gray-200 px-4 py-2 space-y-2 bg-gray-50 rounded-b-xl"
        >
          <button
            v-for="(sub, i) in item.subOptions"
            :key="i"
            @click="selectSubOption(item.label, sub)"
            class="block w-full text-left px-2 py-1 rounded hover:bg-blue-200 transition"
          >
            {{ sub }}
          </button>
        </div>
      </li>
    </ul>

    <div v-if="selectedOption" class="mt-4 p-4 bg-blue-50 rounded">
      Você selecionou:
      <strong>{{ selectedOption.main }} > {{ selectedOption.sub }}</strong>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const options = [
  { label: 'Status', subOptions: ['Não iniciado', 'Em leitura', 'Concluído'] },
  { label: 'Avaliação', subOptions: ['Água', 'Suco', 'Refrigerante'] },
]

const openItem = ref(null)
const selectedOption = ref(null)

function toggle(label) {
  openItem.value = openItem.value === label ? null : label
}

function isOpen(label) {
  return openItem.value === label
}

function selectSubOption(main, sub) {
  selectedOption.value = { main, sub }
}
</script>
