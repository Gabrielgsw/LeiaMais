<!-- src/components/ComboBox.vue -->
<template>
  <div class="w-full relative">
    <input
      v-model="input"
      @focus="open = true"
      @input="onInput"
      @keydown.down.prevent="highlightNext"
      @keydown.up.prevent="highlightPrev"
      @keydown.enter.prevent="selectHighlighted"
      @blur="closeDropdown"
      type="text"
      class="w-full p-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
      :placeholder="placeholder"
    />

    <ul
      v-if="open && filteredOptions.length"
      class="absolute z-10 mt-1 w-full bg-white border rounded shadow max-h-60 overflow-auto"
    >
      <li
        v-for="(option, index) in filteredOptions"
        :key="option"
        @mousedown.prevent="selectOption(option)"
        :class="[
          'p-2 cursor-pointer',
          index === highlightedIndex ? 'bg-blue-100' : 'hover:bg-blue-50'
        ]"
      >
        {{ option }}
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue'

const props = defineProps({
  options: { type: Array, required: true },
  modelValue: String,
  placeholder: { type: String, default: 'Selecione ou digite...' },
})

const emit = defineEmits(['update:modelValue'])

const input = ref(props.modelValue || '')
const open = ref(false)
const highlightedIndex = ref(-1)

const filteredOptions = computed(() => {
  return props.options.filter(option =>
    option.toLowerCase().includes(input.value.toLowerCase())
  )
})

function selectOption(option) {
  input.value = option
  emit('update:modelValue', option)
  open.value = false
  highlightedIndex.value = -1
}

function onInput() {
  open.value = true
  highlightedIndex.value = -1
  emit('update:modelValue', input.value)
}

function closeDropdown() {
  setTimeout(() => {
    open.value = false
    highlightedIndex.value = -1
  }, 100)
}

function highlightNext() {
  if (!filteredOptions.value.length) return
  highlightedIndex.value = (highlightedIndex.value + 1) % filteredOptions.value.length
}

function highlightPrev() {
  if (!filteredOptions.value.length) return
  highlightedIndex.value =
    (highlightedIndex.value - 1 + filteredOptions.value.length) % filteredOptions.value.length
}

function selectHighlighted() {
  if (highlightedIndex.value >= 0) {
    selectOption(filteredOptions.value[highlightedIndex.value])
  }
}

watch(() => props.modelValue, val => {
  input.value = val || ''
})
</script>
