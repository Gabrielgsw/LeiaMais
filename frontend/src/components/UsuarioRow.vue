<script setup>
import { defineProps, defineEmits } from 'vue';

defineProps(['id', 'nome', 'cpf', 'tipo']);


const emit = defineEmits(['deleteUser']); 
</script>

<template>
    <div class="grid grid-cols-4 items-center px-4 py-2 bg-blue-100 mb-2 rounded-md">
        <span>{{ nome }}</span>
        <span>{{ cpf }}</span>
        <span>{{ tipo }}</span>
        <span class="flex justify-center gap-3">
            <button><img src="../assets/botoes/botao_editar.svg" alt="Editar" /></button>
            <button @click="emit('deleteUser', id)">
                <img src="/src/assets/botoes/botao_excluir.svg" alt="Excluir">
            </button>
        </span>
    </div>
</template>