<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'
import { RouterLink, useRoute, useRouter } from 'vue-router'
import axios from 'axios'

type Aluno = {
    id: string
    cpf: string
    nome: string
    email: string
    senha: string
    cargo: string
    dataNascimento: string
    matricula: string
    pontos: number
    qtdLivrosLidos: number
    qtdLivrosFavoritos: number
}

type StatusCorrecao = 'nao-corrigido' | 'iniciado' | 'finalizado'

type AlunoCorrecao = Aluno & {
    statusCorrecao: StatusCorrecao
}

const route = useRoute()
const router = useRouter()
const alunosDaTurma = ref<AlunoCorrecao[]>([])

// Dados recebidos da tela anterior
const atividadeId = computed(() => route.params.atividadeId as string)
const nomeAtividade = computed(() => route.query.nomeAtividade as string || 'Atividade não especificada')
const descricaoAtividade = computed(() => route.query.descricaoAtividade as string || '')
const dataVencimento = computed(() => route.query.dataVencimento as string || '')
const nomeTurma = computed(() => route.query.nomeTurma as string || '1° ano - ensino fundamental')

// Função para buscar alunos da turma
async function carregarAlunosDaTurma() {
    try {
        const response = await axios.get('http://localhost:8080/alunos')
        if (response.status !== 200) {
            throw new Error('Erro ao buscar alunos')
        }
        
        // Todos os alunos iniciam com status "nao-corrigido"
        alunosDaTurma.value = response.data.slice(0, 8).map((aluno: Aluno) => ({
            ...aluno,
            statusCorrecao: 'nao-corrigido' as StatusCorrecao
        }))
    } catch (error) {
        console.error("Erro ao carregar alunos:", error)
        // Dados de exemplo para desenvolvimento - reduzido para apenas 2 alunos
        alunosDaTurma.value = [
            {
                id: '1',
                nome: 'Ana Silva',
                cpf: '123.456.789-01',
                email: 'ana@email.com',
                senha: '',
                cargo: 'aluno',
                dataNascimento: '2010-05-15',
                matricula: '2024001',
                pontos: 150,
                qtdLivrosLidos: 5,
                qtdLivrosFavoritos: 2,
                statusCorrecao: 'nao-corrigido' as StatusCorrecao
            },
            {
                id: '2',
                nome: 'Bruno Santos',
                cpf: '234.567.890-12',
                email: 'bruno@email.com',
                senha: '',
                cargo: 'aluno',
                dataNascimento: '2010-08-22',
                matricula: '2024002',
                pontos: 200,
                qtdLivrosLidos: 8,
                qtdLivrosFavoritos: 3,
                statusCorrecao: 'nao-corrigido' as StatusCorrecao
            }
        ]
    }
}

// Função para alterar status de correção
function alterarStatusCorrecao(alunoId: string, novoStatus: StatusCorrecao) {
    const aluno = alunosDaTurma.value.find(a => a.id === alunoId)
    if (aluno) {
        aluno.statusCorrecao = novoStatus
        console.log(`Status do aluno ${aluno.nome} alterado para: ${novoStatus}`)
        
        // Aqui você pode fazer uma chamada à API para salvar o status
        // salvarStatusCorrecao(alunoId, atividadeId.value, novoStatus)
    }
}

// Função para ir para tela de correção individual
function irParaCorrecao(alunoId: string) {
    const aluno = alunosDaTurma.value.find(a => a.id === alunoId)
    if (aluno) {
        // Atualiza status para "iniciado" quando clica em corrigir
        alterarStatusCorrecao(alunoId, 'iniciado')
        
        // Navega para tela de correção individual passando dados do aluno e atividade
        router.push({
            name: 'TelaCorrigirAtividadeProfessor',
            params: {
                alunoId: alunoId,
                atividadeId: atividadeId.value
            },
            query: {
                nomeAluno: aluno.nome,
                matriculaAluno: aluno.matricula,
                nomeAtividade: nomeAtividade.value,
                descricaoAtividade: descricaoAtividade.value,
                nomeTurma: nomeTurma.value
            }
        })
        
        console.log(`Navegando para correção do aluno: ${aluno.nome} - Atividade: ${nomeAtividade.value}`)
    }
}

// Função para obter cor do status para o select
function getStatusColor(status: StatusCorrecao): string {
    switch (status) {
        case 'nao-corrigido': return 'bg-gray-100 text-gray-700'
        case 'iniciado': return 'bg-yellow-100 text-yellow-700'
        case 'finalizado': return 'bg-green-100 text-green-700'
        default: return 'bg-gray-100 text-gray-700'
    }
}

// Função para obter texto do status
function getStatusText(status: StatusCorrecao): string {
    switch (status) {
        case 'nao-corrigido': return 'Não corrigido'
        case 'iniciado': return 'Iniciado'
        case 'finalizado': return 'Finalizado'
        default: return 'Não corrigido'
    }
}

// Função para voltar para a tela anterior
function voltarTela() {
    router.back()
}

