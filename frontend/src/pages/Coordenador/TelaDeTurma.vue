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
const atividades = ref([
  { id: '1', nome: 'Leitura do Livro - Pequeno Príncipe' }
]);
const alunosDaTurma = ref<Aluno[]>([]);
const alunosDoSistema = ref<(Aluno & { checked: boolean })[]>([]);
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
      `http://localhost:8080/atividades`
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

    <div class="max-w-5xl mx-auto bg-white shadow-md rounded-lg p-6 mt-16">
      <div class="flex items-center justify-between mb-6">
        <div class="flex items-center gap-3">
          <RouterLink to="/TelaInicial">
            <img src="../../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8" />
          </RouterLink>

          <h2 class="text-[28px] font-bold">
            Turma: 1° ano - ensino fundamental
          </h2>
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
            <div v-for="aluno in alunosDoSistema" class="flex gap-4">
              <Checkbox id="nameAluno" v-model="aluno.checked" />
              <label for="nameAluno"
                class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70">
                {{ aluno.nome }}
              </label>
              <label for="cpfaluno"
                class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70">
                {{ aluno.cpf }}
              </label>
            </div>
            <div class="flex items-center justify-between mt-4">
              <button
                class="bg-white text-[#359DFF] px-4 py-2 rounded shadow ring-1 ring-[#359DFF] hover:bg-black/5 transition-colors"
                @click="isAddAlunoDialogOpen = false">
                Cancelar
              </button>
              <Button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600"
                @click="adicionarAlunosNaTurma">
                Adicionar
              </Button>
            </div>
          </DialogContent>
        </Dialog>
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
        <RouterLink :to="`/turma/${turma?.id}/criar-atividade`"
          class="bg-[#359DFF] text-white px-4 py-1 rounded hover:bg-blue-600">
          Cadastrar atividade
        </RouterLink>
      </div>
      <!-- <div class="flex justify-between font-bold px-4 py-2 bg-blue-100 rounded-md mb-2"> -->
      <div class="flex justify-between px-4 py-2 bg-blue-100 rounded-md mb-2 font-bold">
        <span>Nome</span> <span class="text-center">Ações</span>
      </div>
      <div v-for="atividade in atividades" :key="atividade.id"
        class="flex justify-between items-center px-4 py-2 bg-white border-b">
        <span>{{ atividade.nome }}</span>
        <div class="flex gap-2">
          <RouterLink :to="`/TelaAlunosCorrecao/${atividade.id}`"
            class="bg-green-500 text-white px-3 py-1 rounded text-sm hover:bg-green-600">
            Corrigir
          </RouterLink>
          <RouterLink :to="`/TelaCriarAtividadeProfessor/${atividade.id}`"
            class="bg-yellow-500 text-white px-3 py-1 rounded text-sm hover:bg-yellow-600">
            Editar
          </RouterLink>
          <button @click="confirmarExclusao(atividade.id)"
            class="bg-red-500 text-white px-3 py-1 rounded text-sm hover:bg-red-600">
            Excluir
          </button>
        </div>
      </div>

      <div class="flex justify-between">
        <router-link to="/TeladeRanking">
          <button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600 transition-colors mt-4">
            Ranking geral
          </button>
        </router-link>
        <router-link to="/TelaBiblioteca">
          <button class="bg-[#359DFF] text-white mt-4 items-end px-4 py-2 rounded shadow hover:bg-blue-600">
            Ver mais
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>
