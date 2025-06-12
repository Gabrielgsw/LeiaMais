<script setup>

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
    <!--Seção onde fica os serviços-->
    <div class="mx-auto w-5/6 my-8 p-14 flex flex-col items-center">
        <h2 class="text-[36px] font-semibold text-[#007bff] mb-12 text-left w-full">Nossos Serviços</h2>
        <div class="grid grid-cols-1 md:grid-cols-2 gap-10 w-full max-w-3xl p-4">
            <div
                class="w-full h-64 bg-emerald-200 shadow-md rounded-lg flex flex-col items-center justify-center text-gray-700 p-4">
                <img src="../assets/icones-serviço/icone-teste-capivara.png" alt="Ícone" class="w-16 h-16 mb-4">
                <h3 class="text-2xl font-bold mb-2 text-center">Título fofo kk 1</h3>
                <p class="text-base text-center">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corporis
                    recusandae velit facere facilist dolorem!</p>
            </div>
            <div
                class="w-full h-64 bg-amber-200 shadow-md rounded-lg flex flex-col items-center justify-center text-gray-700 p-4">
                <img src="../assets/icones-serviço/icone-teste-capivara.png" alt="Ícone" class="w-16 h-16 mb-4">
                <h3 class="text-2xl font-bold mb-2 text-center">Título fofo kk 2</h3>
                <p class="text-base text-center">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corporis
                    recusandae velit facere facilist dolorem!</p>
            </div>
            <div
                class="w-full h-64 bg-rose-200 shadow-md rounded-lg flex flex-col items-center justify-center text-gray-700 p-4">
                <img src="../assets/icones-serviço/icone-teste-capivara.png" alt="Ícone" class="w-16 h-16 mb-4">
                <h3 class="text-2xl font-bold mb-2 text-center">Título fofo kk 3</h3>
                <p class="text-base text-center">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corporis
                    recusandae velit facere facilist dolorem!</p>
            </div>
            <div
                class="w-full h-64 bg-orange-200 shadow-md rounded-lg flex flex-col items-center justify-center text-gray-700 p-4">
                <img src="../assets/icones-serviço/icone-teste-capivara.png" alt="Ícone" class="w-16 h-16 mb-4">
                <h3 class="text-2xl font-bold mb-2 text-center">Título fofo kk 4</h3>
                <p class="text-base text-center">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corporis
                    recusandae velit facere facilist dolorem!</p>
            </div>
        </div>
    </div>

    <!--Aqui será a seção onde vai ficar a parte do formulário onde a escola pode se comunicar para adiquirir o nosso software-->

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