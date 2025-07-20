<script setup>
import { RouterLink, useRouter } from 'vue-router';
import { ref } from 'vue'

import Turma from '../../components/Turma.vue'
import axios from "axios";

const router = useRouter();

const turmas = ref([
    { id: 1, nome: '1º ano - Ensino Fundamental' },
])

const livros = ref([
    'https://covers.openlibrary.org/b/isbn/9788562936524-M.jpg',
    'https://covers.openlibrary.org/b/isbn/9788544102930-M.jpg',
    'https://covers.openlibrary.org/b/isbn/9788544101636-M.jpg',
])

const handleLogout = async () => {
    try {
        const response = await axios.post('http://localhost:8080/api/auth/logout', {}, { withCredentials: true });
        if (response.status === 200) {
            alert("Logout realizado com sucesso.");
            router.push('/');
        }
    } catch (error) {
        console.error("Erro no logout:", error);
    }
}
</script>

<template>
    <div class="min-h-screen bg-[#e6f7fa] font-sans">
        <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
            <RouterLink to="/TelaInicialProfessor">
                <div class="text-5xl flex ">
                    <h1>Leia+</h1>
                    <img src="../../assets/capivara.svg" alt="" />
                </div>
            </RouterLink>
        </header>

        <main class="p-12">
            <div class="bg-white rounded-lg shadow-md p-6">
                
                <div class="flex items-center gap-4 mb-6">
                    <div class="w-20 h-20 bg-gray-300 rounded-full">
                        <img src="../../assets/capivara.png" alt="Fundo" class="w-full h-full object-cover" />
                    </div>
                    <div>
                        <p class="text-blue-600 font-bold text-lg">Olá, Professor</p>
                        <p class="text-gray-600">Escola: Educandário São Judas Tadeu</p>
                    </div>
                </div>

                <div class="flex gap-4 mb-6">
                    <router-link to="/TeladeRanking">
                        <button
                            class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600">Ranking geral</button>
                    </router-link>
                    
                    <button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600"
                        @click="handleLogout">Sair da conta</button>
                </div>

                <div class="space-y-4 mb-8">
                    <router-link v-for="turma in turmas" to="/TelaDeTurmaProfessor" class="block">
                        <Turma :nome="turma.nome" />
                    </router-link>
                </div>

                <div>
                    <h2 class="text-lg font-bold mb-4">Biblioteca Geral:</h2>
                    <RouterLink to="/TeladeLivroGeral">
                        <div class="flex overflow-x-auto gap-4 bg-blue-100 p-4 rounded">
                            <img v-for="livro in livros" :src="livro"
                                class="w-[160px] h-[230px] rounded-sm object-cover" />
                        </div>
                    </RouterLink>
                </div>
            </div>
        </main>
    </div>
</template>

<style scoped></style>