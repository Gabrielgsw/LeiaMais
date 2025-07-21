<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import '@vuepic/vue-datepicker/dist/main.css'
import { RouterLink } from 'vue-router';
import axios from 'axios';
import { ScrollArea } from '@/components/ui/scroll-area'
import AtividadeRow from '@/components/AtividadeRow.vue';
import UsuarionaTurma from '@/components/UsuarionaTurma.vue';
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
const livros = ref([
    "https://covers.openlibrary.org/b/isbn/9788522005239-M.jpg",
    "https://covers.openlibrary.org/b/isbn/9781421806501-M.jpg",
    "https://covers.openlibrary.org/b/isbn/9780316183567-M.jpg",

])
const atividades = ref([]);
const filtro = ref('');
const isDialogOpen = ref(false);


const nome = ref('');
const enunciado = ref('');

const alunos = ref([])

</script>
<template>
    <div class="min-h-screen bg-[#e6f7fa] font-sans">
        <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
            <RouterLink to="/TelaInicialAluno">
                <div class="text-5xl flex ">
                    <h1>Leia+</h1>
                    <img src="../../assets/capivara.svg" alt="" />
                </div>
            </RouterLink>
        </header>

        <div class="max-w-5xl mx-auto bg-white shadow-md rounded-lg p-6 mt-16">
            <div class="flex items-center justify-between mb-6">
                <div class="flex items-center gap-3">
                    <RouterLink to="/TelaInicialAluno">
                        <img src="../../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8" />
                    </RouterLink>

                    <h2 class="text-[28px] font-bold">Turma: 1° ano - ensino fundamental</h2>
                </div>
            </div>
            <div class="flex justify-between items-center text-center mb-3">
                <h3 class="text-[20px] font-bold mt-3">Alunos</h3>
            </div>

            <div class="grid grid-cols-4 font-bold px-4 py-2 bg-blue-100 rounded-md mb-2">
                <span>Nome</span> <span>CPF</span> <span>Tipo</span> <span class="text-center">Ações</span>
            </div>
            <!-- <UsuarionaTurma v-for="aluno in alunos"/> -->
            <div class="flex justify-between items-center text-center mb-3 mt-8">
                <h3 class="text-[20px] font-bold mt-3">Atividades</h3>
            </div>
            <div class="flex justify-between font-bold px-4 py-2 bg-blue-100 rounded-md mb-2">
                
            </div>
            <AtividadeRow v-for="atividade in atividades" :key="atividade.id" :numeroatividade="atividade.nome"
                :atividadename="atividade.nome" />
            <div class="flex justify-between items-center text-center mb-3 mt-8">
                <h3 class="text-[20px] font-bold mt-3">Livros</h3>
            </div>

            <div class="flex overflow-x-auto gap-4 bg-blue-100 p-4 rounded">
                <img v-for="livro in livros" :src="livro" class="w-[160px] h-[230px] rounded-sm object-cover" />
            </div>



        </div>
    </div>
</template>
