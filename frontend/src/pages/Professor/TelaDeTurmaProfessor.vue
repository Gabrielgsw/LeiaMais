<script setup lang="ts">
import { ref } from 'vue'
import { RouterLink } from 'vue-router';
import AtividadeRow from '@/components/AtividadeRow.vue';
import UsuarionaTurma from '@/components/UsuarionaTurma.vue';

const livros = ref([
    "https://covers.openlibrary.org/b/isbn/9788522005239-M.jpg",
    "https://covers.openlibrary.org/b/isbn/9781421806501-M.jpg",
    "https://covers.openlibrary.org/b/isbn/9780316183567-M.jpg",
])

type Aluno = {
    id: string
    cpf: string
    nome: string
    cargo: string
}

const atividades = ref([
    { id: '1', nome: 'Leitura do Livro - Pequeno Príncipe' }
]);

const alunosDaTurma = ref<Aluno[]>([
    { id: '1', cpf: '123.456.789-10', nome: 'Ana Silva', cargo: 'Aluno' },
    { id: '2', cpf: '987.654.321-00', nome: 'Pedro Santos', cargo: 'Aluno' }
]);

// Estado do popup de exclusão
const mostrarPopupExclusao = ref(false);
const atividadeParaExcluir = ref<string | null>(null);

const confirmarExclusao = (atividadeId: string) => {
    atividadeParaExcluir.value = atividadeId;
    mostrarPopupExclusao.value = true;
};

const cancelarExclusao = () => {
    mostrarPopupExclusao.value = false;
    atividadeParaExcluir.value = null;
};

const excluirAtividade = () => {
    if (atividadeParaExcluir.value) {
        // Como ainda não há banco de dados, vamos apenas simular a exclusão
        // removendo a atividade da lista (ou você pode apenas fechar o popup)
        console.log('Atividade excluída:', atividadeParaExcluir.value);
        // Para simular, você pode esvaziar a lista ou manter como está
        // atividades.value = [];
    }
    mostrarPopupExclusao.value = false;
    atividadeParaExcluir.value = null;
};
</script>

<template>
    <div class="min-h-screen bg-[#e6f7fa] font-sans">
        <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
            <RouterLink to="/TelaInicialProfessor">
                <div class="text-5xl flex">
                    <h1>Leia+</h1>
                    <img src="../../assets/capivara.svg" alt="" />
                </div>
            </RouterLink>
        </header>

        <div class="max-w-5xl mx-auto bg-white shadow-md rounded-lg p-6 mt-16 mb-16">
            <div class="flex items-center justify-between mb-6">
                <div class="flex items-center gap-3">
                    <RouterLink to="/TelaInicialProfessor">
                        <img src="../../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8" />
                    </RouterLink>
                    <h2 class="text-[28px] font-bold">Turma: 1° ano - ensino fundamental</h2>
                </div>
            </div>

            <!-- Seção de Alunos -->
            <div class="mb-8">
                <h3 class="text-[20px] font-bold mb-3">Alunos</h3>
                <div class="grid grid-cols-4 font-bold px-4 py-2 bg-blue-100 rounded-md mb-2">
                    <span>Nome</span>
                    <span>CPF</span>
                    <span>Tipo</span>
                    <span class="text-center">Ações</span>
                </div>
                <UsuarionaTurma 
                    v-for="usuario in alunosDaTurma" 
                    :key="usuario.id" 
                    :id="usuario.id" 
                    :nome="usuario.nome"
                    :cpf="usuario.cpf" 
                    :tipo="usuario.cargo" 
                />
            </div>

            <!-- Seção de Atividades -->
            <div class="mb-8">
                <div class="flex justify-between items-center mb-3">
                    <h3 class="text-[20px] font-bold">Atividades</h3>
                    <RouterLink 
                        to="/TelaCriarAtividadeProfessor"
                        class="bg-[#359DFF] text-white px-4 py-2 rounded hover:bg-blue-600"
                    >
                        Cadastrar atividade
                    </RouterLink>
                </div>
                <div class="flex justify-between px-4 py-2 bg-blue-100 rounded-md mb-2 font-bold">
                    <span>Nome</span>
                    <span class="text-center">Ações</span>
                </div>
                <div class="flex justify-between items-center px-4 py-2 bg-white border-b">
                    <span>{{ atividades[0].nome }}</span>
                    <div class="flex gap-2">
                        <RouterLink to="/TelaAlunosCorrecao" class="bg-green-500 text-white px-3 py-1 rounded text-sm hover:bg-green-600">
                            Corrigir
                        </RouterLink>
                        <RouterLink to="/TelaCriarAtividadeProfessor" class="bg-yellow-500 text-white px-3 py-1 rounded text-sm hover:bg-yellow-600">
                            Editar
                        </RouterLink>
                        <button 
                            @click="confirmarExclusao(atividades[0].id)"
                            class="bg-red-500 text-white px-3 py-1 rounded text-sm hover:bg-red-600"
                        >
                            Excluir
                        </button>
                    </div>
                </div>
            </div>

            <!-- Seção de Livros -->
            <div class="mb-8">
                <h3 class="text-[20px] font-bold mb-3">Livros</h3>
                <RouterLink to="/TeladeLivroGeral">
                    <div class="flex overflow-x-auto gap-4 bg-blue-100 p-4 rounded">
                        <img 
                            v-for="livro in livros" 
                            :src="livro" 
                            class="w-[160px] h-[230px] rounded-sm object-cover" 
                        />
                    </div>
                </RouterLink>
            </div>
        </div>

        <!-- Popup de Confirmação de Exclusão -->
        <div 
            v-if="mostrarPopupExclusao" 
            class="fixed inset-0 z-50 flex items-center justify-center"
            style="background-color: rgba(0, 0, 0, 0.5);"
        >
            <div class="bg-white rounded-lg w-[450px] shadow-2xl max-w-md mx-4">
                <!-- Header do modal -->
                <div class="bg-[#359DFF] text-white px-6 py-4 rounded-t-lg">
                    <h3 class="text-lg font-semibold">Excluir atividade</h3>
                </div>
                
                <!-- Conteúdo do modal -->
                <div class="p-6">
                    <p class="text-gray-700 text-center mb-6">
                        Tem certeza que deseja excluir esta atividade?
                    </p>
                    
                    <!-- Botões -->
                    <div class="flex gap-3 justify-end">
                        <button 
                            @click="cancelarExclusao"
                            class="px-6 py-2 border border-[#359DFF] text-[#359DFF] rounded hover:bg-blue-50 transition-colors"
                        >
                            Cancelar
                        </button>
                        <button 
                            @click="excluirAtividade"
                            class="px-6 py-2 bg-[#359DFF] text-white rounded hover:bg-blue-600 transition-colors"
                        >
                            Excluir
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>