<script setup>
import { RouterLink } from 'vue-router';
import { ref } from 'vue'

const selected = ref('professor')

const toggleRole = () => {
    selected.value = selected.value === 'professor' ? 'aluno' : 'professor'
}

</script>

<template>
    <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
        <RouterLink to="/">
            <div class="text-5xl flex ">
                <h1 class="">Leia+</h1>
                <img src="../assets/capivara.svg" alt="">
            </div>
        </RouterLink>
    </header>
    <div class="flex items-center justify-center min-h-screen bg-[#E0F7FF]">
        <div class="bg-white bg-opacity-70 p-8 rounded-xl shadow-md w-full max-w-md">
            <h2 class="text-center text-lg font-semibold text-sky-600 mb-6">Digite suas informações</h2>
            <form @submit.prevent="handleLogin" class="space-y-4">
                <div class="flex justify-center mb-4">
                    <div @click="toggleRole"
                        class="w-66 h-10 relative flex items-center bg-[#91CFE5] rounded-full overflow-hidden cursor-pointer transition-colors duration-300"
                        :class="selected === 'professor' ? 'bg-[#91CFE5]' : 'bg-[#91CFE5]'">
                        <div class="absolute h-8 w-[124px] rounded-full shadow-md text-white font-semibold flex items-center justify-center transition-transform duration-300"
                            :class="[
                                selected === 'professor' ? 'translate-x-1 bg-sky-600' : 'translate-x-[135px] bg-sky-600'
                            ]">
                            {{ selected === 'professor' ? 'Sou Professor' : 'Sou Aluno' }}
                        </div>
                    </div>
                </div>



                <div>
                    <label class="block text-sm font-medium text-sky-600 mb-1">
                        E-mail <span class="text-red-500">*</span>
                    </label>
                    <input type="email" v-model="email" required
                        class="w-full px-3 py-2 border border-blue-300 rounded focus:outline-none focus:ring-2 focus:ring-sky-400 bg-blue-50" />
                </div>

                <div>
                    <label class="block text-sm font-medium text-sky-600 mb-1">
                        Senha <span class="text-red-500">*</span>
                    </label>
                    <input type="password" v-model="senha" required
                        class="w-full px-3 py-2 border border-blue-300 rounded focus:outline-none focus:ring-2 focus:ring-sky-400 bg-blue-50" />
                </div>

                <div class="flex justify-between pt-4">
                    <RouterLink to="/" type="button"
                        class="px-4 py-2 border border-blue-300 rounded hover:bg-blue-100 text-sky-600 font-medium">
                        Voltar
                    </RouterLink>

                    <RouterLink to="/InicialSystem" type="submit"
                        class="px-4 py-2 border border-blue-300 rounded hover:bg-blue-100 text-sky-600 font-medium">
                        Entrar
                    </RouterLink>
                </div>
            </form>
        </div>
    </div>
</template>

<style scoped></style>