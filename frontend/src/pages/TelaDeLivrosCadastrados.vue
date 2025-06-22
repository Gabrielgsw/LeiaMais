<script setup>
import LivroRow from '../components/LivroRow.vue'
import { ref, computed } from 'vue'
import { Search } from 'lucide-vue-next'

const livros = ref([
    { titulo: '1984', autor: 'George Orwell', editora: 'Companhia das Letras' },
    { titulo: 'O Pequeno Príncipe', autor: 'Antoine de Saint-Exupéry', editora: 'Agir' },
    { titulo: 'Orgulho e Preconceito', autor: 'Jane Austen', editora: 'Martin Claret' },
    { titulo: 'O Senhor dos Anéis', autor: 'J.R.R. Tolkien', editora: 'HarperCollins Brasil' },
])

const filtro = ref('')

const livrosFiltrados = computed(() => {
    if (!filtro.value) return livros.value
    return livros.value.filter((livro) =>
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
                        <img src="../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8"/>
                    </button>
                    <h2 class="text-2xl font-bold text-[28px] ">Livros</h2>
                </div>

                <div class="flex gap-2">
                    <div class="w-64 pe-2 bg-[#F5F7FA] rounded-md border border-gray-300 flex items-center focus-within:border-blue-600 ">
                        <div class="px-2">
                            <Search class="size-4 text-muted-foreground" />      
                        </div>
                            <input v-model="filtro" type="text" class="w-full focus:outline-none py-2" placeholder="Pesquisar livro...">
                    </div>
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

                <div v-if="livrosFiltrados.length === 0" class="text-center text-gray-500 mt-4">
                    Nenhum livro encontrado.
                </div>
        </div>
    </div>
</template>
