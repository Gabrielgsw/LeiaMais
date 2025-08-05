<script setup lang="ts">
import { ref, onMounted, watch } from 'vue';
import { useRoute, RouterLink } from 'vue-router';
import axios from 'axios';
import Rating from 'primevue/rating';
import Select from 'primevue/select';

// --- 1. Definição de Tipos ---
interface Livro {
    titulo: string;
    autor: string;
    descricao?: string;
}

interface Atividade {
    id: string;
    nome: string;
}

// Nova interface para representar a entidade Leitura
interface Leitura {
    id: string;
    status: string; // Ex: 'NI', 'EL', 'C'
    avaliacao?: number; // Adicionado para a avaliação
}

// --- 2. Estado Reativo ---
const route = useRoute();
const isbn = route.params.isbn as string;

const livro = ref<Livro | null>(null);
const atividadeId = ref<string | null>(null);
const alunoId = ref<string | null>(null);
const leitura = ref<Leitura | null>(null); // Estado para guardar a leitura atual
const loading = ref<boolean>(true);
const error = ref<string | null>(null);

const capaUrl = `https://covers.openlibrary.org/b/isbn/${isbn}-M.jpg`;

const value = ref(0); // Para a avaliação
const selectedStatus = ref<{ name: string, code: string } | null>(null);
const options = ref([
    { name: 'Não iniciado', code: 'NAOINICIADO' },
    { name: 'Em leitura', code: 'EMLEITURA' },
    { name: 'Concluído', code: 'CONCLUIDO' }
]);

// --- 3. Funções ---

async function carregarDados() {

    loading.value = true;
    error.value = null;
    try {
        // Busca o ID do aluno primeiro, pois é necessário para as outras chamadas
        // (Embora o backend não precise mais para POST/PUT de leitura, ainda pode ser útil para outras chamadas)
        const idUsuarioResponse = await axios.get<string>(`http://localhost:8080/api/auth/me/id`, { withCredentials: true });
        alunoId.value = idUsuarioResponse.data;

        if (!alunoId.value) {
            throw new Error("Não foi possível obter o ID do utilizador.");
        }

        // Busca os dados do livro, atividade e status da leitura em paralelo
        const [livroResponse, atividadeResponse, leituraResponse] = await Promise.all([
            axios.get<Livro>(`http://localhost:8080/livros/isbn/${isbn}`, { withCredentials: true }),
            // Tenta buscar a atividade (pode falhar com 404)
            axios.get<Atividade>(`http://localhost:8080/api/atividades/livro/${isbn}`, { withCredentials: true }).catch(() => null),
            // Tenta buscar o status da leitura (endpoint ajustado para leitura/{idUser}/{isbn} )
            axios.get<Leitura>(`http://localhost:8080/leituras/leitura/${alunoId.value}/${isbn}`, { withCredentials: true }).catch(() => null)
        ]);

        livro.value = livroResponse.data;
        
        if (atividadeResponse?.data) {
            atividadeId.value = atividadeResponse.data.id;
        }
        
        if (leituraResponse?.data) {
            leitura.value = leituraResponse.data;
            // Define o valor inicial do seletor com base no status guardado
            selectedStatus.value = options.value.find(opt => opt.code === leituraResponse.data.status) || null;
            value.value = leituraResponse.data.avaliacao || 0; // Define a avaliação se existir
        }

        enviarAtualizacaoLeitura();
    } catch (err: any) {
        console.error("Erro ao carregar dados:", err);
        error.value = "Não foi possível carregar os detalhes do livro. Verifique se está logado.";
    } finally {
        loading.value = false;
    }
}

// Função para enviar atualização de status e avaliação
async function enviarAtualizacaoLeitura() {
    
    // Garante que temos um status selecionado e um ISBN para o POST inicial
    if (!selectedStatus.value || !isbn) return;

    // A avaliação é opcional para o PUT, mas enviamos 0 se não houver valor
    const avaliacaoParaEnviar = value.value || 0; 

    console.log(`A enviar atualização: Status=${selectedStatus.value.code}, Avaliacao=${avaliacaoParaEnviar}`);

    try {
        if (leitura.value && leitura.value.id) {
            // Se já existe uma leitura (tem ID), fazemos PUT
            // URL: /leituras/{id}/{status}/{avalicao}
            const response = await axios.put<Leitura>(
                `http://localhost:8080/leituras/${leitura.value.id}/${selectedStatus.value.code}/${avaliacaoParaEnviar}`,
                {}, // Corpo da requisição vazio, pois os dados vão na URL
                { withCredentials: true }
            );
            leitura.value = response.data; // Atualiza o estado da leitura com a resposta
        } else {
            // Se não existe (leitura.value é null), cria uma nova (POST)
            // URL: /leituras/{ISBN}
            console.log('ISBN -->'+ isbn);
            const response = await axios.post<Leitura>(
                `http://localhost:8080/leituras/${isbn}`,
                 // Corpo da requisição vazio, pois o ISBN vai na URL
                { withCredentials: true }
            );
            leitura.value = response.data; // Guarda a nova leitura no estado
            
            // Depois de criar a leitura, se o status inicial for diferente de "Não Iniciado"
            // ou se houver avaliação, precisamos fazer um PUT subsequente.
            if (selectedStatus.value.code !== 'NAOINICIADO' || avaliacaoParaEnviar > 0) {
                 // É necessário um segundo PUT para atualizar o status e avaliação,
                 // pois o POST inicial apenas cria a leitura com status padrão.
                 const updatedResponse = await axios.put<Leitura>(
                    `http://localhost:8080/leituras/${leitura.value.id}/${selectedStatus.value.code}/${avaliacaoParaEnviar}`,
                    {},
                    { withCredentials: true }
                 );
                 leitura.value = updatedResponse.data;
            }
        }
        // Poderia adicionar uma notificação de sucesso aqui (ex: toast)
    } catch (err) {
        console.error("Erro ao atualizar status da leitura:", err);
        // Poderia adicionar uma notificação de erro aqui
        //error.value = "Erro ao salvar o progresso da leitura. Tente novamente.";
    }
}

