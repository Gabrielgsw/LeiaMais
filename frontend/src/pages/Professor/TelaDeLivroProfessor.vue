<script setup lang="ts">
import { useRoute } from 'vue-router'
import { ref, onMounted, computed, watch } from 'vue'
import Rating from 'primevue/rating'
import Select from 'primevue/select'
import axios from 'axios'

const route = useRoute()
const isbn = route.params.isbn as string
const userId = ref("")

// --- Refs para armazenar os dados ---
const livroInfo = ref({
  id: '',
  titulo: 'Carregando Título...',
  autor: '',
  editora: null,
  descricao: ''
});
const leituraId = ref<string | null>(null); // Armazena o ID do registro de leitura
const value = ref(0); // v-model para o Rating (estrelas)
const selectedStatus = ref(); // v-model para o Select (status)
const isDataLoaded = ref(false); // Flag para controlar o watch inicial

// --- Propriedades Computadas ---
const descricaoLivro = computed(() => {
  if (livroInfo.value.descricao) {
    return livroInfo.value.descricao;
  }
  if (!livroInfo.value.autor) {
    return 'Carregando descrição...';
  }
  let desc = `Um livro de ${livroInfo.value.autor}.`;
  if (livroInfo.value.editora) {
    desc += ` Publicado por ${livroInfo.value.editora}.`;
  }
  return desc;
});

// --- Funções de API ---

const getLivroInfo = async (isbn: string) => {
  try {
    const response = await axios.get(`http://localhost:8080/livros/isbn/${isbn}`, {
      withCredentials: true,
    });
    if (response.data) {
      livroInfo.value = response.data;
    }
  } catch (error) {
    console.error("Erro ao buscar informações do livro:", error);
    livroInfo.value.titulo = "Livro não encontrado";
  }
};

const getUserId = async () => {
  try {
    const response = await axios.get("http://localhost:8080/api/auth/me/id", {
      withCredentials: true,
    });
    if (response.status === 200) {
      userId.value = response.data;
    }
  } catch (error) {
    console.error("Erro ao obter ID do usuário:", error);
  }
};

// Opções de status que correspondem ao Enum do backend
const options = ref([
  { name: 'Não iniciado', code: 'NAOINICIADO' },
  { name: 'Em leitura', code: 'EMLEITURA' },
  { name: 'Concluído', code: 'CONCLUIDO' }
]);

const getLeituraStatus = async (idUser: string, livroIsbn: string) => {
  try {
    const response = await axios.get(`http://localhost:8080/leituras/leitura/${idUser}/${livroIsbn}`, {
      withCredentials: true,
    });

    if (response.data && response.data.length > 0) {
      const leitura = response.data[0];
      leituraId.value = leitura.id; // Salva o ID da leitura
      value.value = leitura.avaliacao || 0; // Salva a avaliação (estrelas)
      selectedStatus.value = options.value.find(option => option.code === leitura.status);
    } else {
      selectedStatus.value = options.value.find(o => o.code === 'NAOINICIADO');
    }
  } catch (error) {
    console.log("Nenhum registro de leitura, definindo status como 'Não iniciado'.");
    selectedStatus.value = options.value.find(o => o.code === 'NAOINICIADO');
  }
};

const iniciarLeitura = async (livroIsbn: string) => {
  if (!userId.value || !livroIsbn) {
    // Não mostra alerta, pois pode ser chamado automaticamente
    console.log("Dados do usuário ou do livro incompletos para iniciar leitura.");
    return;
  }
  try {
    // O backend deve lidar com a criação de um registro caso ele não exista
    await axios.post(`http://localhost:8080/leituras/${livroIsbn}`, {
      userId: userId.value
    }, { withCredentials: true });
    
    // Após a tentativa de criação, busca o status atual
    await getLeituraStatus(userId.value, livroIsbn);

  } catch (error) {
    console.error("Erro na chamada para iniciar leitura:", error);
  }
};

const handleUpdateLeitura = async () => {
  if (!leituraId.value) {
    console.log("Não é possível atualizar: ID da leitura não encontrado.");
    return;
  }
  
  const statusToUpdate = selectedStatus.value?.code;
  const ratingToUpdate = value.value;

  if (!statusToUpdate) {
    console.error("Status inválido para atualização.");
    return;
  }

  try {
    await axios.put(`http://localhost:8080/leituras/${leituraId.value}`, {
      status: statusToUpdate,
      avaliacao: ratingToUpdate
    }, { withCredentials: true });
    console.log("Leitura atualizada com sucesso no backend.");
  } catch (error) {
    console.error("Erro ao atualizar leitura:", error);
  }
};

// --- Watchers: Observam mudanças e chamam a atualização ---
watch([selectedStatus, value], () => {
  if (isDataLoaded.value) {
    handleUpdateLeitura();
  }
});


// --- Funções de Ação do Usuário ---

function abrirPdf(isbn: string) {
  const url = `http://localhost:8080/livros/${isbn}/pdf`;
  window.open(url, '_blank');
}

const handleLerAgora = async () => {
  try {
    if (!userId.value) {
      await getUserId();
    }
    await iniciarLeitura(isbn);
    abrirPdf(isbn);
  } catch (error) {
    console.error("Ocorreu um erro no processo de 'Ler agora':", error);
    alert("Ocorreu um erro ao tentar iniciar a leitura.");
  }
};

const capaUrl = `https://covers.openlibrary.org/b/isbn/${isbn}-M.jpg`;

// --- Ciclo de Vida do Componente ---

onMounted(async () => {
  await getLivroInfo(isbn);
  await getUserId();
  
  if (userId.value) {
    await iniciarLeitura(isbn);
  } else {
    selectedStatus.value = options.value.find(o => o.code === 'NAOINICIADO');
  }
  
  isDataLoaded.value = true;
});
</script>

<template>
  <div class="min-h-screen bg-[#e6f7fa] font-sans">
    <header class="text-white bg-[#0f8ebd] flex justify-around py-3">
      <RouterLink to="/TelaInicial">
        <div class="text-5xl flex ">
          <h1>Leia+</h1>
          <img src="../../assets/capivara.svg" alt="" />
        </div>
      </RouterLink>
    </header>

    <div class="flex flex-row max-w-5xl mx-auto bg-white rounded-2xl p-6 mt-16">
      <div class="mr-10 flex-shrink-0">
        <img class="rounded-2xl w-52 h-auto" :src="capaUrl" alt="Capa do livro" />
      </div>
      <div class="flex-1 gap-1">
        <div class="flex gap-5 align-middle items-center">
          <RouterLink to="/TelaInicialProfessor">
            <img src="../../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8" />
          </RouterLink>
          <h1 class="text-2xl font-bold text-[28px]">{{ livroInfo.titulo }}</h1>
        </div>

        <p class="mt-2">
          {{ descricaoLivro }}
        </p>

        <div class="flex gap-10 items-center mt-4">
          <button
            class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600"
            @click="handleLerAgora()"
          >
            Ler agora
          </button>

          <Rating v-model="value" />
          <Select
            v-model="selectedStatus"
            :options="options"
            optionLabel="name"
            placeholder="Status do livro"
            class="w-full md:w-56"
          />
        </div>
      </div>
    </div>
  </div>
</template>
