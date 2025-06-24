<script>
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
                <button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600">
                    Cadastrar Atividade
                </button>
            </div>
            <div class="grid grid-cols-4 font-bold px-4 py-2 bg-blue-100 rounded-md mb-2">
                <span>Nome</span> <span>Enunciado</span> <span>Tipo</span> <span class="text-center">Ações</span>
            </div>
            <div class="grid grid-cols-4 items-center px-4 py-2 bg-blue-100 mb-2 rounded-md">
                <span>{{ nome }}</span>
                <span>{{ cpf }}</span>
                <span>{{ tipo }}</span>
                <span class="flex justify-center gap-3">
                    <button>
                        <img src="../assets/botoes/botao_editar.svg" alt="Editar" />
                    </button>
                    <!--aqui vai todo o conteúdo/popup-->
                    
                    <Dialog v-model:open="isDialogOpen">
                        <DialogTrigger as-child>
                            <button>
                                <img src="/src/assets/botoes/botao_excluir.svg" alt="Excluir" />
                            </button>
                        </DialogTrigger>
                        <DialogContent>
                            <DialogHeader>
                                <DialogTitle>
                                    <h1 class="text-[#0084FF] font-bold text-[24px]  mt-2">
                                        Tem certeza que deseja excluir este usuário?
                                    </h1>

                                </DialogTitle>
                                <div>
                                    <p>
                                        <span class="font-bold">Nome:</span>
                                        {{ nome }}
                                    </p>
                                    <p>
                                        <span class="font-bold ">CPF:</span>
                                        {{ cpf }}
                                    </p>
                                </div>
                            </DialogHeader>
                            <div class="flex items-center justify-between mt-4">
                                <button
                                    class="bg-white text-[#359DFF] px-4 py-2 rounded shadow ring-1 ring-[#359DFF] hover:bg-black/5 transition-colors"
                                    @click="isDialogOpen = false"> Cancelar
                                </button>
                                <Button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600"
                                    @click="$emit('deleteUser', id)">
                                    Excluir
                                </Button>
                            </div>


                        </DialogContent>
                    </Dialog>

                </span>
            </div>
        </div>

    </div>

</template>
