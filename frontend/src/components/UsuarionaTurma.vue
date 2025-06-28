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

const nomeInput = ref(nome);
const emailInput = ref('email@teste.com');
const senhaInput = ref('');
const cpfInput = ref(cpf);
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
    <div class="grid grid-cols-5 items-center px-4 py-2 bg-blue-100 mb-2 rounded-md">
        <span>{{ nome }}</span>
        <div>
                Corrigir
            </div>
        <span>{{ cpf }}</span>
        <span>{{ tipo }}</span>
        <span class="flex justify-center gap-3 ">
            <div>
                Corrigir
            </div>
            

        </span>
    </div>

</template>
