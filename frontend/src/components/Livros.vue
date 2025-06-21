<script setup>
import { ref, computed } from 'vue'
import LivroRow from '../components/LivroRow.vue'
import ComboboxLivro from '../components/ComboboxLivro.vue'

const livros = ref([
    { titulo: 'Next.js', autor: 'Vercel', editora: 'Open Web' },
    { titulo: 'Remix', autor: 'Remix Team', editora: 'Remix Press' },
    { titulo: 'Nuxt', autor: 'Nuxt Labs', editora: 'Vue Books' },
    { titulo: 'Astro', autor: 'Fred K. Schott', editora: 'Astro Editions' },
])

const filtro = ref('')

const livrosFiltrados = computed(() => {
    if (!filtro.value) return livros.value
    return livros.value.filter(livro =>
        livro.titulo.toLowerCase().includes(filtro.value.toLowerCase())
    )
})
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
                    
                    <ComboboxLivro @update="filtro = $event" />
                    <button class="bg-[#359DFF] text-white px-4 py-1 rounded hover:bg-blue-600">
                        Cadastrar livro
                    </button>
                </div>
            </div>

            <div class="grid grid-cols-4 font-bold px-4 py-2 bg-blue-100 rounded-md mb-2">
                <span>Título</span>
                <span>Autor</span>
                <span>Editora</span>
                <span class="text-center">Ações</span>
            </div>

            <LivroRow v-for="(livro, index) in livrosFiltrados" :key="index" :titulo="livro.titulo" :autor="livro.autor"
                :editora="livro.editora" />
        </div>
    </div>
</template>
