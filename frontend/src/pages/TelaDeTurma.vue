<script setup>
import { ref, computed } from 'vue'
import '@vuepic/vue-datepicker/dist/main.css'
import { RouterLink } from 'vue-router';
import AtividadeRow from '../components/AtividadeRow.vue';
import { ScrollArea } from '@/components/ui/scroll-area'
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
const atividades = [
    { id: 1, nome: 'Atividade 01' },
    { id: 2, nome: 'Atividade 02' },
    { id: 3, nome: 'Atividade 03' },
]

const isDialogOpen = ref(false);

</script>
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

        <div class="max-w-5xl mx-auto bg-white shadow-md rounded-lg p-6 mt-16">
            <div class="flex items-center justify-between mb-6">
                <div class="flex items-center gap-3">
                    <button @click="$router.back()" class="text-2xl">
                        <img src="../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8" />
                    </button>
                    <h2 class="text-[28px] font-bold">Turma: 1° ano - ensino fundamental</h2>
                </div>
            </div>
            <div class="flex justify-between items-center text-center mb-3">
                <h3 class="text-[20px] font-bold mt-3">Alunos</h3>
                <button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600">
                    Adicionar usuário
                </button>
            </div>

            <div class="grid grid-cols-4 font-bold px-4 py-2 bg-blue-100 rounded-md mb-2">
                <span>Nome</span> <span>CPF</span> <span>Tipo</span> <span class="text-center">Ações</span>
            </div>
            <div class="flex justify-between items-center text-center mb-3 mt-8">
                <h3 class="text-[20px] font-bold mt-3">Atividades</h3>
                <RouterLink to="/Telacriaratividade">
                    <button class="bg-[#359DFF] text-white px-4 py-1 rounded hover:bg-blue-600">
                        Cadastrar atividade
                    </button>

                </RouterLink>

            </div>
            <div class="flex justify-between font-bold px-4 py-2 bg-blue-100 rounded-md mb-2">
                <span>Nome</span> <span class="text-center">Ações</span>
            </div>
            <AtividadeRow v-for="(atividade, index) in atividades" :key="atividade.id" :numeroatividade="atividade.id"
                :atividadename="atividade.nome" />
        </div>
    </div>
</template>
