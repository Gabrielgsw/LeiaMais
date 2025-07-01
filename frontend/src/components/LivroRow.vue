<script setup lang="ts">
import { ref } from 'vue'; // Importe ref para criar uma variável reativa
import {
    Dialog,
    DialogContent,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
} from "@/components/ui/dialog";
import {
    FormControl,
    FormDescription,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from '@/components/ui/form'

const { id, isbn, titulo, autor, editora } = defineProps<{
    id: string
    isbn: string
    titulo: string
    autor: string
    editora: string
}>()

const emit = defineEmits<{
    deleteLivro: [isbn: string];
    atualizarLivro: [id: string, isbn: string, titulo: string, autor: string, editora: string];
}>();

const inputTitulo = ref(titulo);
const inputAutor = ref(autor);
const inputEditora = ref(editora);

const isDeleteDialogOpen = ref(false);
const isEditDialogOpen = ref(false);

defineExpose({
    isEditDialogOpen
})

</script>

<template>
    <div class="grid grid-cols-4 items-center px-4 py-2 bg-blue-100 mb-2 rounded-md">
        <span>{{ titulo }}</span>
        <span>{{ autor }}</span>
        <span>{{ editora }}</span>
        <span class="flex justify-center gap-3">
            <Dialog v-model:open="isEditDialogOpen">
                <DialogTrigger as-child>
                    <button><img src="../assets/botoes/botao_editar.svg" alt="Editar" /></button>
                </DialogTrigger>
                <DialogContent>
                    <form class=" space-y-6">
                        <FormField name="username">
                            <DialogHeader>
                                <DialogTitle>
                                    <h1 class="text-[#0084FF] font-bold text-[24px] mt-2">
                                        Editar livro
                                    </h1>
                                </DialogTitle>
                            </DialogHeader>
                            <FormItem>
                                <FormLabel>Título <span class="text-red-500 font-bold">*</span></FormLabel>
                                <FormControl>
                                    <input id="titulo"
                                        class="w-100% col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                                        placeholder="Título do livro" v-model="inputTitulo" />
                                </FormControl>
                            </FormItem>
                            <!-- <FormItem>
                                    <FormLabel>ISBN <span class="text-red-500 font-bold">*</span></FormLabel>
                                    <FormControl>
                                        <input id="isbn" type="number"
                                            class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                                            placeholder="ISBN" v-model="inputIsbn" />
                                    </FormControl>
                                </FormItem> -->
                            <FormItem>
                                <FormLabel>Autor <span class="text-red-500 font-bold">*</span></FormLabel>
                                <FormControl>
                                    <input id="autor"
                                        class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                                        placeholder="Autor do livro" v-model="inputAutor" />
                                </FormControl>
                            </FormItem>
                            <FormItem>
                                <FormLabel>Editora <span class="text-red-500 font-bold">*</span></FormLabel>
                                <FormControl>
                                    <input id="editora"
                                        class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                                        placeholder="Editora do livro" v-model="inputEditora" />
                                </FormControl>
                            </FormItem>
                        </FormField>
                    </form>
                    <div class="flex items-center justify-between mt-4">
                        <button
                            class="bg-white text-[#359DFF] px-4 py-2 rounded shadow ring-1 ring-[#359DFF] hover:bg-black/5 transition-colors"
                            @click="isEditDialogOpen = false"> Cancelar
                        </button>
                        <Button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600" @click="() => {
                            emit('atualizarLivro', id, isbn, inputTitulo, inputAutor, inputEditora)
                            isEditDialogOpen = false
                        }
                        ">
                            Atualizar
                        </Button>
                    </div>
                </DialogContent>

            </Dialog>

            <Dialog v-model:open="isDeleteDialogOpen">
                <DialogTrigger as-child>
                    <button>
                        <img src="/src/assets/botoes/botao_excluir.svg" alt="Excluir" />
                    </button>
                </DialogTrigger>
                <DialogContent>
                    <DialogHeader>
                        <DialogTitle>
                            <h1 class="text-[#0084FF] font-bold text-[24px] mt-2">
                                Tem certeza que deseja excluir este livro?
                            </h1>
                        </DialogTitle>
                        <div>
                            <p>
                                <span class="font-bold">Título do livro:</span>
                                {{ titulo }}
                            </p>
                            <p>
                                <span class="font-bold ">Editora:</span>
                                {{ editora }}
                            </p>
                        </div>
                    </DialogHeader>
                    <div class="flex items-center justify-between mt-4">
                        <button
                            class="bg-white text-[#359DFF] px-4 py-2 rounded shadow ring-1 ring-[#359DFF] hover:bg-black/5 transition-colors"
                            @click="isDeleteDialogOpen = false"> Cancelar
                        </button>
                        <Button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600"
                            @click="$emit('deleteLivro', isbn)">
                            Excluir
                        </Button>
                    </div>
                </DialogContent>
            </Dialog>

        </span>
    </div>
</template>

<style scoped>
#titulo,
#isbn,
#autor,
#editora {
    border-color: #DDDDDD;
    border: 1px solid #DDDDDD;
    border-radius: var(--dp-border-radius);
    color: var(--dp-text-color);
    background-color: #fff;
}
</style>