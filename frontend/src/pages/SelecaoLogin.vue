<script setup>
import { RouterLink } from 'vue-router';
</script>

<template>
    <div class="flex items-center justify-center min-h-screen bg-blue-100">
        <div class="bg-white bg-opacity-70 p-8 rounded-xl shadow-md w-full max-w-md">
            <h2 class="text-center text-lg font-semibold text-sky-600 mb-6">Digite suas informações</h2>

            <form @submit.prevent="handleLogin" class="space-y-4">
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