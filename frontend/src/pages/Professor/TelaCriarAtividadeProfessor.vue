<script setup>
import FormularioBuilder from '@/components/FormularioBuilder.vue';
import FormularioBuilderProfessor from '@/components/FormularioBuilderProfessor.vue';
</script>

<template>
    <div class="min-h-screen bg-[#e6f7fa] font-sans">
        <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
            <RouterLink to="/TelaInicial">
                <div class="text-5xl flex ">
                    <h1>Leia+</h1>
                    <img src="../../assets/capivara.svg" alt="" />
                </div>
            </RouterLink>
        </header>
        <div class="max-w-5xl mx-auto bg-white shadow-md rounded-lg p-6 mt-16 mb-5">
            <div class="flex items-center justify-between mb-6">
                <div class="flex items-center gap-3">
                    <button @click="$router.back()" class="text-2xl">
                        <img src="../../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8" />
                    </button>
                    <h2 class="text-[28px] font-bold">Criar atividade</h2>
                </div>
            </div>
            <FormularioBuilderProfessor/>
            
        </div>
    </div>
</template>