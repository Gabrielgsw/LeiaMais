<script setup>
import { RouterLink } from 'vue-router';
import { ref } from 'vue'

import Turma from '../components/Turma.vue'

const turmas = ref([
    {id: 1 , nome:'1º ano - Ensino Fundamental'},
    {id: 2 , nome:'2º ano - Ensino Fundamental'},
    {id: 3 , nome:'3º ano - Ensino Fundamental'},
])

const livros = ref([
    'https://covers.openlibrary.org/b/isbn/9788562936524-M.jpg',
    'https://covers.openlibrary.org/b/isbn/9788544102930-M.jpg',
    'https://covers.openlibrary.org/b/isbn/9788544101636-M.jpg',
])

</script>

<!--<div class="min-h-[90vh] bg-blue-100">
        <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
            <RouterLink to="/InicialSystem">
                <div class="text-5xl flex ">
                    <h1 class="">Leia+</h1>
                    <img src="../assets/capivara.svg" alt="">
                </div>
            </RouterLink>

            <div class="text-3xl flex gap-5">
                <nav class="text-center ">
                    <a href="#" class="no-underline pt-0 pr-[50px] pb-[10px] pl-0 text-[16pt] relative group">
                        Espaço educacional
                        <span
                            class="absolute bottom-0 left-0 w-0 h-[2px] bg-white rounded-full transition-all duration-300 ease-in-out group-hover:w-[80%]"></span>
                    </a>

                    <a href="#" class="no-underline pt-0 pr-[50px] pb-[10px] pl-0 text-[16pt] relative group">
                        Minhas atividades
                        <span
                            class="absolute bottom-0 left-0 w-[0px] h-[2px] bg-white rounded-full transition-all duration-300 ease-in-out group-hover:w-[75%]"></span>
                    </a>
                </nav>
            </div>
        </header>


        <main class="p-6">
            <div class="bg-white p-6 rounded-lg shadow-md flex items-center gap-4">
                <div class="w-16 h-16 relative rounded-full overflow-hidden">
                    <img src="../assets/Capivara.png" alt="Fundo" class="w-full h-full object-cover" />
                    <div class="absolute inset-0 bg-black mask mask-custom"
                        style="mask-image: url('../assets/icone-edit.svg'); mask-repeat: no-repeat; mask-position: center; mask-size: 1.5rem;">
                    </div>
                </div>
                <div>
                    <h2 class="text-lg font-bold text-blue-600">Olá, Aécio</h2>
                    <p>Você leu <strong>X</strong> livros este mês<br />Turma: 3º ano B</p>
                </div>
            </div>


            <section class="mt-6">
                <h3 class="font-bold text-lg mb-2">Amigos</h3>
                <div class="bg-blue-200 p-4 rounded-lg overflow-x-auto whitespace-nowrap flex gap-4">
                    <template v-for="n in 10" :key="n">
                        <div
                            class="w-12 h-12 rounded-full bg-white inline-flex items-center justify-center overflow-hidden">
                            <img src="../assets/capivara-icone-amigo.png" alt="Amigo" class="w-10 h-10 object-cover" />
                        </div>
                    </template>
</div>
<div class="text-right mt-1">
    <button class="text-xs text-blue-600 hover:underline">Ver mais</button>
</div>
</section>


<section class="mt-6">
    <h3 class="font-bold text-lg mb-2">Últimas leituras</h3>
    <div class="bg-blue-200 p-4 rounded-lg overflow-x-auto whitespace-nowrap flex gap-4">
        <template v-for="n in 6" :key="'livro-' + n">
                        <img src="../assets/livros-teste/gameofthrones.webp" alt="Capa do livro"
                            class="h-48 rounded shadow" />
                    </template>
    </div>
    <div class="text-right mt-1">
        <button class="text-xs text-blue-600 hover:underline">Ver tudo</button>
    </div>
</section>
</main>
</div>-->
<template>
    <div class="min-h-screen bg-[#e6f7fa] font-sans">
        <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
            <RouterLink to="/TelaInicial">
                <div class="text-5xl flex ">
                    <h1>Leia+</h1>
                    <img src="../assets/capivara.svg" alt="" />
                </div>
            </RouterLink>
        </header>

        <main class="p-12">
            <div class="bg-white rounded-lg shadow-md p-6">

                <div class="flex items-center gap-4 mb-6">
                    <div class="w-20 h-20 bg-gray-300 rounded-full">
                        <img src="../assets/Capivara.png" alt="Fundo" class="w-full h-full object-cover" />
                    </div>
                    <div>
                        <p class="text-blue-600 font-bold text-lg">Olá, Coordenador</p>
                        <p class="text-gray-600">Escola: Educandário São Judas Tadeu</p>
                    </div>
                </div>


                <div class="flex gap-4 mb-6">
                    <router-link to="/TelaDeLivrosCadastrados">
                        <button class="bg-[#359DFF] text-white px-4 py-2 rounded hover:bg-blue-600">Adicionar
                            Livros</button>
                    </router-link>
                    <router-link to="/TelaDeUsuariosCadastrados"><button
                            class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600">Adicionar
                            usuários</button>
                    </router-link>
                    <button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600">Adicionar
                        turma</button>
                </div>

                <!--:to="`/turma/${turma.id}`" colocar quando tiver pronto-->
                <div class="space-y-4 mb-8">
                    <router-link v-for="turma in turmas" to="/TelaDeTurma" class="block">
                        <Turma :nome="turma.nome" />
                    </router-link>
                    
                </div>


                <div>
                    <h2 class="text-lg font-bold mb-4">Biblioteca Geral:</h2>
                    <div class="flex overflow-x-auto gap-4 bg-blue-100 p-4 rounded">
                        <img v-for="livro in livros" :src="livro" class="w-[160px] h-[230px] rounded-sm object-cover" />
                    </div>
                </div>
            </div>
        </main>
    </div>
</template>



<style scoped></style>