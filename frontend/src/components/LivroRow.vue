<script setup>
defineProps(['titulo', 'autor', 'editora'])
</script>
<template>
    <div class="grid grid-cols-4 items-center px-4 py-2 bg-blue-100 mb-2 rounded-md">
        <span>{{ titulo }}</span>
        <span>{{ autor }}</span>
        <span>{{ editora }}</span>
        <span class="flex justify-center gap-3">
            <button><img src="../assets/botoes/botao_editar.svg" alt="Editar" /></button>
            <button><img src="../assets/botoes/botao_excluir.svg" alt="Excluir" /></button>
        </span>
    </div>
</template>


