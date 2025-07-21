<script setup lang="ts">
import { RouterLink } from 'vue-router'
import { ref } from 'vue'
import ListaSuspensa from '@/components/ListaSuspensa.vue';
const props = defineProps(['livro']);
import Rating from 'primevue/rating';
import Select from 'primevue/select';

function abrirPdf(isbn) {
    const url = `http://localhost:8080/livros/${isbn}/pdf`;
    window.open(url, '_blank');
}

const value = ref(0);
const selectedStatus = ref()
const options = ref([
    { name: 'Não iniciado', code: 'NY' },
    { name: 'Em leitura', code: 'RM' },
    { name: 'Concluído', code: 'LDN' }
]);
</script>


<template>
    <div class="min-h-screen bg-[#e6f7fa] font-sans">
        <header class="text-white bg-[#0f8ebd] flex justify-around py-3">
            <RouterLink to="/TelaInicial">
                <div class="text-5xl flex ">
                    <h1>Leia+</h1>
                    <img src="../assets/capivara.svg" alt="" />
                </div>
            </RouterLink>
        </header>
        <div class="flex flex-row max-w-5xl mx-auto bg-white rounded-2xl p-6 mt-16">
            <div class="mr-10 flex-shrink-0">
                <img class="rounded-2xl w-52 h-auto" src="../assets/livros-teste/O pequeno príncipe.jpg" alt="">
            </div>
            <div class="flex-1 gap-1">
                <h1 class="text-2xl font-bold text-[28px]">O pequeno príncipe</h1>
                <p>conta a história de um piloto que cai com seu avião no deserto do Saara e conhece um menino loiro,
                    vindo de um pequeno planeta distante, o asteroide B-612. O menino, conhecido como o Pequeno
                    Príncipe, narra suas viagens por outros planetas, onde encontra personagens excêntricos que
                    representam diferentes aspectos da vida adulta, como a vaidade, a ambição e a solidão. Ao chegar à
                    Terra, o Pequeno Príncipe faz amizade com uma raposa, que lhe ensina sobre o valor da amizade e do
                    amor, e a importância de ver com o coração, não apenas com os olhos. </p>

                <div class="flex  gap-10 items-center mt-4">
                    <button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600"
                        @click="abrirPdf('9788522005239')">
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

<style scoped></style>