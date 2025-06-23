<script setup>
import { ref, computed } from 'vue'
import UsuarioRow from '../components/UsuarioRow.vue'
import { RouterLink } from 'vue-router'
import { Search } from 'lucide-vue-next'
import axios from 'axios';
import { useForm } from 'vee-validate'
import Datepicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'


import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogFooter,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
} from "@/components/ui/dialog";
import {
    FormControl,
    FormDescription,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from '@/components/ui/form'

const placeholder = ref()

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
const date = ref(null)

// testando ainda o cadastro de usuário na API
const cadastrarUsuario = () => {
    const usuario = {
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
const isDialogOpen = ref(false);
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
                    <div
                        class="w-64 pe-2 bg-[#F5F7FA] rounded-md border border-gray-300 flex items-center focus-within:border-blue-600 ">
                        <div class="px-2">
                            <Search class="size-4 text-muted-foreground" />
                        </div>
                        <input v-model="filtro" type="text" class="w-full focus:outline-none py-2"
                            placeholder="Pesquisar usuário...">
                    </div>
                    <Dialog v-model:open="isDialogOpen">
                        <DialogTrigger as-child>
                            <button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600">
                                Cadastrar usuário
                            </button>
                        </DialogTrigger>
                        <DialogContent>
                            <form class=" space-y-6" @submit="onSubmit">
                                <FormField v-slot="{ componentField }" name="username"
                                    :validate-on-blur="!isFieldDirty">
                                    <DialogHeader>
                                        <DialogTitle>
                                            <h1 class="text-[#0084FF] font-bold text-[28px] mt-2">
                                                Cadastrar usuário
                                            </h1>
                                        </DialogTitle>
                                    </DialogHeader>
                                    <FormItem>
                                        <FormLabel>Nome <span class="text-red-500 font-bold">*</span></FormLabel>
                                        <FormControl>
                                            <Input id="nome"
                                                class="col-span-4 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75"
                                                placeholder="Nome do usuário" />
                                        </FormControl>
                                    </FormItem>
                                    <FormItem>
                                        <FormLabel>E-mail <span class="text-red-500 font-bold">*</span></FormLabel>
                                        <FormControl>
                                            <Input id="email"
                                                class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75"
                                                placeholder="Digite o email" />
                                        </FormControl>
                                    </FormItem>
                                    <FormItem>
                                        <FormLabel>Senha <span class="text-red-500 font-bold">*</span></FormLabel>
                                        <FormControl>
                                            <Input id="senha"
                                                class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75"
                                                placeholder="Digite a senha" />
                                        </FormControl>
                                    </FormItem>
                                    <FormItem>
                                        <FormLabel>CPF <span class="text-red-500 font-bold">*</span></FormLabel>
                                        <FormControl>
                                            <Input id="cpf"
                                                class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75"
                                                placeholder="Digite o CPF" />
                                        </FormControl>
                                    </FormItem>
                                    <FormItem>
                                        <FormLabel>Data de nascimento <span class="text-red-500 font-bold">*</span>
                                        </FormLabel>
                                        <FormControl>

                                            <Datepicker v-model="date" :max-date="new Date()" :format="'dd/MM/yyyy'" 
                                                placeholder="Selecione a data"
                                                class=""
                                                />

                                        </FormControl>
                                    </FormItem>


                                </FormField>

                            </form>
                            <div class="flex items-center justify-between mt-4">
                                <button
                                    class="bg-white text-[#359DFF] px-4 py-2 rounded shadow ring-1 ring-[#359DFF] hover:bg-black/5 transition-colors"
                                    @click="isDialogOpen = false"> Cancelar
                                </button>
                                <Button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600"
                                    @click="$emit('deleteUser', id)">
                                    Cadastrar
                                </Button>
                            </div>
                        </DialogContent>
                    </Dialog>


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
