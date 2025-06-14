<script setup>
import Footer from '../components/Footer.vue'
import { RouterLink } from 'vue-router';
const scrollToForm = () => {
    document.getElementById('formulario').scrollIntoView({ behavior: 'smooth' });
}


</script>

<template>

    <body class="bg-gradient-to-b from-white to-[#E0F7FF]">
        <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
            <RouterLink to="/">
                <div class="text-5xl flex ">
                    <h1 class="">Leia+</h1>
                    <img src="../assets/capivara.svg" alt="">
                </div>
            </RouterLink>

            <div class="text-3xl flex gap-5">
                <button @click="scrollToForm"
                    class="bg-white text-sky-600 text-[20px] font-semibold py-2 px-4 rounded-lg border-2 border-sky-600 shadow-sm hover:bg-sky-50 transition text-2xl">Solicite
                    já</button>
                <RouterLink to="/SelecaoLogin"
                    class="bg-white text-sky-600 text-[20px] font-semibold py-2 px-4 rounded-lg border-2 border-sky-600 shadow-sm hover:bg-sky-50 transition text-2xl">
                    Entrar</RouterLink>
            </div>
        </header>
        <slot></slot>
        <Footer></Footer>
    </body>
</template>