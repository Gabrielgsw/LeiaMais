<script setup lang="ts">
import { ref } from 'vue'
import { Check, Search } from 'lucide-vue-next'
import { cn } from '../lib/utils' 
import {
    Combobox,
    ComboboxAnchor,
    ComboboxEmpty,
    ComboboxGroup,
    ComboboxInput,
    ComboboxItem,
    ComboboxItemIndicator,
    ComboboxList
} from '@/components/ui/combobox'

const emit = defineEmits(['update'])


const usuarios = [
    { value: 'joao-silva', label: 'João Silva' },
    { value: 'maria-souza', label: 'Maria Souza' },
    { value: 'pedro-santos', label: 'Pedro Santos' },
    { value: 'ana-costa', label: 'Ana Costa' },
    { value: 'carlos-pereira', label: 'Carlos Pereira' },
]

const selected = ref(null)
</script>

<template>
    <Combobox v-model="selected" by="label" @update:model-value="(val) => emit('update', val?.label ?? '')">
        <ComboboxAnchor>
            <div class="relative w-full max-w-sm items-center">
                <ComboboxInput
                    class="w-full rounded-md border border-gray-300 bg-[#F5F7FA] pl-10 pr-2 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                    :display-value="(val) => val?.label ?? ''" placeholder="Buscar usuário..." /> <span class="absolute start-0 inset-y-0 flex items-center justify-center px-3">
                    <Search class="size-4 text-muted-foreground" />
                </span>
            </div>
        </ComboboxAnchor>

        <ComboboxList class="mt-2 w-64 rounded-md border border-gray-200 bg-white shadow-lg z-50">
            <ComboboxEmpty class="px-4 py-2 text-sm text-gray-500">Nenhum usuário encontrado.</ComboboxEmpty> <ComboboxGroup>
                <ComboboxItem v-for="usuario in usuarios" :key="usuario.value" :value="usuario"
                    class="px-4 py-2 hover:bg-blue-100 cursor-pointer flex items-center justify-between">
                    {{ usuario.label }}
                    <ComboboxItemIndicator>
                        <Check :class="cn('ml-2 h-4 w-4 text-blue-500')" />
                    </ComboboxItemIndicator>
                </ComboboxItem>
            </ComboboxGroup>
        </ComboboxList>
    </Combobox>
</template>