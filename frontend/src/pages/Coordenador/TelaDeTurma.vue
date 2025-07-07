<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import '@vuepic/vue-datepicker/dist/main.css'
import { RouterLink } from 'vue-router';
import axios from 'axios';
import { ScrollArea } from '@/components/ui/scroll-area'
import AtividadeRow from '@/components/AtividadeRow.vue';
import UsuarionaTurma from '@/components/UsuarionaTurma.vue';
import { Checkbox } from '@/components/ui/checkbox'
const livros = ref([
    'https://covers.openlibrary.org/b/isbn/9788562936524-M.jpg',
    'https://covers.openlibrary.org/b/isbn/9788544102930-M.jpg',
    'https://covers.openlibrary.org/b/isbn/9788544101636-M.jpg',
    'https://covers.openlibrary.org/b/isbn/9788544101636-M.jpg',
    'https://covers.openlibrary.org/b/isbn/9788544101636-M.jpg',
    'https://covers.openlibrary.org/b/isbn/9788544101636-M.jpg',
    'https://covers.openlibrary.org/b/isbn/9788544101636-M.jpg',
    'https://covers.openlibrary.org/b/isbn/9788544101636-M.jpg',
    'https://covers.openlibrary.org/b/isbn/9788544101636-M.jpg',

])
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
const atividades = ref([]);
const filtro = ref('');
const isDialogOpen = ref(false);


const nome = ref('');
const enunciado = ref('');

const alunosDaTurma = ref([])
const alunos = ref([])

async function getAlunos() {
    try {
        const response = await axios.get('http://localhost:8080/alunos');
        if (response.status !== 200) {
            throw new Error('Erro ao buscar alunos, status: ' + response.status + ' - ' + response.statusText);
        }
        alunos.value = response.data;
    } catch (error) {
        console.error("Erro ao carregar alunos:", error);
    }    
}

const carregarAtividades = async () => {
    try {
        const response = await axios.get('http://localhost:8080/atividades');
        atividades.value = response.data;
    } catch (error) {
        console.error("Erro ao carregar atividades:", error);
    }
};

const atividadesFiltradas = computed(() => {
    if (!filtro.value) return atividades.value
    return atividades.value.filter((atividade) =>
        atividade.nome.toLowerCase().includes(filtro.value.toLowerCase())
    )
})

const cadastrarAtividade = async () => {
    const novaAtividade = {
        nome: nome.value,
        enunciado: enunciado.value
    };

    try {
        const response = await axios.post('http://localhost:8080/atividades', novaAtividade);
        const atividadeSalva = response.data;
        console.log("Atividade salvo:", atividadeSalva);


        atividades.value.push(atividadeSalva);
        isDialogOpen.value = false;


        nome.value = '';
        enunciado.value = '';


    } catch (error) {
        console.error("Erro ao cadastrar:", error);
    }
};

const isAddAlunoDialogOpen = ref(false);



onMounted(() => {
    carregarAtividades();
    getAlunos()
});

</script>
<template>
    <div class="min-h-screen bg-[#e6f7fa] font-sans">
        <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
            <RouterLink to="/TelaInicial">
                <div class="text-5xl flex ">
                    <h1>Leia+</h1>
                    <img src="../../assets/capivara.svg" alt="" />
                </div>
            </RouterLink>
        </header>

        <div class="max-w-5xl mx-auto bg-white shadow-md rounded-lg p-6 mt-16">
            <div class="flex items-center justify-between mb-6">
                <div class="flex items-center gap-3">
                    <RouterLink to="/TelaInicial">
                        <img src="../../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8" />
                    </RouterLink>

                    <h2 class="text-[28px] font-bold">Turma: 1° ano - ensino fundamental</h2>
                </div>
            </div>
            <div class="flex justify-between items-center text-center mb-3">
                <h3 class="text-[20px] font-bold mt-3">Alunos</h3>
                <Dialog v-model:open="isAddAlunoDialogOpen">
                    <DialogTrigger as-child>
                        <button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600">
                            Adicionar aluno
                        </button>
                    </DialogTrigger>
                    <DialogContent class="sm:max-w-[425px]">
                        <DialogHeader>
                            <DialogTitle>
                                <h1 class="text-[#0084FF] font-bold text-[28px] mt-2">
                                    Adicionar aluno
                                </h1>
                            </DialogTitle>
                            <DialogDescription>
                                Adicione um ou mais alunos na turma.
                            </DialogDescription>
                        </DialogHeader>
                        <div v-for="aluno in alunos" class="flex gap-4">
                            <Checkbox id="nameAluno" />
                            <label for="nameAluno"
                                class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70">
                                {{aluno.nome}}
                            </label>
                            <label for="cpfaluno"
                                class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70">
                               {{aluno.cpf}}
                            </label>
                        </div>
                        <div class="flex items-center justify-between mt-4">
                            <button
                                class="bg-white text-[#359DFF] px-4 py-2 rounded shadow ring-1 ring-[#359DFF] hover:bg-black/5 transition-colors"
                                @click="isAddAlunoDialogOpen = false"> Cancelar
                            </button>
                            <Button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600"
                                >
                                Adicionar
                            </Button>
                        </div>
                    </DialogContent>
                </Dialog>
            </div>

            <div class="grid grid-cols-4 font-bold px-4 py-2 bg-blue-100 rounded-md mb-2">
                <span>Nome</span> <span>CPF</span> <span>Tipo</span> <span class="text-center">Ações</span>
                <UsuarionaTurma v-for="usuario in alunosDaTurma" :key="usuario.id" :id="usuario.id" :nome="usuario.nome"
                    :cpf="usuario.cpf" :tipo="usuario.tipo" />

            </div>
            <!-- <UsuarionaTurma v-for="aluno in alunos"/> -->
            <div class="flex justify-between items-center text-center mb-3 mt-8">
                <h3 class="text-[20px] font-bold mt-3">Atividades</h3>
                <RouterLink to="/Telacriaratividade"
                    class="bg-[#359DFF] text-white px-4 py-1 rounded hover:bg-blue-600">
                    Cadastrar atividade
                </RouterLink>

            </div>
            <div class="flex justify-between font-bold px-4 py-2 bg-blue-100 rounded-md mb-2">
                <span>Nome</span> <span class="text-center">Ações</span>
                <AtividadeRow v-for="atividade in atividades" :key="atividade.id" :numeroatividade="atividade.nome"
                :atividadename="atividade.nome" />
            </div>
            <div class="flex justify-between items-center text-center mb-3 mt-8">
                <h3 class="text-[20px] font-bold mt-3">Livros</h3>
            </div>

            <div class="flex overflow-x-auto gap-4 bg-blue-100 p-4 rounded">
                <img v-for="livro in livros" :src="livro" class="w-[160px] h-[230px] rounded-sm object-cover" />
            </div>

        </div>
    </div>
</template>
