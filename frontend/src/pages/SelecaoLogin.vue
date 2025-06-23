<!---->
<script setup>
import { RouterLink, useRouter } from 'vue-router';
import { ref, onMounted, nextTick, watch } from 'vue';

const router = useRouter();

const roles = ['professor', 'aluno', 'coordenador'];
const selected = ref('professor');

const email = ref('');
const senha = ref('');

const buttons = ref([]);

const selectRole = (role) => {
    selected.value = role;
};

const sliderStyle = ref({ left: '0px', width: '0px' });

const updateSlider = () => {
    nextTick(() => {
        const index = roles.indexOf(selected.value);
        if (buttons.value && buttons.value[index]) {
            const btn = buttons.value[index];
            sliderStyle.value = {
                width: btn.offsetWidth + 'px',
                left: btn.offsetLeft + 'px',
            };
        }
    });
};

onMounted(() => {
    updateSlider();
});

watch(selected, () => {
    updateSlider();
});

const handleLogin = () => {
    // Aqui vai a lógica de autenticação
    alert(`Logando como ${selected.value} com email ${email.value}`);

    router.push('/TelaInicial');
};

</script>

<template>
    <!---->
    <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
        <RouterLink to="/">
            <div class="text-5xl flex ">
                <h1>Leia+</h1>
                <img src="../assets/capivara.svg" alt="" />
            </div>
        </RouterLink>
    </header>

    <div class="flex items-center justify-center min-h-screen bg-[#E0F7FF]">
        <div class="bg-white bg-opacity-70 p-8 rounded-xl shadow-md w-full max-w-md">
            <h2 class="text-center text-lg font-semibold text-sky-600 mb-6">Digite suas informações</h2>

            <div class="relative mb-8 w-full px-4">
                <div class="grid grid-cols-3 text-center relative" ref="buttonsContainer">
                    <button v-for="(role, index) in roles" :key="role" @click="selectRole(role)"
                        :ref="el => buttons[index] = el"
                        class="py-2 text-lg font-medium cursor-pointer transition-colors duration-300 text-gray-500"
                        :class="{ 'text-sky-600': selected === role }">
                        {{ role === 'coordenador' ? 'Coordenador' : role.charAt(0).toUpperCase() + role.slice(1) }}
                    </button>

                    <!-- Slider -->
                    <span class="absolute bottom-0 h-1 bg-sky-600 rounded-full transition-all duration-500 ease-in-out"
                        :style="sliderStyle"></span>
                </div>
            </div>


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

                    <button type="submit"
                        class="px-4 py-2 border border-blue-300 rounded hover:bg-blue-100 text-sky-600 font-medium">
                        Entrar
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>

<style scoped></style>