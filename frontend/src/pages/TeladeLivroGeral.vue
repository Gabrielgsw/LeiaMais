<script setup></script>

<template>
    <div class="min-h-screen bg-[#e6f7fa] font-sans">
        <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
            <RouterLink to="/TelaInicial">
                <div class="text-5xl flex ">
                    <h1>Leia+</h1>
                    <img src="../assets/capivara.svg" alt="" />
                </div>
            </RouterLink>
        </header>
        <div class="flex flex-row max-w-5xl mx-auto bg-white  p-6 mt-16">
            <div class="mr-10">
                <img src="../assets/livros-teste/gameofthrones.webp" alt="">
            </div>
            <div>
                <h1 class="text-2xl font-bold text-[28px]">Game of thrones - livro um</h1>
                <p>teste</p>
            </div>
        </div>


    </div>
</template>

<style scoped></style>