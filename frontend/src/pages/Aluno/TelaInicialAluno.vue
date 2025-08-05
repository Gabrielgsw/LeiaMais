<script setup lang="ts">
import { RouterLink, useRouter } from "vue-router";
import { toTypedSchema } from "@vee-validate/zod";
import { useForm } from "vee-validate";
import * as z from "zod";
import { ref, onMounted } from "vue";
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
  FormField,
  FormItem,
  FormLabel,
} from "@/components/ui/form";
import Turma from "../../components/Turma.vue";
import axios from "axios";

const router = useRouter();

const turmas = ref<
  { id: string; nome: string; alunosMatriculados: []; professores: []; livros: []; atividades: [] }[]
>([]);

const livros = ref([
  "https://covers.openlibrary.org/b/isbn/9788522005239-M.jpg",
  "https://covers.openlibrary.org/b/isbn/8538048201-M.jpg",
  "https://covers.openlibrary.org/b/isbn/9780316183567-M.jpg",
  "https://covers.openlibrary.org/b/isbn/8538083724-M.jpg",
  "https://covers.openlibrary.org/b/isbn/8478646795-M.jpg"
 
]);



const userId = ref("");

function extrairISBN(url: string): string {
  const match = url.match(/isbn\/(\d+)-/);
  return match ? match[1] : "";
}

const handleLogout = async () => {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/auth/logout",
      {},
      { withCredentials: true }
    );
    if (response.status === 200) {
      alert("Logout realizado com sucesso.");
      router.push("/");
    }
  } catch (error) {
    console.error("Erro no logout:", error);
  }
};

const getUserId = async () => {
  try {
    const response = await axios.get("http://localhost:8080/api/auth/me/id", {
      withCredentials: true,
    });
    if (response.status === 200) {
      userId.value = response.data;
      console.log("ID do usuário obtido:", userId.value);
      return response.data;
    }
  } catch (error) {
    console.error("Erro ao obter ID do usuário:", error);
    return null;
  }
};

async function getTurmas() {
  if (!userId.value) {
    console.warn("ID do usuário não disponível para buscar turmas.");
    return;
  }
  try {
    console.log(`Buscando turmas para o usuário com ID: ${userId.value}`);
    const response = await axios.get(`http://localhost:8080/turmas/minhaturma/${userId.value}`, {
      withCredentials: true,
    });

    console.log("Status da resposta da API de turmas:", response.status);
    console.log("Dados recebidos da API de turmas:", response.data);

    if (response.status >= 200 && response.status < 300) {
      const data = response.data;

      
      if (data && typeof data === 'object' && !Array.isArray(data)) {
        
        turmas.value = [data];
        console.log("Turma única detectada e convertida para array:", turmas.value);
      } else if (Array.isArray(data)) {
        
        turmas.value = data;
        console.log("Turmas carregadas (já era um array):", turmas.value);
      } else {
        
        console.warn("Dados da API de turmas não são um objeto ou array esperado:", data);
        turmas.value = [];
      }
    } else {
      console.error("Erro ao buscar turmas:", response.status, response.statusText);
      turmas.value = [];
    }
  } catch (error) {
    console.error("Erro na requisição Axios para turmas:", error);
    turmas.value = [];
  }
}

onMounted(async () => {
  await getUserId(); 
  getTurmas(); 
});
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

    <main class="p-12">
      <div class="bg-white rounded-lg shadow-md p-6">

        <div class="flex items-center gap-4 mb-6">
          <div class="w-20 h-20 bg-gray-300 rounded-full">
            <img src="../../assets/capivara-icone-amigo.png" alt="Fundo"
              class="w-full h-full object-cover" />
          </div>
          <div>
            <p class="text-blue-600 font-bold text-lg">Olá, Aluno</p>
            <p class="text-gray-600">Escola: Educandário São Judas Tadeu</p>
            <p class="text-gray-600" v-if="turmas.length > 0">Turma atual: {{ turmas[0].nome }}</p>
          </div>
        </div>
        <div class="flex gap-4 mb-6">
          <router-link to="/TeladeRankingAluno">
            <button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600">Ranking
              geral</button>
          </router-link>

          <button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600"
            @click="handleLogout">Sair da conta</button>
        </div>
        <div class="space-y-4 mb-8">
          <template v-if="turmas.length > 0">
            <router-link v-for="turma in turmas" :key="turma.id" :to="`/turma/minhaturma/${turma.id}`" class="block">
              <Turma :nome="turma.nome" />
            </router-link>
          </template>
          <template v-else>
            <p class="text-gray-500">Nenhuma turma encontrada para este usuário.</p>
          </template>
        </div>

        <div>
          <h2 class="text-lg font-bold mb-4">Biblioteca Geral:</h2>
          <div class="flex overflow-x-auto gap-4 bg-blue-100 p-4 rounded">
            <router-link v-for="livro in livros" :key="livro" :to="`/livro/aluno/${extrairISBN(livro)}`">
              <img :src="livro" class="w-[160px] h-[230px] rounded-sm object-cover" />
            </router-link>
          </div>

          
        </div>
      </div>
    </main>
  </div>
</template>

<style scoped></style>