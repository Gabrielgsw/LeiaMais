<script setup>
import QuestaoFormulario from '@/components/QuestaoFormulario.vue';

</script>

<!--Essa tela é a tela que o aluno vê-->
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
        
        <div class="max-w-5xl mx-auto bg-white shadow-md rounded-lg p-6 mt-16">
            <QuestaoFormulario/>
        </div> 
    </div>
</template>

<style scoped></style>