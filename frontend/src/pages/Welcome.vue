<script setup>
import { reactive } from 'vue'

const form = reactive({
    nome: '',
    email: '',
    numero: '',
    mensagem: ''
})

const handleSubmit = () => {
    // Aqui você pode enviar os dados via fetch/axios
    alert(`Formulário enviado!\nNome: ${form.nome}\nEmail: ${form.email}\nNúmero: ${form.numero}\nMensagem: ${form.mensagem} `)
}
</script>

<template>
    <div class="h-180 bg-[#E0F7FF] flex flex-row items-center justify-around">
        <div>
            <div class="flex flex-col items-center text-[#0084FF] w-200 text-center">
                <h1 class="text-[48px] font-bold">
                    Se adapte a leitura igual a uma capivara se adapta a água!
                </h1>
                <p class="text-[18px] font-medium">
                    Aprimore a leitura de seus alunos de maneira digital na sua instituição
                </p>
            </div>
        </div>
        <div class="mt-10 md:mt-0">
            <img class="w-[300px] md:w-[400px] h-auto capivara-img" src="../assets/Capivara-homepage.svg"
                alt="Capivara feliz" />
        </div>
    </div>
    <div class="mx-auto w-5/6 my-8 p-14 flex flex-col items-center">
        <h2 class="text-[36px] font-semibold text-[#007bff] mb-12 text-left w-full">Nossos Serviços</h2>
        <div class="grid grid-cols-1 md:grid-cols-2 gap-10 w-full max-w-3xl p-4">
            <div
                class="w-full bg-emerald-200 shadow-md rounded-lg flex flex-col items-center justify-start text-gray-700 p-6">
                <img src="../assets/icones-serviço/card01.png" alt="Ícone" class="w-18 h-18 mb-4">
                <h3 class="text-2xl font-bold mb-2 text-center">Descubra o prazer de ler!</h3>
                <p class="text-base text-center">Desperte nos alunos o amor pela leitura com atividades interativas, histórias envolventes e um ambiente pensado para encantar desde o primeiro clique.</p>
            </div>
            <div
                class="w-full bg-amber-200 shadow-md rounded-lg flex flex-col items-center justify-start text-gray-700 p-6">
                <img src="../assets/icones-serviço/card02.png" alt="Ícone" class="w-18 h-18 mb-4">
                <h3 class="text-2xl font-bold mb-2 text-center">Ideal para professores e alunos</h3>
                <p class="text-base text-center">Acompanhe e avalie o progresso de cada aluno com facilidade. Nosso sistema transforma a leitura em uma experiência organizada e eficiente.</p>
            </div>
            <div
                class="w-full bg-rose-200 shadow-md rounded-lg flex flex-col items-center justify-start text-gray-700 p-6">
                <img src="../assets/icones-serviço/card03.png" alt="Ícone" class="w-18 h-18 mb-4">
                <h3 class="text-2xl font-bold mb-2 text-center">Diversão que educa</h3>
                <p class="text-base text-center">Com gamificação, pontos e leituras encantadoras, as crianças aprendem brincando. Um jeito leve e estimulante de mergulhar no universo dos livros.</p>
            </div>
            <div
                class="w-full bg-orange-200 shadow-md rounded-lg flex flex-col items-center justify-start text-gray-700 p-6">
                <img src="../assets/icones-serviço/card04.png" alt="Ícone" class="w-18 h-18 mb-4">
                <h3 class="text-2xl font-bold mb-2 text-center">Feito para escolas que transformam</h3>
                <p class="text-base text-center">Leve inovação para a sala de aula com uma plataforma segura, intuitiva e cheia de magia.</p>
            </div>
        </div>
    </div>

    <div id="formulario" class=" scroll-mt-[108px] flex justify-center mt-30 mb-50">
        <div class="bg-white rounded-xl shadow-md p-8 w-full max-w-md">
            <h2 class="text-2xl font-bold text-center text-sky-600 mb-6">Solicite já</h2>

            <form @submit.prevent="handleSubmit" class="space-y-4">
                <div>
                    <label class="block text-sm font-medium text-sky-700 mb-1">
                        Nome <span class="text-red-500">*</span>
                    </label>
                    <input v-model="form.nome" type="text" placeholder="Seu nome"
                        class="w-full px-3 py-2 border border-blue-300 rounded focus:outline-none focus:ring-2 focus:ring-sky-400 bg-blue-50"
                        required>
                </div>

                <div>
                    <label class="block text-sm font-medium text-sky-700 mb-1">
                        E-mail <span class="text-red-500">*</span>
                    </label>
                    <input v-model="form.email" type="email" placeholder="exemplo@email.com"
                        class="w-full px-3 py-2 border border-blue-300 rounded focus:outline-none focus:ring-2 focus:ring-sky-400 bg-blue-50"
                        required>
                </div>

                <div>
                    <label class="block text-sm font-medium text-sky-700 mb-1">
                        Número <span class="text-red-500">*</span>
                    </label>
                    <input v-model="form.numero" type="tel" placeholder="(XX) 9-XXXX-XXXX"
                        class="w-full px-3 py-2 border border-blue-300 rounded focus:outline-none focus:ring-2 focus:ring-sky-400 bg-blue-50"
                        required>
                </div>

                <div>
                    <label class="block text-sm font-medium text-sky-700 mb-1">
                        Mensagem <span class="text-red-500">*</span>
                    </label>
                    <textarea v-model="form.mensagem" placeholder="Sua mensagem..."
                        class="w-full px-3 py-2 border border-blue-300 rounded focus:outline-none focus:ring-2 focus:ring-sky-400 bg-blue-50"
                        required></textarea>
                </div>

                <div class="flex justify-center">
                    <button type="submit"
                        class="bg-sky-500 text-white font-medium px-6 py-2 rounded hover:bg-sky-600 transition">
                        Enviar
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>

<style scoped>
/* Um hover básico só para a capivara dar uma puladinha quando passar o mouse por cima */
@keyframes capivara-hover-fake {
    0% {
        transform: scale(1) rotate(0deg);
        filter: brightness(1);
    }

    50% {
        transform: scale(1.1) rotate(-2deg);
        filter: brightness(1.1);
    }

    100% {
        transform: scale(1) rotate(0deg);
        filter: brightness(1);
    }
}

.capivara-img {
    transition: transform 0.4s ease, filter 0.4s ease;
    animation: capivara-hover-fake 1.5s ease;
}

.capivara-img:hover {
    transform: scale(1.1) rotate(-2deg);
    filter: brightness(1.1);
}
</style>