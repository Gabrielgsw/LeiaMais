<!-- src/components/FormBuilder.vue -->
<template>

    <div class="flex-1 max-w-2xl mx-auto p-4 space-y-4">

        <div class="flex gap-4 items-center">
            <label for="" class="shrink-0">Digite o título da atividade</label>
            <input type="text"  v-model="form.nome" class="bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 w-full">
        </div>
        <div class="flex gap-10 items-center">
            <label for="" class="shrink-0">Selecione o livro da atividade</label>
            <Combobox by="label" v-model="form.livro">
                <ComboboxAnchor>
                    <div class="relative w-full max-w-sm items-center">
                        <ComboboxInput class="pl-9" :display-value="(val) => val?.label ?? ''"
                            placeholder="Select framework..." />
                        <span class="absolute start-0 inset-y-0 flex items-center justify-center px-3">
                            <Search class="size-4 text-muted-foreground" />
                        </span>
                    </div>
                </ComboboxAnchor>

                <ComboboxList>
                    <ComboboxEmpty>
                        Nenhum livro encontrado.
                    </ComboboxEmpty>

                    <ComboboxGroup>
                        <ComboboxItem v-for="framework in frameworks" :key="framework.value" :value="framework">
                            {{ framework.label }}

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
            <p class="text-sm text-gray-600">Tipo: Resposta curta</p>
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

<script setup>

import { reactive } from 'vue'
import { Check, Search } from 'lucide-vue-next'
import { cn } from '../lib/utils'
import { Combobox, ComboboxAnchor, ComboboxEmpty, ComboboxGroup, ComboboxInput, ComboboxItem, ComboboxItemIndicator, ComboboxList } from '@/components/ui/combobox'

const frameworks = [
    { value: 'O Senhor dos anéis', label: 'O Senhor dos anéis' },
    { value: 'Percy Jackson', label: 'Percy Jackson' },
    { value: 'The witcher', label: 'The witcher' },
    { value: 'Harry potter e o prisioneiro de Azkaban', label: 'Harry potter' },
    { value: 'As crônicas de gelo e fogo', label: 'As crônicas de gelo e fogo' },
]

const questions = reactive([])

const form = reactive({
  nome: '',         // Título
  livro: null,   // Lista de perguntas
})

function addQuestion() {
    questions.push({
        text: ''
    })
}
async function handleSubmit() {
  const enunciados = questions.map(q => q.text)

  const payload = {
    nome: form.nome,
    enunciado: enunciados,
    feedback: "",
    livro: {
      nome: form.livro?.value || ''
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
