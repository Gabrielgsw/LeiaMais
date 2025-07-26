<script setup lang="ts">
import { ref } from 'vue';

const livros = ref([
    "https://covers.openlibrary.org/b/isbn/9788522005239-M.jpg",
    "https://covers.openlibrary.org/b/isbn/9781421806501-M.jpg",
    "https://covers.openlibrary.org/b/isbn/9780316183567-M.jpg",
])
</script>

<template>
    <div class="min-h-screen bg-[#e6f7fa] font-sans">
        <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
            <RouterLink to="/TelaInicialAluno">
                <div class="text-5xl flex ">
                    <h1>Leia+</h1>
                    <img src="../assets/capivara.svg" alt="" />
                </div>
            </RouterLink>
        </header>
        <div class="max-w-5xl mx-auto bg-white shadow-md rounded-lg p-6 mt-16">
            <div class="flex items-center justify-between mb-6">
                <div class="flex items-center gap-3">
                    <RouterLink to="/TelaInicialAluno">
                        <img src="../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8" />
                    </RouterLink>
                    
                    <h2 class="text-[28px] font-bold">Biblioteca de livros</h2>
                </div>
            </div>
            <div
                class="bg-blue-100 w-full max-h-[600px] p-4 rounded overflow-y-auto grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-4">
                <img v-for="(livro, index) in livros" :key="index" :src="livro"
                    class="w-full h-[230px] rounded-sm object-cover" />
            </div>


        </div>
    </div>

</template>