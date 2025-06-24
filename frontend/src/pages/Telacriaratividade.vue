<script setup>
</script>

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
        <div class="max-w-5xl mx-auto bg-white shadow-md rounded-lg p-6 mt-16">
            <div class="flex items-center justify-between mb-6">
                <div class="flex items-center gap-3">
                    <button @click="$router.back()" class="text-2xl">
                        <img src="../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8" />
                    </button>
                    <h2 class="text-[28px] font-bold">Criar atividade</h2>
                </div>
            </div>
            <div class="flex items-center text-center ">
                <div class="flex text-center">
                    <h3 class="text-[20px] font-bold mt-3 ">Pergunta n°1: </h3>
                </div>
                
                <Input id="pergunta"
                    class="w-100% col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 "
                    placeholder="Digite a pergunta" />
            </div>

        </div>
    </div>
</template>
