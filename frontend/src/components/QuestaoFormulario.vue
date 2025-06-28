<template>

        <h1 class="text-2xl font-bold">Atividade</h1>
        <div class="flex gap-4 items-center">
            <label for="" class="shrink-0">Digite o título da atividade</label>
            <input type="text" class="bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 w-full">
        </div>
        <label for="" class="shrink-0">Selecione o livro da atividade</label>
        <div v-for="(question, qIndex) in questions" :key="question.id" class="bg-white shadow p-4 rounded-xl">
            <p class="font-semibold mb-2">{{ qIndex + 1 }}. {{ question.text }}</p>

            <div class="space-y-2">
                <label v-for="(option, oIndex) in question.options" :key="oIndex" class="flex items-center space-x-2">
                    <input type="radio" :name="'question-' + qIndex" :value="option" v-model="answers[qIndex]"
                        class="text-indigo-600" />
                    <span>{{ option }}</span>
                </label>
            </div>
        </div>
        <div class="flex justify-between">
            <RouterLink to="/">
                <button class="bg-indigo-600 text-white px-4 py-2 rounded hover:bg-indigo-700">
                    Cancelar
                </button>
            </RouterLink>

            <button @click="submit" class="bg-indigo-600 text-white px-4 py-2 rounded hover:bg-indigo-700">
                Enviar Respostas
            </button>
        </div>


        <div v-if="submitted" class="mt-4 bg-green-100 p-4 rounded">
            <h2 class="text-lg font-bold">Respostas enviadas:</h2>
            <ul class="list-disc pl-6">
                <li v-for="(resp, idx) in answers" :key="idx">Pergunta {{ idx + 1 }}: {{ resp }}</li>
            </ul>
        </div>

</template>

<script setup>
import { ref } from 'vue'

const questions = [
    {
        id: 1,
        text: 'Qual a capital do Brasil?',
        options: ['São Paulo', 'Brasília', 'Rio de Janeiro', 'Salvador']
    },
    {
        id: 2,
        text: 'Qual é o resultado de 2 + 2?',
        options: ['3', '4', '5', '6']
    },
    {
        id: 3,
        text: 'Qual linguagem é usada com Vue?',
        options: ['PHP', 'Python', 'JavaScript', 'C#']
    }
]

const answers = ref(Array(questions.length).fill(''))
const submitted = ref(false)

function submit() {
    submitted.value = true
    console.log('Respostas:', answers.value)
}
</script>
