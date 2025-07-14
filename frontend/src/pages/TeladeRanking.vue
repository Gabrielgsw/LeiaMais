<script scoped lang="ts">
import { RouterLink } from 'vue-router'
</script>

<template>
    <div class="min-h-screen bg-[#e6f7fa] font-sans">
        <header class="text-white bg-[#0f8ebd] flex justify-around py-3">
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
                        <RouterLink to="/TelaInicial">
                            <img src="../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8" />
                        </RouterLink>

                        <h2 class="text-[28px] font-bold">Ranking da turma:</h2>
                    </div>
                </div>
                <div class="flex items-center justify-center">
                    <img src="../assets/capivara-trofeu.png" alt="">
                </div>
                <div>
                    <span></span>
                </div>

            </div>
    </div>

</template>

<style></style>