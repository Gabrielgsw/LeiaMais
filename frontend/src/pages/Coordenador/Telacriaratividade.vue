<script setup>
import {
    FormControl,
    FormDescription,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from '@/components/ui/form'
import FormularioBuilder from '@/components/FormularioBuilder.vue';
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
        <div class="max-w-5xl mx-auto bg-white shadow-md rounded-lg p-6 mt-16">
            <div class="flex items-center justify-between mb-6">
                <div class="flex items-center gap-3">
                    <button @click="$router.back()" class="text-2xl">
                        <img src="../../assets/botoes/botao_voltar.svg" alt="Voltar" class="w-8 h-8" />
                    </button>
                    <h2 class="text-[28px] font-bold">Criar atividade</h2>
                </div>
            </div>
            <!-- <h3 class="text-[20px] font-bold mt-3 mb-5 ">Pergunta n°1: </h3>
            <form class="w-2/3 space-y-6" @submit="onSubmit">
                <FormField v-slot="{ componentField }" name="username" :validate-on-blur="!isFieldDirty">
                    <FormItem>
                        <FormLabel>Digite o enunciado da 1° pergunta:</FormLabel>
                        <FormControl>
                            <Input id="pergunta"
                                class="w-100% col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 "
                                placeholder="Digite o enunciado da pergunta" />
                        </FormControl>
                        <FormLabel>Digite o enunciado da 2° pergunta:</FormLabel>
                        <FormControl>
                            <Input id="pergunta"
                                class="w-100% col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 "
                                placeholder="Digite o enunciado da pergunta" />
                        </FormControl>
                    </FormItem>
                </FormField>
            </form> -->
            <FormularioBuilder/>
            
        </div>
    </div>
</template>
