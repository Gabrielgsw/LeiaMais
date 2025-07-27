<script setup lang="ts">
import { ref, onMounted, watch } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

// 1. Definição de Tipos para os dados dinâmicos
interface Enunciado {
  id: string;
  texto: string;
}

interface Atividade {
  id: string;
  nome: string;
  livro: {
    id: string;
    titulo: string;
  };
  enunciado: Enunciado[];
}

interface RespostaPayload {
  atividadeId: string;
  respostas: {
    enunciadoId: string;
    resposta: string;
  }[];
}

// 2. Props para receber o ID da atividade
const props = defineProps({
  idAtividade: {
    type: String,
    required: true,
  },
});

const router = useRouter();

// 3. Estado reativo para controlar o componente
const atividade = ref<Atividade | null>(null);
const answers = ref<string[]>([]);
const loading = ref<boolean>(true);
const error = ref<string | null>(null);
const submitted = ref<boolean>(false);

// 4. Funções para buscar e enviar dados
async function carregarAtividade() {
  loading.value = true;
  error.value = null;

  try {
    const response = await axios.get<Atividade>(`http://localhost:8080/api/atividades/${props.idAtividade}`);
    atividade.value = response.data;
    if (response.data.enunciado) {
      answers.value = Array(response.data.enunciado.length).fill('');
    }
  } catch (err) {
    console.error("Erro ao carregar atividade:", err);
    error.value = "Falha ao carregar a atividade.";
  } finally {
    loading.value = false;
  }
}

async function submitAnswers() {
  if (!atividade.value) return;

  const algumaRespostaVazia = answers.value.some(answer => answer.trim() === '');
  if (algumaRespostaVazia) {
    alert('Por favor, responda todas as questões.');
    return;
  }

  const payload: RespostaPayload = {
    atividadeId: props.idAtividade,
    respostas: atividade.value.enunciado.map((enunciado, index) => ({
      enunciadoId: enunciado.id,
      resposta: answers.value[index],
    })),
  };

  try {
    await axios.post('http://localhost:8080/respostas', payload);
    submitted.value = true;
  } catch (err) {
    console.error("Erro ao enviar respostas:", err);
    error.value = "Falha ao enviar as respostas.";
  }
}

// 5. Hooks do ciclo de vida
onMounted(carregarAtividade);
watch(() => props.idAtividade, carregarAtividade);

</script>

<template>
  <div class="container mx-auto p-4">

    <div v-if="loading" class="text-center py-10">Carregando...</div>
    <div v-else-if="error" class="text-center py-10 text-red-600">{{ error }}</div>

    <div v-else-if="submitted" class="mt-4 bg-green-100 p-6 rounded-lg text-center">
      <h2 class="text-xl font-bold">✅ Respostas enviadas com sucesso!</h2>
      <ul class="list-disc text-left pl-6 mt-4">
        <li v-for="(resp, idx) in answers" :key="idx">
          <strong>Pergunta {{ idx + 1 }}:</strong> {{ resp }}
        </li>
      </ul>
      <button @click="router.push('/')" class="mt-6 bg-green-600 text-white px-4 py-2 rounded">
        Voltar para o Início
      </button>
    </div>

    <div v-else-if="atividade">

      <div class="flex gap-80 mb-6">
        <div class="flex flex-col gap-4 mb-4">
          <div class="flex gap-4 items-center">
            <button @click="$router.back()" class="text-2xl hover:opacity-70">
              <img src="../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8" />
            </button>
            <h1 class="text-2xl font-bold">Atividade</h1>
          </div>
          <div class="flex gap-4">
            <label class="shrink-0 font-medium">Título da atividade:</label>
            <p><strong>{{ atividade.nome }}</strong></p>
          </div>
          <div class="flex gap-4">
            <label class="shrink-0 font-medium">Livro da atividade:</label>
            <p><strong>{{ atividade.livro.titulo }}</strong></p>
          </div>
        </div>
        <div>
          <img class="w-[110px] h-[130px] rounded-sm shadow-lg" src="../assets/livros-teste/hattypotter.jpg" alt="Capa do livro">
        </div>
      </div>

      <form @submit.prevent="submitAnswers">
        <div v-for="(enunciado, index) in atividade.enunciado" :key="enunciado.id" class="bg-white shadow-md p-6 mb-5 rounded-xl border">
          <div class="space-y-4">
            <p class="font-semibold text-lg mb-3">{{ index + 1 }}. {{ enunciado.texto }}</p>
            <textarea
                v-model="answers[index]"
                placeholder="Digite sua resposta aqui..."
                class="w-full p-3 border-2 rounded-lg resize-y min-h-[120px] focus:border-blue-500 focus:outline-none"
                required
            ></textarea>
          </div>
        </div>

        <div class="flex justify-between mt-8">
          <button type="button" @click="router.back()" class="bg-gray-500 text-white px-6 py-2 rounded-lg hover:bg-gray-600 transition-colors">
            Cancelar
          </button>
          <button type="submit" class="bg-[#359DFF] text-white px-6 py-2 rounded-lg hover:bg-blue-600 transition-colors">
            Enviar Respostas
          </button>
        </div>
      </form>

    </div>
  </div>
</template>