onMounted(() => {
    carregarAlunosDaTurma()
    console.log('Dados recebidos:', {
        atividadeId: atividadeId.value,
        nomeAtividade: nomeAtividade.value,
        nomeTurma: nomeTurma.value
    })
})
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

        <div class="max-w-6xl mx-auto bg-white shadow-md rounded-lg p-6 mt-16">
            <!-- Header com botão voltar e título -->
            <div class="flex items-center justify-between mb-6">
                <div class="flex items-center gap-3">
                    <button @click="voltarTela" class="flex items-center">
                        <img src="../../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8" />
                    </button>
                    <div>
                        <h2 class="text-[28px] font-bold">Correção de Atividade</h2>
                        <p class="text-lg text-gray-600">Turma: {{ nomeTurma }}</p>
                    </div>
                </div>
            </div>

            <!-- Informações da atividade -->
            <div class="bg-blue-50 p-4 rounded-lg mb-6">
                <h3 class="text-xl font-semibold text-[#0f8ebd] mb-2">Atividade:</h3>
                <p class="text-lg font-medium">{{ nomeAtividade }}</p>
                <p v-if="descricaoAtividade" class="text-sm text-gray-600 mt-1">{{ descricaoAtividade }}</p>
                <p v-if="dataVencimento" class="text-sm text-gray-600 mt-1">
                    <strong>Vencimento:</strong> {{ new Date(dataVencimento).toLocaleDateString('pt-BR') }}
                </p>
            </div>

            <!-- Lista de alunos para correção -->
            <div class="mb-4">
                <h3 class="text-[20px] font-bold mb-4">Alunos da Turma</h3>
                
                <!-- Cabeçalho da tabela -->
                <div class="grid grid-cols-5 font-bold px-4 py-3 bg-blue-100 rounded-md mb-2">
                    <span>Nome</span>
                    <span>Matrícula</span>
                    <span>CPF</span>
                    <span class="text-center">Status</span>
                    <span class="text-center">Ação</span>
                </div>

                <!-- Lista de alunos -->
                <div class="space-y-2">
                    <div 
                        v-for="aluno in alunosDaTurma" 
                        :key="aluno.id"
                        class="grid grid-cols-5 items-center px-4 py-3 bg-white border border-gray-200 rounded-md hover:bg-gray-50 transition-colors"
                    >
                        <!-- Nome -->
                        <span class="font-medium">{{ aluno.nome }}</span>
                        
                        <!-- Matrícula -->
                        <span class="text-gray-600">{{ aluno.matricula }}</span>
                        
                        <!-- CPF -->
                        <span class="text-gray-600">{{ aluno.cpf }}</span>
                        
                        <!-- Status com dropdown nativo -->
                        <div class="flex justify-center">
                            <select 
                                :value="aluno.statusCorrecao"
                                @change="(event) => alterarStatusCorrecao(aluno.id, (event.target as HTMLSelectElement).value as StatusCorrecao)"
                                class="px-3 py-1 border border-gray-300 rounded-md text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                                :class="getStatusColor(aluno.statusCorrecao)"
                            >
                                <option value="nao-corrigido" class="text-gray-600">Não corrigido</option>
                                <option value="iniciado" class="text-yellow-600">Iniciado</option>
                                <option value="finalizado" class="text-green-600">Finalizado</option>
                            </select>
                        </div>
                        
                        <!-- Botão Corrigir -->
                        <div class="flex justify-center">
                            <RouterLink to="/TelaCorrigirAtividadeProfessor" class="flex justify-center">
                                <button
                                    @click="irParaCorrecao(aluno.id)"
                                    class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600 transition-colors"
                                    :class="{ 
                                        'bg-green-500 hover:bg-green-600': aluno.statusCorrecao === 'finalizado',
                                        'bg-yellow-500 hover:bg-yellow-600': aluno.statusCorrecao === 'iniciado'
                                    }"
                                >
                                    {{ aluno.statusCorrecao === 'finalizado' ? 'Revisar' : 'Corrigir' }}
                                </button>
                            </RouterLink>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Estatísticas de correção -->
            <div class="mt-8 p-4 bg-gray-50 rounded-lg">
                <h4 class="font-semibold mb-2">Progresso da Correção:</h4>
                <div class="flex gap-6 text-sm">
                    <span class="text-gray-600">
                        Não corrigidos: {{ alunosDaTurma.filter(a => a.statusCorrecao === 'nao-corrigido').length }}
                    </span>
                    <span class="text-yellow-600">
                        Iniciados: {{ alunosDaTurma.filter(a => a.statusCorrecao === 'iniciado').length }}
                    </span>
                    <span class="text-green-600">
                        Finalizados: {{ alunosDaTurma.filter(a => a.statusCorrecao === 'finalizado').length }}
                    </span>
                </div>
                <div class="mt-2 text-sm text-gray-600">
                    <strong>Total de alunos:</strong> {{ alunosDaTurma.length }}
                </div>
            </div>
        </div>
    </div>
</template>