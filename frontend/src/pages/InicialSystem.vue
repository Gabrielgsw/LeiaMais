<script setup>
import { RouterLink } from 'vue-router';
import { ref } from 'vue'


</script>

<template>
    <div class="min-h-screen bg-blue-100">
        <!-- Header -->
        <header class="bg-sky-600 text-white px-6 py-4 flex justify-between items-center">
            <div class="text-5xl flex ">
                <h1 class="">Leia+</h1>
                <img src="../assets/capivara.svg" alt="">
            </div>
            <nav class="space-x-6 text-sm">
                <a href="#" class="hover:underline">Meu perfil</a>
                <a href="#" class="hover:underline">Espaço educacional</a>
                <a href="#" class="hover:underline">Minhas atividades</a>
            </nav>
        </header>

        <!-- Conteúdo principal -->
        <main class="p-6">
            <!-- Saudação -->
            <div class="bg-white p-6 rounded-lg shadow-md flex items-center gap-4">
                <div class="w-16 h-16 rounded-full bg-gray-200 flex items-center justify-center">
                    <img src="../assets/icone-edit.svg" alt="Editar" class="w-6 h-6" />
                </div>
                <div>
                    <h2 class="text-lg font-bold text-blue-600">Olá, Aécio</h2>
                    <p>Você leu <strong>X</strong> livros este mês<br />Turma: 3º ano B</p>
                </div>
            </div>

            <!-- Amigos -->
            <section class="mt-6">
                <h3 class="font-bold text-lg mb-2">Amigos</h3>
                <div class="bg-blue-200 p-4 rounded-lg overflow-x-auto whitespace-nowrap flex gap-4">
                    <template v-for="n in 10" :key="n">
                        <div class="w-12 h-12 rounded-full bg-white inline-block"></div>
                    </template>
                </div>
                <div class="text-right mt-1">
                    <button class="text-xs text-blue-600 hover:underline">Ver mais</button>
                </div>
            </section>

            <!-- Últimas leituras -->
            <section class="mt-6">
                <h3 class="font-bold text-lg mb-2">Últimas leituras</h3>
                <div class="bg-blue-200 p-4 rounded-lg overflow-x-auto whitespace-nowrap flex gap-4">
                    <template v-for="n in 6" :key="'livro-' + n">
                        <img src="https://m.media-amazon.com/images/I/71pcJnYvDAL._AC_UF1000,1000_QL80_.jpg"
                            alt="Capa do livro" class="h-48 rounded shadow" />
                    </template>
                </div>
            </section>
        </main>
    </div>
</template>


<style scoped></style>