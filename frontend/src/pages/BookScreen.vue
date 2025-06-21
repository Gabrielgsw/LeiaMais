
<script setup>
import LivroRow from '../components/LivroRow.vue'
</script>
<template>
    <div class="min-h-screen bg-[#e6f7fa] font-sans">
        <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
            <RouterLink to="/">
                <div class="text-5xl flex ">
                    <h1>Leia+</h1>
                    <img src="../assets/capivara.svg" alt="" />
                </div>
            </RouterLink>
        </header>


        <div class="max-w-5xl mx-auto bg-white shadow-md rounded-lg p-6 mt-16">
            <div class="flex items-center justify-between mb-6">
                <div class="flex items-center gap-3">
                    <button @click="$router.back()" class="text-2xl">
                        <img src="../assets/botoes/botao_voltar.svg" alt="Voltar" />
                    </button>
                    <h2 class="text-2xl font-bold">Livros</h2>
                </div>
                <div class="flex gap-2">
                    <input type="text" placeholder="🔍 Buscar..." class="border border-gray-300 rounded px-3 py-1" />
                    <button class="bg-[#359DFF] text-white px-4 py-1 rounded hover:bg-blue-600">Cadastrar livro</button>
                </div>
            </div>

            <div class="grid grid-cols-4 font-bold px-4 py-2 bg-blue-100 rounded-md mb-2">
                <span>Título</span>
                <span>Autor</span>
                <span>Editora</span>
                <span class="text-center">Ações</span>
            </div>

            <LivroRow v-for="n in 6" :key="n" titulo="Harry potter e a pedra filosofal" autor="Algum autor ai"
                editora="Alguma ai" />
        </div>
    </div>
</template>
