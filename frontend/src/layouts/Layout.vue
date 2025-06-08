<script setup>

</script>

<template>
    
    <header class="text-white bg-[#2596BE] flex justify-around py-3 items-center">
        <div class="text-5xl flex ">
            <h1 class="">Leia+</h1>
            <img src="../assets/capivara.svg" alt="">
        </div>
        <div class="text-3xl flex gap-5">
            <button class="bg-white text-sky-400 text-[20px] font-semibold py-2 px-4 rounded-lg border-2 border-sky-400 shadow-md hover:bg-sky-50 transition text-2xl">Solicite já</button>
            <button class="bg-white text-sky-400 text-[20px] font-semibold py-2 px-4 rounded-lg border-2 border-sky-400 shadow-md hover:bg-sky-50 transition text-2xl">Entrar</button>
        </div>
    </header>
    <slot></slot>
    <footer class="h-100 bg-[#95e78a]">
        <h1>Aqui vai ficar o footer da página</h1>
    </footer>
</template>