<script setup lang="ts">
import { toTypedSchema } from '@vee-validate/zod'
import { useForm } from 'vee-validate'
import * as z from 'zod'
import { ref, computed, onMounted } from 'vue'
import UsuarioRow from '../../components/UsuarioRow.vue'
import { RouterLink } from 'vue-router'
import { Search } from 'lucide-vue-next'
import '@vuepic/vue-datepicker/dist/main.css'
import {
    Tooltip,
    TooltipContent,
    TooltipProvider,
    TooltipTrigger,
} from '@/components/ui/tooltip'
import axios from 'axios'
import {
    Dialog,
    DialogContent,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
} from "@/components/ui/dialog";
import {
    FormControl,
    FormField,
    FormItem,
    FormLabel,
} from '@/components/ui/form'
import {
    Popover,
    PopoverContent,
    PopoverTrigger,
} from "@/components/ui/popover";
import {
    CalendarDate,
    DateFormatter,
    getLocalTimeZone,
    parseDate,
    today,
} from "@internationalized/date";
import { CalendarIcon } from "lucide-vue-next";
import { Button } from "@/components/ui/button";
import { Calendar } from "@/components/ui/calendar";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";

const createUserFormSchema = toTypedSchema(z.object({
    nome: z.string().min(2).max(50),
    email: z.string().email(),
    senha: z.string().min(6).max(20),
    cpf: z.string().length(14),
    dataNascimento: z.string(),
    matricula: z.string().optional(),
    tipoUsuario: z.enum(['ALUNO', 'PROFESSOR', 'COORDENADOR'])
}))

const df = new DateFormatter("pt-BR", {
    dateStyle: "long",
});

const { handleSubmit, isFieldDirty, values, setFieldValue } = useForm({
    validationSchema: createUserFormSchema,
    initialValues: {
        nome: '',
        email: '',
        senha: '',
        cpf: '',
        dataNascimento: '',
    },
})

const tipoUsuario = ref('');
const dataNascimento = computed({
    get: () =>
        values.dataNascimento ? parseDate(values.dataNascimento) : undefined,
    set: (val) => val,
});

const usuarios = ref<{
    id: string;
    cpf: string;
    nome: string;
    email: string;
    senha: string;
    cargo: 'COORDENADOR' | 'PROFESSOR' | 'ALUNO';
    dataNascimento: string | null;
    turmasCoordenadas: string[];
}[]>([]);

const filtro = ref('');
const usuariosFiltrados = computed(() => {
    if (!filtro.value) return usuarios.value;
    return usuarios.value.filter(usuario =>
        usuario.nome.toLowerCase().includes(filtro.value.toLowerCase())
    );
});

async function getUsuarios() {
    try {
        const response = await axios.get('http://localhost:8080/api/coordenador/usuarios')

        console.log(response.data);

        if (response.status >= 200 && response.status < 300) {
            usuarios.value = response.data;
        } else {
            console.error('Erro ao buscar usuários:', response.statusText);
        }
    } catch (error) {
        console.error('Erro ao buscar usuários:', error);
    }
}

const handleDeleteUser =async (userId: string) => {
    // try {
    //     const response = await axios.delete(`http://localhost:8080/api/coordenador/usuario/${userId}`);
    //     if (response.status >= 200 && response.status < 300) {
    //         console.log('Usuário deletado com sucesso:', response.data);
    //         getUsuarios();
    //     } else {
    //         console.error('Erro ao deletar usuário:', response.statusText);
    //     }
    // } catch (error) {
    //     console.error('Erro ao deletar usuário:', error);
    // }
};

const isDialogOpen = ref(false);

