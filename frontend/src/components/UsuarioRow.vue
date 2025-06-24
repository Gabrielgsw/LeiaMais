<script setup lang="ts">
//todos os imports do sistema
import { ref, watch } from 'vue';
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
import Datepicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'

//todas as variáveis do sistema
const { nome, cpf, id, tipo } = defineProps<{
    id: number;
    nome: string;
    cpf: string;
    tipo: string;
}>();

const isDialogOpen = ref(false);
const idDialogOpenEdit = ref(false)
const date = ref(null)

const nomeInput = ref('');
const emailInput = ref('');
const senhaInput = ref('');
const cpfInput = ref('');
const nascimentoInput = ref(null);

//todas as funções do sistema
function carregarDadosParaEdicao() {
    nomeInput.value = nome;
    cpfInput.value = cpf;
    emailInput.value = 'email@email.com'; // substitua por valor real se possível
    senhaInput.value = '';
    nascimentoInput.value = null; // ou use uma data válida, se houver
}
const emit = defineEmits<{
    deleteUser: [id: number];
}>();
const isDialogOpenEdit = ref(false);
</script>

<template>
    <div class="grid grid-cols-4 items-center px-4 py-2 bg-blue-100 mb-2 rounded-md">
        <span>{{ nome }}</span>
        <span>{{ cpf }}</span>
        <span>{{ tipo }}</span>
        <span class="flex justify-center gap-3">
            <Dialog v-model:open="idDialogOpenEdit">
                <DialogTrigger as-child>
                    <button @open="carregarDadosParaEdicao">
                        <img src="../assets/botoes/botao_editar.svg" alt="Editar" />
                    </button>
                </DialogTrigger>
                <DialogContent >
                    <form class=" space-y-6">
                        <FormField v-slot="{ componentField }" name="username">
                            <DialogHeader>
                                <DialogTitle>
                                    <h1 class="text-[#0084FF] font-bold text-[28px] mt-2">
                                        Editar usuário
                                    </h1>
                                </DialogTitle>
                            </DialogHeader>
                            <FormItem>
                                <FormLabel>Nome <span class="text-red-500 font-bold">*</span></FormLabel>
                                <FormControl>
                                    <Input id="nome"
                                        class="col-span-4 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                                        v-model="nomeInput" placeholder="Nome" />
                                </FormControl>
                            </FormItem>
                            <FormItem>
                                <FormLabel>E-mail <span class="text-red-500 font-bold">*</span></FormLabel>
                                <FormControl>
                                    <Input id="email" type="email" v-model="emailInput"
                                        class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                                        placeholder="Digite o email" />
                                </FormControl>
                            </FormItem>
                            <FormItem>
                                <FormLabel>Senha <span class="text-red-500 font-bold">*</span></FormLabel>
                                <FormControl>
                                    <Input id="senha" type="password"
                                        class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                                        placeholder="Digite a senha" />
                                    <i class="bi bi-eye"></i>
                                </FormControl>
                            </FormItem>
                            <FormItem>
                                <FormLabel>CPF <span class="text-red-500 font-bold">*</span></FormLabel>
                                <FormControl>
                                    <Input id="cpf" type="number"
                                        class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                                        placeholder="Digite o CPF" />
                                </FormControl>
                            </FormItem>
                            <FormItem>
                                <FormLabel>Data de nascimento <span class="text-red-500 font-bold">*</span>
                                </FormLabel>
                                <FormControl>

                                    <Datepicker v-model="date" :max-date="new Date()" :format="'dd/MM/yyyy'"
                                        placeholder="Selecione a data" class="" />

                                </FormControl>
                            </FormItem>


                        </FormField>

                    </form>
                    <div class="flex items-center justify-between mt-4">
                        <button
                            class="bg-white text-[#359DFF] px-4 py-2 rounded shadow ring-1 ring-[#359DFF] hover:bg-black/5 transition-colors"
                            @click="idDialogOpenEdit = false"> Cancelar
                        </button>
                        <Button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600"
                            @click="$emit('deleteUser', id)">
                            Atualizar
                        </Button>
                    </div>
                </DialogContent>
            </Dialog>


            <!--aqui vai todo o conteúdo/popup de excluir-->
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
</template>
