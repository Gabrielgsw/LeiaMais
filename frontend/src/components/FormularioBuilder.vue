<script setup lang="ts">

import { reactive, ref, computed } from 'vue'
import { Check, Search } from 'lucide-vue-next'
import { cn } from '../lib/utils'
import { Combobox, ComboboxAnchor, ComboboxEmpty, ComboboxGroup, ComboboxInput, ComboboxItem, ComboboxItemIndicator, ComboboxList } from '@/components/ui/combobox'


const livros = [
    {
        "id": "a9f91256-18cf-4629-8725-63a86bcddaef",
        "titulo": "Livro 1",
        "autor": "tedssfwe",
        "editora": "wefwefw",
        "livroUrl": "3424323423423423412",
        "isbn": "32423123"
    },
    {
        "id": "609e6de3-e44f-44de-97bf-55fe4270f96f",
        "titulo": "sdfwefwefwef",
        "autor": "edffgersgsg",
        "editora": "dfgcdfxbcnfgn",
        "livroUrl": "dgwfwfwefwef",
        "isbn": "342534654654"
    }
]

const questions = reactive([])

const nome = ref('')
const livro = ref(null)
const filtroLivro = ref('')

const livrosFiltrados = computed(() => {
    if (!filtroLivro.value) return livros
    return livros.filter(livro => livro.titulo.toLowerCase().includes(filtroLivro.value.toLowerCase()))
})

function addQuestion() {
    questions.push({
        text: ''
    })
}
async function handleSubmit() {
    const enunciados = questions.map(q => q.text)

    const payload = {
        nome: nome.value,
        enunciado: enunciados,
        feedback: "",
        livro: {
            nome: livro.value || ''
        },
        professor: {
            id: "COLOCAR UM ID VÁLIDO JÁ CRIADO AQUI"
        }
    }

    try {
        const res = await fetch("http://localhost:8080/atividades", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(payload),
        })

        if (!res.ok) {
            const errorText = await res.text()
            console.error("Erro completo do servidor:", errorText)
            throw new Error("Erro ao criar atividade")
        }

        const data = await res.json()
        console.log("✔️ Atividade criada:", data)
        alert("Atividade criada com sucesso!")

    } catch (err) {
        console.error("❌ Erro ao enviar:", err)
        alert("Erro ao criar atividade.")
    }
}

function removeQuestion(index) {
    questions.splice(index, 1)
}

function correctMark(qIndex, optIndex) {
    questions[qIndex].correct = optIndex
}

function addOption(qIndex) {
    questions[qIndex].options.push('')
}

function removeOption(qIndex, optIndex) {
    questions[qIndex].options.splice(optIndex, 1)
}
</script>

<template>
    <div class="flex-1 max-w-2xl mx-auto p-4 space-y-4">
        <div class="flex gap-4 items-center">
            <label for="" class="shrink-0">Digite o título da atividade</label>
            <input type="text" v-model="nome"
                class="bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 w-full">
        </div>
        <div class="flex gap-10 items-center">
            <label for="" class="shrink-0">Selecione o livro da atividade</label>
            <Combobox by="label" v-model="livro">
                <ComboboxAnchor>
                    <ComboboxInput type="text" class="w-full focus:outline-none py-2"
                        placeholder="Pesquisar livro..." />
                </ComboboxAnchor>

                <ComboboxList>
                    <ComboboxEmpty>
                        Nenhum livro encontrado.
                    </ComboboxEmpty>

                    <ComboboxGroup>
                        <ComboboxItem v-for="livro in livros" :key="livro.id" :value="livro">
                            {{ livro.titulo }}
                            <ComboboxItemIndicator>
                                <Check :class="cn('ml-auto h-4 w-4')" />
                            </ComboboxItemIndicator>
                        </ComboboxItem>
                    </ComboboxGroup>
                </ComboboxList>
            </Combobox>

        </div>
        <div v-for="(question, index) in questions" :key="index" class="border p-4 rounded-xl space-y-2">
            <input v-model="question.text" placeholder="Digite a pergunta" class="w-full p-2 border rounded" />
            <p class="text-sm text-gray-600"><span class="font-bold">Tipo:</span> Resposta curta</p>
            <div v-if="question.type === 'multiple'" class="space-y-1">
                <button @click="addOption(index)" class="text-blue-500 hover:underline">+ Adicionar opção</button>
            </div>

            <button @click="removeQuestion(index)" class="text-red-600 hover:underline">Excluir pergunta</button>
        </div>


        <button @click="addQuestion" :disabled="questions.length == 3"
            class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 disabled:opacity-50">
            + Nova Pergunta
        </button>
        <div class="flex items-center justify-between mt-4">
            <RouterLink to="/TeladeTurma"><button
                    class="bg-white text-[#359DFF] px-4 py-2 rounded shadow ring-1 ring-[#359DFF] hover:bg-black/5 transition-colors">
                    Cancelar
                </button></RouterLink>
            <RouterLink to="/TeladeTurma">
                <Button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600"
                    @click="handleSubmit">
                    Criar
                </Button>
            </RouterLink>

        </div>



    </div>
</template>