const onSubmit = handleSubmit(async (values) => {
 const   {
        email,
        nome,
        senha,
        cpf,
        dataNascimento,
        tipoUsuario,
        matricula

    } = values;

    const usuario = {
            nome,
            cpf,
            email,
            senha,
            cargo: tipoUsuario,
            dataNascimento,
            matricula: tipoUsuario === 'ALUNO' ? matricula : undefined
    }
    try {
        const response = await axios.post('http://localhost:8080/api/coordenador/register-user',usuario
        )

        if (response.status >= 200 && response.status < 300) {
            console.log('Usuário cadastrado com sucesso:', response.data);
            isDialogOpen.value = false;
            getUsuarios();
        } else {
            console.error('Erro ao cadastrar usuário:', response.statusText);
        }
    } catch (error) {
        console.error('Erro ao cadastrar usuário:', error);
    }


    console.log('Formulário enviado com sucesso:', values);
})

onMounted(() => {
    getUsuarios();
});


</script>
<template>
    <div class="min-h-screen bg-[#e6f7fa] font-sans">
        <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
            <RouterLink to="/">
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
                    <h2 class="text-[28px] font-bold">Usuários</h2>
                </div>

                <div class="flex gap-2 ">
                    <div
                        class="w-64 pe-2 bg-[#F5F7FA] rounded-md border border-gray-300 flex items-center focus-within:border-blue-600 ">
                        <div class="px-2">
                            <Search class="size-4 text-muted-foreground" />
                        </div>
                        <input v-model="filtro" type="text" class="w-full focus:outline-none py-2"
                            placeholder="Pesquisar usuário...">
                    </div>
                    <Dialog v-model:open="isDialogOpen">
                        <DialogTrigger as-child>
                            <button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600">
                                Cadastrar usuário
                            </button>
                        </DialogTrigger>
                        <DialogContent>
                            <form class=" space-y-6" @submit="onSubmit">
                                <DialogHeader>
                                    <DialogTitle>
                                        <h1 class="text-[#0084FF] font-bold text-[28px] mt-2">
                                            Cadastrar usuário
                                        </h1>
                                    </DialogTitle>
                                </DialogHeader>
                                <FormField v-slot="{ componentField: nome }" name="nome"
                                    :validate-on-blur="!isFieldDirty">
                                    <FormItem>
                                        <FormLabel>Nome <span class="text-red-500 font-bold">*</span></FormLabel>
                                        <FormControl>
                                            <input id="nome"
                                                class="col-span-4 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                                                placeholder="Nome do usuário" v-bind="nome" />
                                        </FormControl>
                                    </FormItem>
                                </FormField>
                                <FormField v-slot="{ componentField: email }" name="email"
                                    :validate-on-blur="!isFieldDirty">
                                    <FormItem>
                                        <FormLabel>E-mail <span class="text-red-500 font-bold">*</span></FormLabel>
                                        <FormControl>
                                            <input id="email" type="email"
                                                class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                                                placeholder="Digite o email" v-bind="email" />
                                        </FormControl>
                                    </FormItem>
                                </FormField>
                                <FormField v-slot="{ componentField: senha }" name="senha"
                                    :validate-on-blur="!isFieldDirty">
                                    <FormItem>
                                        <FormLabel>Senha <span class="text-red-500 font-bold">*</span></FormLabel>
                                            <FormControl>
                                                <input id="senha" type="password"
                                                    class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                                                    placeholder="Digite a senha" v-bind="senha" />
                                                <i class="bi bi-eye"></i>
                                            </FormControl>
                                    </FormItem>
                                </FormField>
                                <FormField v-slot="{ componentField: cpf }" name="cpf"
                                    :validate-on-blur="!isFieldDirty">
                                    <FormItem>
                                        <FormLabel>CPF <span class="text-red-500 font-bold">*</span></FormLabel>
                                        <FormControl>
                                            <input id="cpf"
                                                class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                                                placeholder="Digite o CPF" v-bind="cpf" />
                                        </FormControl>
                                    </FormItem>
                                </FormField>
                                <FormField name="dataNascimento">
                                    <FormItem>
                                        <FormLabel>Data de nascimento <span class="text-red-500 font-bold">*</span>
                                        </FormLabel>
                                        <FormControl>
                                            <Popover>
                                                <PopoverTrigger as-child>
                                                    <Button variant="outline" :class="`w-[240px] ps-3 text-start font-normal ${!dataNascimento && 'text-muted-foreground'
                                                        }`">
                                                        <span>{{
                                                            dataNascimento
                                                                ? df.format(dataNascimento.toDate(getLocalTimeZone()))
                                                                : "Escolha a data de nascimento"
                                                        }}</span>
                                                        <CalendarIcon class="ms-auto h-4 w-4 opacity-50" />
                                                    </Button>
                                                    <input hidden />
                                                </PopoverTrigger>
                                                <PopoverContent class="w-auto p-0">
                                                    <Calendar :model-value="dataNascimento"
                                                        calendar-label="Date of birth" initial-focus
                                                        :min-value="new CalendarDate(1900, 1, 1)"
                                                        :max-value="today(getLocalTimeZone())" @update:model-value="
                                                            (value) => {
                                                                if (value) {
                                                                    setFieldValue('dataNascimento', value.toString());
                                                                } else {
                                                                    setFieldValue('dataNascimento', undefined);
                                                                }
                                                            }
                                                        " />
                                                </PopoverContent>
                                            </Popover>
                                        </FormControl>
                                    </FormItem>
                                </FormField>


                                <FormField v-slot="{ componentField: tipoUsuario }" type="radio" name="tipoUsuario">
                                    <FormItem>
                                        <FormLabel>Qual o tipo de perfil que deseja criar? <span
                                                class="text-red-500 font-bold">*</span></FormLabel>
                                        <FormControl>
                                            <RadioGroup class="flex gap-3" v-bind="tipoUsuario">
                                                <FormItem class="flex items-center gap-x-2">
                                                    <FormControl>
                                                        <RadioGroupItem value="ALUNO" />
                                                    </FormControl>
                                                    <FormLabel class="font-normal"> Aluno </FormLabel>
                                                </FormItem>
                                                <FormItem class="flex items-center gap-2">
                                                    <FormControl>
                                                        <RadioGroupItem value="PROFESSOR" />
                                                    </FormControl>
                                                    <FormLabel class="font-normal">
                                                        Professor
                                                    </FormLabel>
                                                </FormItem>
                                            </RadioGroup>
                                        </FormControl>
                                    </FormItem>
                                </FormField>
                                <FormField v-if="values.tipoUsuario === 'ALUNO'" v-slot="{ componentField: matricula }"
                                    name="matricula" :validate-on-blur="!isFieldDirty">
                                    <FormItem>
                                        <FormLabel>Matrícula <span class="text-red-500 font-bold">*</span></FormLabel>
                                        <FormControl>
                                            <input id="matricula"
                                                class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                                                placeholder="Digite a matrícula" v-bind="matricula" />
                                        </FormControl>
                                    </FormItem>
                                </FormField>



                                <div class="flex items-center justify-between mt-4">
                                    <button
                                        class="bg-white text-[#359DFF] px-4 py-2 rounded shadow ring-1 ring-[#359DFF] hover:bg-black/5 transition-colors"
                                        @click="isDialogOpen = false"> Cancelar
                                    </button>
                                    <Button type="submit"
                                        class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600">
                                        Cadastrar
                                    </Button>
                                </div>
                            </form>
                        </DialogContent>
                    </Dialog>


                </div>
            </div>

            <div class="grid grid-cols-4 font-bold px-4 py-2 bg-blue-100 rounded-md mb-2">
                <span>Nome</span> <span>CPF</span> <span>Tipo</span> <span class="text-center">Ações</span>
            </div>

            <UsuarioRow v-for="usuario in usuariosFiltrados" :key="usuario.id" :id="usuario.id" :nome="usuario.nome"
                :cpf="usuario.cpf" :tipo="usuario.cargo" @delete-user="handleDeleteUser" />

            <div v-if="usuariosFiltrados.length === 0" class="text-center text-gray-500 mt-4">
                Nenhum usuário encontrado.
            </div>
        </div>
    </div>
</template>

<style scoped>
#cpf,
#nome,
#email,
#senha {
    border-color: #DDDDDD;
    border: 1px solid #DDDDDD;
    border-radius: var(--dp-border-radius);
    color: var(--dp-text-color);
    background-color: #fff;
}
</style>