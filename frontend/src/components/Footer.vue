<template>
    <footer class="bg-[#0f8ebd] text-gray-300 py-16 px-4 sm:px-8 lg:px-16 relative">
        <div class="max-w-7xl mx-auto flex flex-col lg:flex-row justify-between items-start gap-12">

            <div class="flex flex-col items-start lg:w-2/5">
                <div class="flex  items-center">
                    <h5 class="text-m mb-6 text-white ">Leia+</h5>
                    <img src="../assets/capivara.svg" alt="integre.me logo" class="h-10 mb-6">
                </div>

                <p class="text-m leading-relaxed text-white max-w-md">
                    Nosso propósito é transformar a experiência de leitura e aprendizado, superando os desafios da
                    desigualdade no acesso a materiais didáticos e o crescente desinteresse pela leitura tradicional.
                </p>
            </div>

            <div class="flex flex-col gap-4 lg:w-1/4">
                <div>
                    <h5 class="font-semibold text-gray-100 mb-2">Endereço</h5>
                    <p class="text-sm text-white">DC - UFRPE, Recife - PE </p>
                </div>
                <div>
                    <h5 class="font-semibold text-gray-100 mb-2">E-Mail</h5>
                    <a href="mailto:contato@integre.me"
                        class="text-sm text-white transition-colors">leiamaisescolar@gmail.com</a>
                </div>
                <div>
                    <h5 class="font-semibold text-gray-100 mb-2">Telefone</h5>
                    <a href="tel:+5511954391023" class="text-sm text-white ">+55 81 9 8312-4412</a>
                </div>
            </div>
        </div>

        <div class="fixed bottom-8 right-8 flex flex-col space-y-4 z-50">
            <button v-show="showScrollButton" @click="scrollToTop"
                class="bg-gray-700 hover:bg-gray-600 text-white p-3 rounded-full shadow-lg transition-all duration-300">
                <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                    xmlns="http://www.w3.org/2000/svg">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 10l7-7m0 0l7 7m-7-7v18">
                    </path>
                </svg>
            </button>
            <a href="https://wa.me/5581983124412?text=Olá! Gostaria de saber mais sobre o Leia Mais Escolar."
                target="_blank" aria-label="WhatsApp"
                class="bg-green-500 hover:bg-green-600 text-white p-3 rounded-full shadow-lg transition-colors flex items-center justify-center">
                <img :src="whatsappIcon" alt="WhatsApp icon" class="w-6 h-6">
            </a>
        </div>
    </footer>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue';
import whatsappIcon from '../assets/logo-whatsapp.svg';

const showScrollButton = ref(false);

const handleScroll = () => {
    const scrollThreshold = 200;
    showScrollButton.value = window.scrollY > scrollThreshold;
};

const scrollToTop = () => {
    window.scrollTo({ top: 0, behavior: 'smooth' });
};


onMounted(() => {
    window.addEventListener('scroll', handleScroll);
    handleScroll();
});

onUnmounted(() => {
    window.removeEventListener('scroll', handleScroll);
});
</script>

<style scoped></style>