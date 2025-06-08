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
    <!--Seção de serviços-->
    <div class="h-180 bg-[#EFFBFF]">

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