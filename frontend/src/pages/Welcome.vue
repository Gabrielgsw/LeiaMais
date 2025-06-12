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
    <div class="mx-auto w-5/6 my-14 p-14 bg-[#E0F7FF] rounded-lg shadow-xl flex flex-col items-center">
        <h2 class="text-[36px] font-semibold text-[#007bff] mb-12">Serviços</h2>

        <div class="grid grid-cols-1 md:grid-cols-2 gap-10 w-full max-w-3xl p-4">
            <div class="w-full h-48 bg-emerald-200 shadow-md rounded-lg flex items-center justify-center text-gray-700 text-xl font-bold p-4">
                <h4 class="text-center">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corporis recusandae velit facere facilist dolorem!</h4>
            </div>
            <div class="w-full h-48 bg-amber-200 shadow-md rounded-lg flex items-center justify-center text-gray-700 text-xl font-bold p-4">
                <h4 class="text-center">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corporis recusandae velit facere facilist dolorem!</h4>
            </div>
            <div class="w-full h-48 bg-rose-200 shadow-md rounded-lg flex items-center justify-center text-gray-700 text-xl font-bold p-4">
                <h4 class="text-center">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corporis recusandae velit facere facilist dolorem!</h4>
            </div>
            <div class="w-full h-48 bg-orange-200 shadow-md rounded-lg flex items-center justify-center text-gray-700 text-xl font-bold p-4">
                <h4 class="text-center">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corporis recusandae velit facere facilist dolorem!</h4>
            </div>
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