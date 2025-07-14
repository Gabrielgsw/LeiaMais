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
                <img class="rounded-2xl w-52 h-auto" src="../assets/livros-teste/gameofthrones.webp" alt="">
            </div>
            <div class="flex-1 gap-1">
                <h1 class="text-2xl font-bold text-[28px]">Game of thrones - livro um</h1>
                <p>"A Guerra dos Tronos", primeiro livro da série "As Crônicas de Gelo e Fogo", de George R. R. Martin,
                    narra o início de uma intrincada disputa pelo poder nos Sete Reinos de Westeros. O livro apresenta a
                    chegada do Rei Robert Baratheon a Winterfell, convidando Eddard Stark para ser sua Mão do Rei,
                    desencadeando uma série de eventos que mergulham a região em uma guerra civil. Paralelamente,
                    Daenerys Targaryen, exilada, planeja sua vingança e a retomada do Trono de Ferro, enquanto no
                    extremo norte, Jon Snow enfrenta perigos além da Muralha. </p>

                <div class="flex  gap-10 items-center mt-4">
                    <button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600"
                        @click="abrirPdf('23487234')">
                        Ler agora
                    </button>
                    
                        <Rating v-model="value" />
                        <Select v-model="selectedStatus" :options="options" optionLabel="name"
                            placeholder="Status do livro" class="w-full md:w-56"  />

                </div>

            </div>

        </div>
    </div>
</template>

<style scoped></style>