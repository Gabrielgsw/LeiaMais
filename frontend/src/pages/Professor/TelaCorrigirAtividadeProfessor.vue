<script setup>
import { ref } from 'vue'

const aluno = ref({
    nome: 'Anna da Silva',
    atividade: 'Atividade 01 - como saber o que é cada coisa?',
    livro: 'Harry Potter e a Pedra Filosofal',
    perguntas: [
        'tste11',
        'teste3233',
        'teste3'
    ],
    respostas: [
        'Harry quis proteger a Pedra Filosofal de quem quisesse usá-la para o mal.',
        'A amizade ajudou nos desafios, pois eles se apoiaram e usaram suas habilidades juntos.',
        'O Chapéu Seletor queria colocá-lo na Sonserina, mas respeitou sua escolha pela Grifinória.'
    ]
})
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

const correcao = ref(Array(aluno.value.respostas.length).fill(''))
const enviado = ref(false)

function enviarCorrecao() {
    enviado.value = true
    console.log('Correções enviadas:', correcao.value)
}
</script>

<template>
    <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
        <RouterLink to="/TelaInicial">
            <div class="text-5xl flex">
                <h1>Leia+</h1>
                <img src="../../assets/capivara.svg" alt="" />
            </div>
        </RouterLink>
    </header>

    <div class="max-w-5xl mx-auto bg-white shadow-md rounded-lg p-6 mt-16 mb-16">
        <div class="flex justify-between">
            <div class="flex flex-col gap-4 mb-4">
                <div class="flex gap-4">
                    <button @click="$router.back()" class="text-2xl">
                        <img src="../../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8" />
                    </button>
                    <h1 class="text-2xl font-bold">Correção de Respostas</h1>
                </div>

                <div class="flex gap-4">
                    <label class="shrink-0">Aluno: </label>
                    <p><strong>{{ aluno.nome }}</strong></p>
                </div>

                <div class="flex gap-4">
                    <label class="shrink-0">Título da atividade: </label>
                    <p><strong>{{ aluno.atividade }}</strong></p>
                </div>

                <div class="flex gap-4">
                    <label class="shrink-0">Livro: </label>
                    <p><strong>{{ aluno.livro }}</strong></p>
                </div>
            </div>
            <div>
                <img class="w-[110px] h-[130px] rounded-sm" src="../../assets/livros-teste/hattypotter.jpg" alt="">
            </div>
        </div>

        <div v-for="(resposta, index) in aluno.respostas" :key="index" class="bg-white shadow p-4 mb-5 rounded-xl">
            <p class=" mb-2 font-bold ">Pergunta {{ index + 1 }}: {{ questions[index].text }}</p>
            <p class=" mb-2 font-normal "><span class="font-semibold">Resposta:</span> {{ resposta }}</p>

            <label class="block font-medium mb-1">Comentário e Nota de 0 a 10:</label>
            <textarea v-model="correcao[index]" placeholder="Escreva seu feedback aqui..."
                class="w-full p-2 border rounded resize-y min-h-[80px]"></textarea>
            <input class=" p-2 border rounded w-[60px] h-[30px]" min="0" max="10" type="number">
        </div>

        <div class="flex justify-end mt-5">
            <button @click="enviarCorrecao" class="bg-[#359DFF] text-white px-4 py-1 rounded hover:bg-blue-600">
                Enviar Correção
            </button>
        </div>

        <div v-if="enviado" class="mt-4 bg-green-100 p-4 rounded">
            <h2 class="text-lg font-bold">Correções enviadas:</h2>
            <ul class="list-disc pl-6">
                <li v-for="(feedback, idx) in correcao" :key="idx">
                    Pergunta {{ idx + 1 }}: {{ feedback }}
                </li>
            </ul>
        </div>
    </div>
</template>
