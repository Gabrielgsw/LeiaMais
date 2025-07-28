<script setup lang="ts">
import { useRoute } from 'vue-router'
import { ref } from 'vue'
import Rating from 'primevue/rating'
import Select from 'primevue/select'

const route = useRoute()
const isbn = route.params.isbn as string

function abrirPdf(isbn: string) {
    const url = `http://localhost:8080/livros/${isbn}/pdf`
    window.open(url, '_blank')
}

const capaUrl = `https://covers.openlibrary.org/b/isbn/${isbn}-M.jpg`

const value = ref(0)
const selectedStatus = ref()
const options = ref([
    { name: 'Não iniciado', code: 'NY' },
    { name: 'Em leitura', code: 'RM' },
    { name: 'Concluído', code: 'LDN' }
])
</script>

<template>
    <div class="min-h-screen bg-[#e6f7fa] font-sans">
        <header class="text-white bg-[#0f8ebd] flex justify-around py-3">
            <RouterLink to="/TelaInicial">
                <div class="text-5xl flex ">
                    <h1>Leia+</h1>
                    <img src="../../assets/capivara.svg" alt="" />
                </div>
            </RouterLink>
        </header>

        <div class="flex flex-row max-w-5xl mx-auto bg-white rounded-2xl p-6 mt-16">
            <div class="mr-10 flex-shrink-0">
                <img class="rounded-2xl w-52 h-auto" :src="capaUrl" alt="Capa do livro" />
            </div>
            <div class="flex-1 gap-1">
                <div class="flex gap-5 align-middle items-center">
                    <RouterLink to="/TelaInicialProfessor">
                        <img src="../../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8" />
                    </RouterLink>
                    <h1 class="text-2xl font-bold text-[28px]">Título do Livro</h1>
                </div>

                <p class="mt-2">
                    Este é um exemplo de descrição do livro. Você pode carregar isso do backend no futuro.
                </p>

                <div class="flex gap-10 items-center mt-4">
                    <button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600"
                        @click="abrirPdf(isbn)">
                        Ler agora
                    </button>

                    <Rating v-model="value" />
                    <Select v-model="selectedStatus" :options="options" optionLabel="name" placeholder="Status do livro"
                        class="w-full md:w-56" />
                    


                </div>
            </div>
        </div>
    </div>
</template>