// Observadores para chamar a função de atualização quando status ou avaliação mudam
watch(selectedStatus, enviarAtualizacaoLeitura);
watch(value, enviarAtualizacaoLeitura);


function abrirPdf(isbn: string) {
    
    const url = `http://localhost:8080/livros/${isbn}/pdf`;
    window.open(url, '_blank');
}

function alertaSemAtividade() {
    alert('Não há atividades disponíveis para este livro no momento.');
}

// --- 4. Hook do Ciclo de Vida ---
onMounted(carregarDados);
</script>

<template>
    <div class="min-h-screen bg-[#e6f7fa] font-sans">
        <header class="text-white bg-[#0f8ebd] flex justify-around py-3">
            <RouterLink to="/TelaInicialAluno">
                <div class="text-5xl flex items-center">
                    <h1>Leia+</h1>
                    <img src="../../assets/capivara.svg" alt="Logo" class="w-12 h-12 ml-2" />
                </div>
            </RouterLink>
        </header>

        <div v-if="loading" class="text-center py-20 text-gray-500">
            <p>A carregar detalhes do livro...</p>
        </div>
        <div v-else-if="error" class="max-w-5xl mx-auto text-center py-20 text-red-600 bg-red-50 p-6 mt-16 rounded-lg">
            <h2 class="font-bold text-xl mb-2">Ocorreu um erro</h2>
            <p>{{ error }}</p>
        </div>
        
        <div v-else-if="livro" class="flex flex-col md:flex-row max-w-5xl mx-auto bg-white rounded-2xl p-6 mt-16 shadow-lg">
            <div class="mr-10 flex-shrink-0 text-center">
                <img class="rounded-2xl w-52 h-auto shadow-md mx-auto" :src="capaUrl" alt="Capa do livro" 
                    onerror="this.onerror=null;this.src='https://placehold.co/208x320/e0e0e0/757575?text=Capa'" />
            </div>
            
            <div class="flex-1 gap-1 mt-6 md:mt-0">
                <div class="flex gap-5 items-center">
                    <RouterLink to="/TelaInicialAluno">
                        <img src="../../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8 hover:opacity-75 transition-opacity" />
                    </RouterLink>
                    <h1 class="text-3xl font-bold text-gray-800">{{ livro.titulo }}</h1>
                </div>
                <p class="text-lg text-gray-600 mt-1 ml-14">{{ livro.autor }}</p>

                <p class="mt-4 text-gray-700">
                    {{ livro.descricao || 'Descrição não disponível.' }}
                </p>

                <div class="flex flex-wrap gap-4 items-center mt-6">
                    <button class="bg-[#359DFF] text-white px-5 py-2 rounded-lg shadow hover:bg-blue-600 transition-colors"
                            @click="abrirPdf(isbn)" >
                        Ler agora
                    </button>

                    <div class="flex items-center gap-4 p-2 bg-gray-50 rounded-lg">
                        <Rating v-model="value" :cancel="false" />
                        <Select v-model="selectedStatus" :options="options" optionLabel="name" placeholder="Status do livro"
                                class="w-full md:w-56" />
                    </div>

                    <RouterLink v-if="atividadeId" :to="`/TelaResolverAtividade/${atividadeId}`">
                        <button class="bg-green-500 text-white px-5 py-2 rounded-lg shadow hover:bg-green-600 transition-colors">
                            Fazer atividade
                        </button>
                    </RouterLink>
                    <button v-else @click="alertaSemAtividade" class="bg-gray-400 text-white px-5 py-2 rounded-lg shadow cursor-pointer hover:bg-gray-500 transition-colors">
                        Fazer atividade
                    </button>
                </div>
            </div>
        </div> 
    </div>
</template>
