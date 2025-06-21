<script setup>
import { ref, computed } from 'vue'
import UsuarioRow from '../components/UsuarioRow.vue'
import ComboboxUsuario from '../components/ComboboxUsuario.vue' 
import { RouterLink } from 'vue-router' 


const usuarios = ref([
    { nome: 'João Silva', cpf: '123.456.789-00', tipo: 'Aluno' },
    { nome: 'Maria Souza', cpf: '987.654.321-11', tipo: 'Professor' },
    { nome: 'Pedro Santos', cpf: '111.222.333-44', tipo: 'Administrador' },
    { nome: 'Ana Costa', cpf: '555.666.777-88', tipo: 'Aluno' },
]);

const filtro = ref(''); 
const usuariosFiltrados = computed(() => {
    if (!filtro.value) return usuarios.value;
    return usuarios.value.filter(usuario =>
        usuario.nome.toLowerCase().includes(filtro.value.toLowerCase())
    );
}); 
</script>
<template>
    <div class="min-h-screen bg-[#e6f7fa] font-sans">
        <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
            <RouterLink to="/">
                <div class="text-5xl flex ">
                    <h1>Leia+</h1>
                    <img src="../assets/capivara.svg" alt=""/>
                </div>
            </RouterLink>
        </header>

        <div class="max-w-5xl mx-auto bg-white shadow-md rounded-lg p-6 mt-16">
            <div class="flex items-center justify-between mb-6">
                <div class="flex items-center gap-3">
                    <button @click="$router.back()" class="text-2xl">
                        <img src="../assets/botoes/botao_voltar.svg" alt="Voltar" />
                    </button>
                    <h2 class="text-2xl font-bold">Usuários</h2>
                </div>

                <div class="flex gap-2">
                    <ComboboxUsuario @update="filtro = $event" />
                    <button class="bg-[#359DFF] text-white px-4 py-1 rounded hover:bg-blue-600">
                        Cadastrar usuário
                    </button>
                </div>
            </div>

            <div class="grid grid-cols-4 font-bold px-4 py-2 bg-blue-100 rounded-md mb-2">
                <span>Nome</span> <span>CPF</span> <span>Tipo</span> <span class="text-center">Ações</span>
            </div>

            <UsuarioRow v-for="(usuario, index) in usuariosFiltrados" :key="index" :nome="usuario.nome"
                :cpf="usuario.cpf" :tipo="usuario.tipo" />
        </div>
    </div>
</template>

