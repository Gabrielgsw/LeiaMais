<template>
    <div class="flex gap-80">
        <div class="flex flex-col gap-4 mb-4 ">
            <div class="flex gap-4">
                <button @click="$router.back()" class="text-2xl">
                    <img src="../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8" />
                </button>
                <h1 class="text-2xl font-bold">Atividade</h1>
            </div>

            <div class="flex gap-4">
                <label for="" class="shrink-0">Título da atividade: </label>
                <p><strong>Atividade 01 - como saber o que é cada coisa?</strong></p>
            </div>
            <div class="flex gap-4">
                <label for="" class="shrink-0">Livro da atividade: </label>
                <p><strong>Harry Potter e a Pedra Filosofal</strong></p>
            </div>
        </div>
        <div>
            <img class="w-[110px] h-[130px] rounded-sm " src="../assets/livros-teste/hattypotter.jpg" alt="">
        </div>
    </div>


    <div v-for="(question, qIndex) in questions" :key="question.id" class="bg-white shadow p-4 mb-5 rounded-xl">


        <div class="space-y-2">
            <p class="font-semibold mb-2">{{ qIndex + 1 }}. {{ question.text }}</p>

            <textarea v-model="answers[qIndex]" placeholder="Digite sua resposta aqui..."
                class="w-full p-2 border rounded resize-y min-h-[100px]"></textarea>
        </div>
    </div>

    <div class="flex justify-between mt-5">
        <RouterLink to="/">
            <button class="bg-[#359DFF] text-white px-4 py-1 rounded hover:bg-blue-600">
                Cancelar
            </button>
        </RouterLink>

        <button @click="submit" class="bg-[#359DFF] text-white px-4 py-1 rounded hover:bg-blue-600">
            Enviar Respostas
        </button>
    </div>
    <!--Respostas enviadas-->
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
        text: 'O que motivou Harry a enfrentar o desafio de atravessar o alçapão para encontrar a Pedra Filosofal?'
    },
    {
        id: 2,
        text: 'Como a amizade entre Harry, Rony e Hermione foi importante durante os desafios no final do livro?'
    },
    {
        id: 3,
        text: 'Explique o papel do Chapéu Seletor na história e por que a decisão dele sobre a casa de Harry foi significativa.'
    }
]

const answers = ref(Array(questions.length).fill(''))
const submitted = ref(false)

function submit() {
    submitted.value = true
    console.log('Respostas:', answers.value)
}
</script>
