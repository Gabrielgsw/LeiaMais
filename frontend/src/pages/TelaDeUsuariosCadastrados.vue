<script setup>
import { ref, computed } from 'vue'
import UsuarioRow from '../components/UsuarioRow.vue'
import { RouterLink } from 'vue-router'
import { Search } from 'lucide-vue-next'


const usuarios = ref([
    { id: 1, nome: 'João Silva', cpf: '123.456.789-00', tipo: 'Aluno' },
    { id: 2, nome: 'Maria Souza', cpf: '987.654.321-11', tipo: 'Professor' },
    { id: 3, nome: 'Pedro Santos', cpf: '111.222.333-44', tipo: 'Administrador' },
    { id: 4, nome: 'Ana Costa', cpf: '555.666.777-88', tipo: 'Aluno' },
]);

const filtro = ref('');
const usuariosFiltrados = computed(() => {
    if (!filtro.value) return usuarios.value;
    return usuarios.value.filter(usuario =>
        usuario.nome.toLowerCase().includes(filtro.value.toLowerCase())
    );
}); 
 
// testando ainda o cadastro de usuário na API
const cadastrarUsuario = () => {
    const usuario ={
  cpf: '123.456.789-00',
  nome: 'Joãozinho',
  email: 'joaozinho@gmail.com',
  senha: 'senha123',
  cargo: 'ALUNO',
//   dataNascimento: null,
  matricula: "223",
}

    fetch('http://localhost:8080/alunos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(usuario)
    })
};

const handleDeleteUser = (userId) => { 
    // const confirmDelete = window.confirm(`Tem certeza que deseja excluir o usuário com ID: ${userId}?`);

    // if (confirmDelete) {
    //     usuarios.value = usuarios.value.filter(usuario => usuario.id !== userId);

    //     console.log(`Usuário com ID ${userId} excluído.`);
    // } else {
    //     console.log(`Exclusão do usuário com ID ${userId} cancelada.`);
    // }
    console.log(`Usuário com ID ${userId} excluído.`);
};

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
                        <img src="../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8" />
                    </button>
                    <h2 class="text-[28px] font-bold">Usuários</h2>
                </div>

                <div class="flex gap-2 ">
                    <div class="w-64 pe-2 bg-[#F5F7FA] rounded-md border border-gray-300 flex items-center focus-within:border-blue-600 ">
                        <div class="px-2">
                            <Search class="size-4 text-muted-foreground" />      
                        </div>
                            <input v-model="filtro" type="text" class="w-full focus:outline-none py-2" placeholder="Pesquisar usuário...">
                    </div>
                        
                    <button class="bg-[#359DFF] text-white px-4 py-1 rounded hover:bg-blue-600"
                    @click="cadastrarUsuario"
                    >
                        Cadastrar usuário
                    </button>
                </div>
            </div>

            <div class="grid grid-cols-4 font-bold px-4 py-2 bg-blue-100 rounded-md mb-2">
                <span>Nome</span> <span>CPF</span> <span>Tipo</span> <span class="text-center">Ações</span>
            </div>

            <UsuarioRow v-for="(usuario, index) in usuariosFiltrados" :key="index" :id="usuario.id" :nome="usuario.nome"
                :cpf="usuario.cpf" :tipo="usuario.tipo" @delete-user="handleDeleteUser" />

                <div v-if="usuariosFiltrados.length === 0" class="text-center text-gray-500 mt-4">
                    Nenhum usuário encontrado.
                </div>
        </div>
    </div>
</template>
