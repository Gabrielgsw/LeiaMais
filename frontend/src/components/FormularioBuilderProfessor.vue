<script setup lang="ts">
import { reactive, ref, onMounted } from "vue";
import { Check, Search } from "lucide-vue-next";
import { cn } from "../lib/utils";
import {
    Combobox,
    ComboboxAnchor,
    ComboboxEmpty,
    ComboboxGroup,
    ComboboxInput,
    ComboboxItem,
    ComboboxItemIndicator,
    ComboboxList,
    ComboboxTrigger,
} from "@/components/ui/combobox";
import axios from "axios";
import { toTypedSchema } from "@vee-validate/zod";
import { useForm } from "vee-validate";
import * as z from "zod";
import {
    FormControl,
    FormDescription,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from "@/components/ui/form";
import { useRouter, RouterLink } from "vue-router";

const livros = ref<
    {
        id: string;
        titulo: string;
        autor: string;
        editora: string;
        livroUrl: string;
        isbn: string;
    }[]
>([]);
const questions = reactive<string[]>([""]);

const router = useRouter();

const formSchema = toTypedSchema(
    z.object({
        nome: z.string().min(1, "O nome é obrigatório"),
        prazoEntrega: z.string(),
        // professor: z.object({
        //     id: z.string().min(1, 'O ID do professor é obrigatório')
        // }),
        livro: z.object({
            id: z.string().min(1, "O ID do livro é obrigatório"),
        }),
    })
);

const { handleSubmit, setFieldValue, isFieldDirty, values } = useForm({
    validationSchema: formSchema,
    initialValues: {
        nome: "",
        prazoEntrega: "",
        // professor: {
        //     id: ''
        // },
        livro: {
            id: "",
        },
    },
});

// const livrosFiltrados = computed(() => {
//     if (!filtroLivro.value) return livros
//     return livros.filter(livro => livro.titulo.toLowerCase().includes(filtroLivro.value.toLowerCase()))
// })

function addQuestion() {
    questions.push("");
}

onMounted(async () => {
    try {
        const res = await axios.get("http://localhost:8080/livros");
        livros.value = res.data;
    } catch (err) {
        console.error("Erro ao buscar livros:", err);
    }

    try {
        const response = await axios.get('http://localhost:8080/professor')
        if (response.status >= 200 && response.status < 300) {
            console.log("Professor encontrado:", response.data);
            professor.value = response.data[0]
        } else {
            console.error("Erro ao buscar professor:", response.statusText);
        }
    } catch (err) {
        console.error("Erro ao buscar professor:", err);
    }
});

function removeQuestion(index: number) {
    questions.splice(index, 1);
}

// function markAsCorrect(qIndex, optIndex) {
//     questions[qIndex].correct = optIndex
// }

// function addOption(qIndex) {
//     questions[qIndex].options.push('')
// }

// function removeOption(qIndex, optIndex) {
//     questions[qIndex].options.splice(optIndex, 1)
// }
const professor = ref()
const turma = ref()

const onSubmit = handleSubmit(async (values) => {
    const turmaId = router.currentRoute.value.params.id as string;
    const atividade = {
        nome: values.nome,
        enunciado: questions.map((q) => q.trim()),
        livro: values.livro,
        professor: {
            // id: "065a994d-1a11-4c26-afcb-6b27add853ea",
            id: professor.value.id,
        }
        // turma: {
        //     id: turmaId,
        // },
    };

    console.log("Atividade: ", atividade);

    try {
        const response = await axios.post(
            `http://localhost:8080/api/atividades/${router.currentRoute.value.params.id}`,
            atividade
        );
        if (response.status >= 200 && response.status < 300) {
            console.log("Atividade criada com sucesso:", response.data);
            router.push(`/turmaprofessor/${turmaId}`);
        } else {
            console.error("Erro ao criar atividade:", response.statusText);
        }
    } catch (err) {
        console.error("Erro ao criar atividade:", err);
    }
});
</script>

<template>
    <form class="flex-1 max-w-2xl mx-auto p-4 space-y-4" @submit="onSubmit">
        <FormField name="nome" v-slot="{ componentField: nome }" :validate="!isFieldDirty">
            <FormItem class="flex gap-4 items-center">
                <FormLabel for="" class="shrink-0">Digite o título da atividade</FormLabel>
                <FormControl>
                    <input type="text" v-bind="nome"
                        class="bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 w-full" />
                </FormControl>
            </FormItem>
        </FormField>
        <FormField name="livro">
            <FormItem class="flex gap-10 items-center">
                <FormLabel class="shrink-0">Selecione o livro da atividade</FormLabel>
                <Combobox by="label">
                    <FormControl>
                        <ComboboxAnchor>
                            <div class="relative w-full max-w-sm items-center">
                                <ComboboxInput class="w-full focus:outline-none py-2"
                                    :display-value="(val) => val?.titulo ?? ''" placeholder="Pesquisar livro..." />

                                <ComboboxTrigger class="absolute end-0 inset-y-0 flex items-center justify-center px-3">
                                    <Search class="size-4 text-muted-foreground" />
                                </ComboboxTrigger>
                            </div>
                        </ComboboxAnchor>
                    </FormControl>
                    <ComboboxList>
                        <ComboboxEmpty> Nenhum livro encontrado. </ComboboxEmpty>

                        <ComboboxGroup>
                            <ComboboxItem v-for="livro in livros" :key="livro.id" :value="livro" @select="
                                () => {
                                    setFieldValue('livro', livro);
                                }
                            ">
                                {{ livro.titulo }}
                                <ComboboxItemIndicator>
                                    <Check :class="cn('ml-auto h-4 w-4')" />
                                </ComboboxItemIndicator>
                            </ComboboxItem>
                        </ComboboxGroup>
                    </ComboboxList>
                </Combobox>
            </FormItem>
        </FormField>
        <div v-for="(question, index) in questions" :key="index" class="border p-4 rounded-xl space-y-2">
            <input v-model="questions[index]" placeholder="Digite a pergunta" class="w-full p-2 border rounded" />
            <p class="text-sm text-gray-600">
                <span class="font-bold">Tipo:</span> Resposta curta
            </p>
            <!-- <div v-if="question.type === 'multiple'" class="space-y-1">
                <button @click="addOption(index)" class="text-blue-500 hover:underline">+ Adicionar opção</button>
            </div> -->

            <button type="button" @click="removeQuestion(index)" class="text-red-600 hover:underline">
                Excluir pergunta
            </button>
        </div>

        <button type="button" @click="addQuestion" :disabled="questions.length == 3"
            class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 disabled:opacity-50">
            + Nova Pergunta
        </button>
        <div class="flex items-center justify-between mt-4">

            <button @click="$router.back()"
                class="bg-white text-[#359DFF] px-4 py-2 rounded shadow ring-1 ring-[#359DFF] hover:bg-black/5 transition-colors">
                Cancelar
            </button>
            <button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600" type="submit">
                Criar
            </button>

        </div>
    </form>
</template>
