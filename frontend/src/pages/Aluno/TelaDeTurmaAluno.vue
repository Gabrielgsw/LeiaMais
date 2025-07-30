<script setup lang="ts">
import { ref, computed, onMounted } from "vue";
import "@vuepic/vue-datepicker/dist/main.css";
import { RouterLink, useRouter } from "vue-router";
import axios from "axios";
import { ScrollArea } from "@/components/ui/scroll-area";
import AtividadeRow from "@/components/AtividadeRow.vue";
import UsuarionaTurma from "@/components/UsuarionaTurma.vue";
import { Checkbox } from "@/components/ui/checkbox";
const livros = ref([
  // 'C:\Users\Gabriel Germano\Desktop\Repositorios\LeiaMais\frontend\src\assets\livros-teste',s
  //   "https://covers.openlibrary.org/b/isbn/9788544102930-M.jpg",
  //   "https://covers.openlibrary.org/b/isbn/9788544101636-M.jpg",
  //   "https://covers.openlibrary.org/b/isbn/9788544101636-M.jpg",
  //   "https://covers.openlibrary.org/b/isbn/9788544101636-M.jpg",
  //   "https://covers.openlibrary.org/b/isbn/9788544101636-M.jpg",
  //   "https://covers.openlibrary.org/b/isbn/9788544101636-M.jpg",
  //   "https://covers.openlibrary.org/b/isbn/9788544101636-M.jpg",
  //   "https://covers.openlibrary.org/b/isbn/9788544101636-M.jpg",
]);
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
type Aluno = {
  id: string;
  cpf: string;
  nome: string;
  email: string;
  senha: string;
  cargo: string;
  dataNascimento: string;
  matricula: string;
  pontos: number;
  qtdLivrosLidos: number;
  qtdLivrosFavoritos: number;
};

const atividades = ref([]);
const alunosDaTurma = ref<Aluno[]>([]);
const alunosDoSistema = ref<(Aluno & { checked: boolean })[]>([]);


async function getAlunos() {
  try {
    const response = await axios.get("http://localhost:8080/alunos");
    if (response.status !== 200) {
      throw new Error(
        "Erro ao buscar alunos, status: " +
        response.status +
        " - " +
        response.statusText
      );
    }
    console.log("Alunos carregados:", response.data);
    alunosDoSistema.value = response.data.map((aluno: Aluno) => ({
      ...aluno,
      checked: false,
    }));

  } catch (error) {
    console.error("Erro ao carregar alunos:", error);
  }
}

// const atividadesFiltradas = computed(() => {
//     if (!filtro.value) return atividades.value
//     return atividades.value.filter((atividade) =>
//         atividade.nome.toLowerCase().includes(filtro.value.toLowerCase())
//     )
// })


async function adicionarAlunosNaTurma() {
  const alunos = alunosDoSistema.value.filter((aluno) => aluno.checked);
  if (alunos.length === 0) {
    alert("Nenhum aluno selecionado para adicionar.");
    return;
  }

  console.log("Alunos selecionados para adicionar:", alunos);

  isAddAlunoDialogOpen.value = false;

  alunosDaTurma.value = alunos.map((aluno) => ({
    id: aluno.id,
    cpf: aluno.cpf,
    nome: aluno.nome,
    email: aluno.email,
    senha: aluno.senha,
    cargo: aluno.cargo,
    dataNascimento: aluno.dataNascimento,
    matricula: aluno.matricula,
    pontos: aluno.pontos,
    qtdLivrosLidos: aluno.qtdLivrosLidos,
    qtdLivrosFavoritos: aluno.qtdLivrosFavoritos,
  }));

  for (const aluno of alunos) {
    console.log(aluno)
    try {
      const response = await axios.post(`http://localhost:8080/turmas/aluno/${turma.value.nome}/${aluno.matricula}`);
      console.log(`Aluno ${aluno.nome} adicionado com sucesso:`, response.data);
    } catch (error) {
      console.error("Erro ao adicionar alunos:", error);
    }
  }

  carregarDadosDaTurma();
}

const router = useRouter();
const isAddAlunoDialogOpen = ref(false);
const turma = ref();
async function carregarDadosDaTurma() {
  try {
    const response = await axios.get(
      `http://localhost:8080/turmas/${router.currentRoute.value.params.id}`
    );
    if (response.status !== 200) {
      throw new Error(
        "Erro ao buscar dados da turma, status: " +
        response.status +
        " - " +
        response.statusText
      );
    }
    turma.value = response.data;
    alunosDaTurma.value = response.data.alunosMatriculados;
    console.log("Dados da turma carregados:", turma.value);
  } catch (error) {
    console.error("Erro ao carregar dados da turma:", error);
  }
}

const carregarAtividades = async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/atividades`
    );
    if (response.status !== 200) {
      throw new Error(
        "Erro ao buscar dados da turma, status: " +
        response.status +
        " - " +
        response.statusText
      );
    }

    atividades.value = response.data;
  } catch (error) {
    console.error("Erro ao carregar atividades:", error);
  }
};

onMounted(() => {
  carregarDadosDaTurma();
  carregarAtividades();
  getAlunos();
});
</script>
<template>
  <div class="min-h-screen bg-[#e6f7fa] font-sans">
    <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
      <RouterLink to="/TelaInicial">
        <div class="text-5xl flex">
          <h1>Leia+</h1>
          <img src="../../assets/capivara.svg" alt="" />
        </div>
      </RouterLink>
    </header>

    <div class="max-w-5xl mx-auto bg-white shadow-md rounded-lg p-6 mt-16 mb-5">
      <div class="flex items-center justify-between">
        <div class="flex items-center gap-3">
          <RouterLink to="/TelaInicialAluno">
            <img src="../../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8" />
          </RouterLink>

          <h2 class="text-[28px] font-bold">
            Turma: 1° ano - ensino fundamental
          </h2>
        </div>
      </div>
      <div class="flex justify-between items-center text-center mb-3">
        <h3 class="text-[20px] font-bold mt-3">Alunos</h3>
        
      </div>


      <div class="grid grid-cols-4 font-bold px-4 py-2 bg-blue-100 rounded-md mb-2">
        <span>Nome</span> <span>CPF</span> <span>Tipo</span>
        <span class="text-center">Ações</span>
      </div>

      <UsuarionaTurma v-for="usuario in alunosDaTurma" :key="usuario.id" :id="usuario.id" :nome="usuario.nome"
        :cpf="usuario.cpf" :tipo="usuario.cargo" />

      <!-- <UsuarionaTurma v-for="aluno in alunos"/> -->
      <div class="flex justify-between items-center text-center mb-3 mt-8">
        <h3 class="text-[20px] font-bold mt-3">Atividades</h3>
        
      </div>
      <!-- <div class="flex justify-between font-bold px-4 py-2 bg-blue-100 rounded-md mb-2"> -->
      <div class="flex justify-between px-4 py-2 bg-blue-100 rounded-md mb-2 font-bold">
        <span>Nome</span> <span class="text-center">Ações</span>
      </div>
      <AtividadeRow v-for="atividade in atividades" :key="atividade.id" :numeroatividade="atividade.nome"
        :atividadename="atividade.nome" />

    
    </div>
  </div>
</template>
