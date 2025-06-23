<script setup>
import LivroRow from '../components/LivroRow.vue'
import { ref, computed } from 'vue'
import { Search } from 'lucide-vue-next'
import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogFooter,
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

const livros = ref([
    { id: 1, titulo: '1984', autor: 'George Orwell', editora: 'Companhia das Letras' },
    { id: 2, titulo: 'O Pequeno Príncipe', autor: 'Antoine de Saint-Exupéry', editora: 'Agir' },
    { id: 3, titulo: 'Orgulho e Preconceito', autor: 'Jane Austen', editora: 'Martin Claret' },
    { id: 4, titulo: 'O Senhor dos Anéis', autor: 'J.R.R. Tolkien', editora: 'HarperCollins Brasil' },
])

const filtro = ref('')

const livrosFiltrados = computed(() => {
    if (!filtro.value) return livros.value
    return livros.value.filter((livro) =>
        livro.titulo.toLowerCase().includes(filtro.value.toLowerCase())
    )
})
const handleDeleteUser = (livroId) => {
    // const confirmDelete = window.confirm(`Tem certeza que deseja excluir o usuário com ID: ${userId}?`);

    // if (confirmDelete) {
    //     usuarios.value = usuarios.value.filter(usuario => usuario.id !== userId);

    //     console.log(`Usuário com ID ${userId} excluído.`);
    // } else {
    //     console.log(`Exclusão do usuário com ID ${userId} cancelada.`);
    // }
    console.log(`Livro com o ID ${livroId} excluído.`);
};
const isDialogOpen = ref(false);
</script>
<template>
    <div class="min-h-screen bg-[#e6f7fa] font-sans">
        <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
            <RouterLink to="/">
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
                    <h2 class="text-2xl font-bold text-[28px] ">Livros</h2>
                </div>

                <div class="flex gap-2">
                    <div
                        class="w-64 pe-2 bg-[#F5F7FA] rounded-md border border-gray-300 flex items-center focus-within:border-blue-600 ">
                        <div class="px-2">
                            <Search class="size-4 text-muted-foreground" />
                        </div>
                        <input v-model="filtro" type="text" class="w-full focus:outline-none py-2"
                            placeholder="Pesquisar livro...">
                    </div>
                    <Dialog v-model:open="isDialogOpen">
                        <DialogTrigger as-child>
                            <button class="bg-[#359DFF] text-white px-4 py-1 rounded hover:bg-blue-600">
                                Cadastrar livro
                            </button>
                        </DialogTrigger>
                        <DialogContent>
                            <DialogHeader>
                                <DialogTitle>
                                    <h1 class="text-[#0084FF] font-bold text-[24px] mt-2">
                                        Cadastrar Livro
                                    </h1>
                                </DialogTitle>

                            </DialogHeader>
                            
                            <div class="flex flex-col gap-3 mt-6 mb-2 text-start">
                                <div class="grid grid-cols-4 items-center gap-4 ">
                                    <Label for="name" class="text-right font-bold">
                                        Título
                                        <span class="text-red-500 font-bold">*</span>
                                    </Label>

                                    <Input id="name"
                                        class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75" />
                                </div>
                                <div class="grid grid-cols-4 items-center gap-4">
                                    <Label for="isbn" class="text-right font-bold">
                                        ISBN
                                        <span class="text-red-500 font-bold">*</span>
                                    </Label>
                                    <Input id="isbn"
                                        class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75" />
                                </div>
                                <div class="grid grid-cols-4 items-center gap-4">
                                    <Label for="autor" class="text-right font-bold">
                                        Autor
                                        <span class="text-red-500 font-bold">*</span>
                                    </Label>
                                    <Input id="autor"
                                        class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75" />
                                </div>
                                <div class="grid grid-cols-4 items-center gap-4">
                                    <Label for="editora" class="text-right font-bold">
                                        Editora
                                        <span class="text-red-500 font-bold">*</span>
                                    </Label>
                                    <Input id="editora"
                                        class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75" />
                                </div>

                            </div>

                            <div class="flex items-center justify-between mt-4">
                                <button
                                    class="bg-white text-[#359DFF] px-4 py-2 rounded shadow ring-1 ring-[#359DFF] hover:bg-black/5 transition-colors"
                                    @click="isDialogOpen = false"> Cancelar
                                </button>
                                <Button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600"
                                    @click="$emit('deleteUser', id)">
                                    Cadastrar
                                </Button>
                            </div>
                        </DialogContent>
                    </Dialog>

                </div>
            </div>

            <div class="grid grid-cols-4 font-bold px-4 py-2 bg-blue-100 rounded-md mb-2">
                <span>Título</span>
                <span>Autor</span>
                <span>Editora</span>
                <span class="text-center">Ações</span>
            </div>
            <LivroRow v-for="(livro, index) in livrosFiltrados" :key="index" :id="livro.id" :titulo="livro.titulo"
                :autor="livro.autor" :editora="livro.editora" @delete-user="handleDeleteUser" />

            <div v-if="livrosFiltrados.length === 0" class="text-center text-gray-500 mt-4">
                Nenhum livro encontrado.
            </div>
        </div>
    </div>
</template>
