<script setup>
import Footer from '../components/Footer.vue'
const scrollToForm = () => {
    document.getElementById('formulario').scrollIntoView({ behavior: 'smooth' });
}
</script>

<template>

    <body class="bg-gradient-to-b from-white to-[#E0F7FF]">
        <slot></slot>
        <Footer></Footer>
    </body>
</template>