<script setup lang="ts">
import { ref, onMounted, watch } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

// 1. Definição de Tipos (sem alterações)
interface Atividade {
  id: string;
  nome: string;
  livro: {
    id: string;
    titulo: string;
  };
  enunciado: string[];
}

interface RespostaPayload {
  atividadeId: string;
  respostas: {
    enunciadoTexto: string;
    resposta: string;
  }[];
}

// 2. Props para receber o ID da atividade (sem alterações)
const props = defineProps({
  idAtividade: {
    type: String,
    required: true,
  },
});

const router = useRouter();

// 3. Estado reativo (sem alterações)
const atividade = ref<Atividade | null>(null);
const answers = ref<string[]>([]);
const loading = ref<boolean>(true);
const error = ref<string | null>(null);
const submitted = ref<boolean>(false);

// 4. Funções para buscar e enviar dados
async function carregarAtividade() {
  loading.value = true;
  error.value = null;
  console.log(`Buscando atividade com ID: ${props.idAtividade}`);

  try {
    // --- CORREÇÃO FINAL ---
    // 1. Voltamos a usar o endpoint correto que busca por ID.
    // 2. O tipo de resposta esperado agora é um único objeto 'Atividade', e não 'Atividade[]'.
    const response = await axios.get<Atividade>(`http://localhost:8080/api/atividades/${props.idAtividade}`);
    
    // 3. Como a resposta já é o objeto da atividade, podemos usá-la diretamente.
    if (response.data) {
      console.log("Atividade recebida da API:", response.data);
      atividade.value = response.data;
      if (response.data.enunciado) {
        answers.value = Array(response.data.enunciado.length).fill('');
      }
    } else {
      // Este 'else' provavelmente nunca será atingido se a API retornar 404,
      // pois isso cairá no bloco 'catch'. Mas é uma boa prática manter.
      throw new Error("A API retornou uma resposta vazia.");
    }
  } catch (err: any) {
    // O erro 404 (Not Found) do backend será capturado aqui.
    console.error("Erro ao carregar atividade:", err);
    if (err.response && err.response.status === 404) {
      error.value = `Atividade com o ID especificado não foi encontrada.`;
    } else {
      error.value = err.message || "Falha ao conectar com a API.";
    }
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
    respostas: atividade.value.enunciado.map((textoDoEnunciado, index) => ({
      enunciadoTexto: textoDoEnunciado,
      resposta: answers.value[index],
    })),
  };

  console.log("Enviando payload:", payload);

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

<!-- O Template não precisa de alterações -->
<template>
  <div class="bg-gray-50 min-h-screen">
    <div class="container mx-auto p-4 sm:p-6 md:p-8">

      <!-- Estados de Carregamento e Erro -->
      <div v-if="loading" class="text-center py-20 text-gray-500">
        <p>Carregando atividade...</p>
      </div>
      <div v-else-if="error" class="text-center py-20 text-red-600 bg-red-50 p-6 rounded-lg">
        <h2 class="font-bold text-xl mb-2">Ocorreu um erro</h2>
        <p>{{ error }}</p>
      </div>

      <!-- Estado de Sucesso (Enviado) -->
      <div v-else-if="submitted" class="max-w-2xl mx-auto mt-8 bg-green-100 p-8 rounded-xl text-center shadow-md">
        <h2 class="text-2xl font-bold text-green-800 mb-4">✅ Respostas enviadas com sucesso!</h2>
        <ul class="list-disc list-inside text-left space-y-2 text-gray-700 bg-white p-4 rounded-lg">
          <li v-for="(resp, idx) in answers" :key="idx">
            <strong>Pergunta {{ idx + 1 }}:</strong> {{ resp }}
          </li>
        </ul>
        <button @click="router.push('/')" class="mt-8 bg-green-600 text-white px-6 py-3 rounded-lg hover:bg-green-700 transition-colors shadow-sm">
          Voltar para o Início
        </button>
      </div>

      <!-- Conteúdo Principal da Atividade -->
      <div v-else-if="atividade">

        <!-- Cabeçalho da Atividade -->
        <div class="bg-white rounded-xl shadow-sm p-6 mb-8">
          <div class="flex flex-col sm:flex-row gap-6 justify-between items-start">
            <!-- Informações da Atividade -->
            <div class="flex-grow">
              <div class="flex gap-4 items-center mb-6">
                <button @click="$router.back()" class="text-gray-500 hover:text-gray-800 transition-colors">
                  <svg xmlns="http://www.w3.org/2000/svg" class="w-7 h-7" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"><path d="M19 12H5M12 19l-7-7 7-7"/></svg>
                </button>
                <h1 class="text-3xl font-bold text-gray-800">Atividade</h1>
              </div>
              <div class="space-y-3">
                <div class="flex items-baseline gap-3">
                  <label class="shrink-0 font-medium text-gray-500 text-sm">TÍTULO:</label>
                  <p class="font-semibold text-lg text-gray-900">{{ atividade.nome }}</p>
                </div>
                <div class="flex items-baseline gap-3">
                  <label class="shrink-0 font-medium text-gray-500 text-sm">LIVRO:</label>
                  <p class="font-semibold text-lg text-gray-900">{{ atividade.livro.titulo }}</p>
                </div>
              </div>
            </div>
            <!-- Capa do Livro -->
            <div class="shrink-0 self-center sm:self-start">
              <img class="w-28 h-40 rounded-md shadow-lg object-cover border" 
                   :src="'/img/placeholder.jpg'" 
                   onerror="this.onerror=null;this.src='https://placehold.co/112x160/e0e0e0/757575?text=Capa'"
                   alt="Capa do livro">
            </div>
          </div>
        </div>

        <!-- Formulário de Respostas -->
        <form @submit.prevent="submitAnswers" class="space-y-6">
          <div v-for="(textoDoEnunciado, index) in atividade.enunciado" :key="index" class="bg-white shadow-sm p-6 rounded-xl border border-gray-200">
            <p class="font-semibold text-lg mb-4 text-gray-800">{{ index + 1 }}. {{ textoDoEnunciado }}</p>
            <textarea
              v-model="answers[index]"
              placeholder="Digite sua resposta aqui..."
              class="w-full p-3 border border-gray-300 rounded-lg resize-y min-h-[120px] focus:border-blue-500 focus:ring-2 focus:ring-blue-200 focus:outline-none transition-shadow"
              required
            ></textarea>
          </div>

          <!-- Botões de Ação -->
          <div class="flex flex-col-reverse sm:flex-row justify-end gap-4 pt-4">
            <button type="button" @click="router.back()" class="bg-gray-200 text-gray-800 px-6 py-3 rounded-lg hover:bg-gray-300 transition-colors">
              Cancelar
            </button>
            <button type="submit" class="bg-blue-600 text-white px-6 py-3 rounded-lg hover:bg-blue-700 transition-colors shadow-sm">
              Enviar Respostas
            </button>
          </div>
        </form>

      </div>
    </div>
  </div>
</template>
