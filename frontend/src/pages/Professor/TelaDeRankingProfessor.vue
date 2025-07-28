<script setup lang="ts">
import { ref, onMounted } from "vue";
import { RouterLink } from 'vue-router';
import axios from "axios";

interface Aluno {
  id: string;
  cpf: string;
  nome: string;
  email: string;
  senha: string;
  cargo: string;
  dataNascimento: string | null;
  matricula: string;
  pontos: number;
  qtdLivrosLidos: number;
  qtdLivrosFavoritos: number;
}

const alunosDaTurma = ref<Aluno[]>([]);
const isLoading = ref(true);
const hasError = ref(false);

async function getAlunos() {
  isLoading.value = true;
  hasError.value = false;
  try {
    const response = await axios.get<Aluno[]>("http://localhost:8080/alunos");
    if (response.status !== 200) {
      throw new Error(`Erro ao buscar alunos: ${response.status} - ${response.statusText}`);
    }
    console.log("Dados recebidos da API (antes da ordenação):", response.data);

    // --- Lógica de Ordenação Adicionada Aqui ---
    const sortedAlunos = [...response.data].sort((a, b) => b.pontos - a.pontos);   

    alunosDaTurma.value = sortedAlunos;
    console.log("Dados ordenados e atribuídos:", alunosDaTurma.value);

  } catch (error) {
    console.error("Erro ao carregar alunos:", error);
    hasError.value = true;
  } finally {
    isLoading.value = false;
  }
}

onMounted(() => {
  console.log("Componente Ranking montado no DOM.");
  getAlunos();
});
</script>

<template>
  <div class="min-h-screen bg-[#e6f7fa] font-sans">
    <header class="text-white bg-[#0f8ebd] flex justify-around py-3">
      <RouterLink to="/TelaInicial">
        <div class="text-5xl flex items-center gap-2">
          <h1>Leia+</h1>
          <img src="../../assets/capivara.svg" alt="Capivara Logo" class="h-12 w-auto" />
        </div>
      </RouterLink>
    </header>

    <div class="max-w-5xl mx-auto bg-white shadow-md rounded-lg p-6 mt-16">
      <div class="flex items-center justify-between mb-6">
        <div class="flex items-center gap-3">
          <RouterLink to="/TelaInicialProfessor">
            <img src="../../assets/botoes/botao_voltar.svg" alt="Botão Voltar" class="w-8 h-8" />
          </RouterLink>
          <h2 class="text-[28px] font-bold">Ranking da turma:</h2>
        </div>
      </div>

      <div class="flex items-center justify-center mb-6">
        <img src="../../assets/capivara-trofeu.png" alt="Capivara com Troféu" class="max-w-full h-auto" />
      </div>

      <div>
        <h1 class="font-normal mt-4 mb-5">Turma: <strong>1° ano A - Ensino fundamental</strong></h1>
      </div>

      <!-- Exibição de estados de carregamento e erro -->
      <div v-if="isLoading" class="text-center py-8 text-gray-600">
        Carregando alunos...
      </div>
      <div v-else-if="hasError" class="text-center py-8 text-red-600">
        Erro ao carregar os dados dos alunos. Por favor, tente novamente mais tarde.
      </div>
      <div v-else-if="alunosDaTurma.length === 0" class="text-center py-8 text-gray-600">
        Nenhum aluno encontrado para esta turma.
      </div>
      <div v-else>
        <div class="grid grid-cols-4 font-bold px-4 py-2 bg-blue-100 rounded-md mb-2">
          <span>Nome</span>
          <span>CPF</span>
          <span>Pontos</span>
          <span>Livros Lidos</span>
        </div>

        <!-- Iteração dos alunos -->
        <div
          v-for="aluno in alunosDaTurma"
          :key="aluno.id"
          class="grid grid-cols-4 px-4 py-2 border-b text-gray-800"
        >
          <span>{{ aluno.nome }}</span>
          <span>{{ aluno.cpf }}</span>
          <span>{{ aluno.pontos }}</span>
          <span>{{ aluno.qtdLivrosLidos }}</span>
        </div>
      </div>

    </div>
  </div>
</template>

<style scoped>

img {
  max-width: 100%;
  height: auto;
}
</style>
