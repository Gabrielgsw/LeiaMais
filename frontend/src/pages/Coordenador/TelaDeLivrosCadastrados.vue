<script setup>
//imports do sistema
import LivroRow from '../../components/LivroRow.vue'
import { ref, computed, onMounted } from 'vue'
import { Search } from 'lucide-vue-next'
import axios from 'axios';
import { useForm } from 'vee-validate'
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

//variáveis do sistema
const livros = ref([]); // Inicialize como um array vazio
const filtro = ref('');
const isDialogOpen = ref(false);

// Adicione as variáveis reativas para os campos do formulário
const titulo = ref('');
const isbn = ref('');
const autor = ref('');
const editora = ref('');
const linkDoLivro = ref('');

// Função para carregar os livros da API
const carregarLivros = async () => {
    try {
        const response = await axios.get('http://localhost:8080/livros'); // Endpoint da sua API para listar livros
        livros.value = response.data; // Atualiza a variável reativa 'livros' com os dados da API
        console.log("Livros carregados:", livros.value);
    } catch (error) {
        console.error("Erro ao carregar livros:", error);
    }
};

// Chame a função carregarLivros quando o componente for montado
onMounted(() => {
    carregarLivros();
});

const livrosFiltrados = computed(() => {
    if (!filtro.value) return livros.value
    return livros.value.filter((livro) =>
        livro.titulo.toLowerCase().includes(filtro.value.toLowerCase()) || livro.isbn.toLowerCase().includes(filtro.value.toLowerCase())
    )
})


async function cadastrarLivro ()  {
    const novoLivro = {
        titulo: titulo.value,
        autor: autor.value,
        editora: editora.value,
        isbn: isbn.value,
        livroUrl: linkDoLivro.value
    };

    console.log("Cadastrando novo livro:", novoLivro);

    try {
        const response = await axios.post('http://localhost:8080/livros', novoLivro);
        const livroSalvo = response.data;
        console.log("Livro salvo:", livroSalvo);

        // Adicione o novo livro à lista existente e feche o modal
        livros.value.push(livroSalvo);
        isDialogOpen.value = false;

        // Limpe os campos do formulário após o cadastro (opcional)
        titulo.value = '';
        autor.value = '';
        editora.value = '';
        isbn.value = '';
        linkDoLivro.value = '';

    } catch (error) {
        console.error("Erro ao cadastrar:", error);
    }
};

 async function handleDeleteLivro(isbn)  {
    console.log("Excluindo livro com ISBN:", isbn);

    try {
        const response = await axios.delete(`http://localhost:8080/livros/${isbn}`);

        if (response.status >= 200 && response.status < 300) {
            console.log("Livro excluído com sucesso");
        } else {
            console.error("Erro ao excluir livro:", response);
        }

        livros.value = livros.value.filter(livro => livro.isbn !== isbn);
    } catch (error) {
        console.error("Erro ao excluir livro:", error);
    }
};

const handleEditarLivro = async (id, isbn, titulo, autor, editora) => {
    console.log("Editando livro com ISBN:", id);
    console.log("Editando titulo:", titulo);
    console.log("Editando autor:", autor);
    console.log("Editando editora:", editora);

    const livroAtualizado = {
        titulo,
        autor,
        editora,
        isbn
    }

    try {
        const response = await axios.put(`http://localhost:8080/livros/${id}`, livroAtualizado);

        if (response.status >= 200 && response.status < 300) {
            console.log("Livro editado com sucesso");
            livros.value = livros.value.map(livro => {
                if (livro.id === id) {
                    return {
                        ...livro,
                        titulo: titulo,
                        autor: autor,
                        editora: editora,
                        isbn: isbn
                    };
                }
                return livro;
            })
        } else {
            console.error("Erro ao editar livro:", response.data);
        }



    } catch (error) {
        console.error("Erro ao editar livro:", error);
    }
};




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
                            <form class=" space-y-6">
                                <FormField v-slot="{ componentField }" name="username"
                                    :validate-on-blur="!isFieldDirty">
                                    <DialogHeader>
                                        <DialogTitle>
                                            <h1 class="text-[#0084FF] font-bold text-[28px] mt-2">
                                                Cadastrar Livro
                                            </h1>
                                        </DialogTitle>
                                    </DialogHeader>
                                    <FormItem>
                                        <FormLabel>Título <span class="text-red-500 font-bold">*</span></FormLabel>
                                        <FormControl>
                                            <input id="titulo"
                                                class="w-100% col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                                                placeholder="Título do livro" v-model="titulo" />
                                        </FormControl>
                                    </FormItem>
                                    <FormItem>
                                        <FormLabel>ISBN <span class="text-red-500 font-bold">*</span></FormLabel>
                                        <FormControl>
                                            <input id="isbn" type="number"
                                                class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                                                placeholder="ISBN" v-model="isbn" />
                                        </FormControl>
                                    </FormItem>
                                    <FormItem>
                                        <FormLabel>Autor <span class="text-red-500 font-bold">*</span></FormLabel>
                                        <FormControl>
                                            <input id="autor"
                                                class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                                                placeholder="Autor do livro" v-model="autor" />
                                        </FormControl>
                                    </FormItem>
                                    <FormItem>
                                        <FormLabel>Editora <span class="text-red-500 font-bold">*</span></FormLabel>
                                        <FormControl>
                                            <input id="editora"
                                                class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                                                placeholder="Editora do livro" v-model="editora" />
                                        </FormControl>
                                    </FormItem>
                                    <FormItem>
                                        <FormLabel>Link do livro <span class="text-red-500 font-bold">*</span></FormLabel>
                                        <FormControl>
                                            <input id="linkdolivro"
                                                class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                                                placeholder="Link do livro" v-model="linkDoLivro" />
                                        </FormControl>
                                    </FormItem>
                                    <FormItem>
                                        <FormLabel>Link da imagem <span class="text-red-500 font-bold">*</span></FormLabel>
                                        <FormControl>
                                            <input id="linkdaimagem"
                                                class="col-span-3 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                                                placeholder="Link da imagem" v-model="linkDaImagem" />
                                        </FormControl>
                                    </FormItem>
                                </FormField>
                            </form>
                            <div class="flex items-center justify-between mt-4">
                                <button
                                    class="bg-white text-[#359DFF] px-4 py-2 rounded shadow ring-1 ring-[#359DFF] hover:bg-black/5 transition-colors"
                                    @click="isDialogOpen = false"> Cancelar
                                </button>
                                <Button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600"
                                    @click="cadastrarLivro">
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

            <LivroRow v-for="livro in livrosFiltrados" :key="livro.isbn" :id="livro.id" :isbn="livro.isbn"
                :titulo="livro.titulo" :autor="livro.autor" :editora="livro.editora" @delete-livro="handleDeleteLivro"
                @atualizar-livro="handleEditarLivro" />

            <div v-if="livrosFiltrados.length === 0" class="text-center text-gray-500 mt-4">
                Nenhum livro encontrado.
            </div>
        </div>
    </div>
</template>
<style scoped>
#titulo,
#isbn,
#autor,
#editora,
#linkdolivro,
#linkdaimagem{
    border-color: #DDDDDD;
    border: 1px solid #DDDDDD;
    border-radius: var(--dp-border-radius);
    color: var(--dp-text-color);
    background-color: #fff;
}
</style>