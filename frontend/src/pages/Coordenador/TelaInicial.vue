<script setup lang="ts">
import { RouterLink, useRouter } from "vue-router";
import { toTypedSchema } from "@vee-validate/zod";
import { useForm } from "vee-validate";
import * as z from "zod";
import { ref, onMounted } from "vue";
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
  FormField,
  FormItem,
  FormLabel,
} from "@/components/ui/form";
import Turma from "../../components/Turma.vue";
import axios from "axios";

const router = useRouter();

const turmas = ref<
  { id: string; nome: string; alunosMatriculados: []; professores: []; livros: []; atividades: [] }[]
>([]);

const livros = ref([
  "https://covers.openlibrary.org/b/isbn/9788522005239-M.jpg",
  "https://covers.openlibrary.org/b/isbn/8538048201-M.jpg",
  "https://covers.openlibrary.org/b/isbn/9780316183567-M.jpg",
  "https://covers.openlibrary.org/b/isbn/8538083724-M.jpg",
  "https://covers.openlibrary.org/b/isbn/8478646795-M.jpg"
 
]);


function extrairISBN(url: string): string {
  const match = url.match(/isbn\/(\d+)-/);
  return match ? match[1] : "";
}




const handleLogout = async () => {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/auth/logout",
      {},
      { withCredentials: true }
    );
    if (response.status === 200) {
      alert("Logout realizado com sucesso.");
      router.push("/");
    }
  } catch (error) {
    console.error("Erro no logout:", error);
  }
};

const isAddTurmaDialogOpen = ref(false);
const createTurmaFormSchema = toTypedSchema(
  z.object({
    nome: z.string().min(2).max(50),
  })
);

const { handleSubmit, isFieldDirty, values, setFieldValue } = useForm({
  validationSchema: createTurmaFormSchema,
  initialValues: {
    nome: "",
  },
});


async function getTurmas() {
  const response = await axios.get("http://localhost:8080/turmas");
  if (response.status >= 200 && response.status < 300) {
    turmas.value = response.data;
  } else {
    console.error("Erro ao buscar turmas:", response.statusText);
  }

  turmas.value = await response.data

  console.log("Turmas carregadas:", turmas.value);
}

const criarTurma = handleSubmit(async (values) => {
  const turma = {
    nome: values.nome,
  };

  try {
    const response = await axios.post("http://localhost:8080/turmas", turma);

    if (response.status >= 200 && response.status < 300) {
      console.log("Usuário cadastrado com sucesso:", response.data);
      isAddTurmaDialogOpen.value = false;
      getTurmas();
    } else {
      console.error("Erro ao cadastrar usuário:", response.statusText);
    }
  } catch (error) {
    console.error("Erro ao cadastrar usuário:", error);
  }

  console.log("Formulário enviado com sucesso:", values);
});

onMounted(() => {
  getTurmas();
});
</script>

<!--<div class="min-h-[90vh] bg-blue-100">
        <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
            <RouterLink to="/InicialSystem">
                <div class="text-5xl flex ">
                    <h1 class="">Leia+</h1>
                    <img src="../assets/capivara.svg" alt="">
                </div>
            </RouterLink>

            <div class="text-3xl flex gap-5">
                <nav class="text-center ">
                    <a href="#" class="no-underline pt-0 pr-[50px] pb-[10px] pl-0 text-[16pt] relative group">
                        Espaço educacional
                        <span
                            class="absolute bottom-0 left-0 w-0 h-[2px] bg-white rounded-full transition-all duration-300 ease-in-out group-hover:w-[80%]"></span>
                    </a>

                    <a href="#" class="no-underline pt-0 pr-[50px] pb-[10px] pl-0 text-[16pt] relative group">
                        Minhas atividades
                        <span
                            class="absolute bottom-0 left-0 w-[0px] h-[2px] bg-white rounded-full transition-all duration-300 ease-in-out group-hover:w-[75%]"></span>
                    </a>
                </nav>
            </div>
        </header>


        <main class="p-6">
            <div class="bg-white p-6 rounded-lg shadow-md flex items-center gap-4">
                <div class="w-16 h-16 relative rounded-full overflow-hidden">
                    <img src="../assets/Capivara.png" alt="Fundo" class="w-full h-full object-cover" />
                    <div class="absolute inset-0 bg-black mask mask-custom"
                        style="mask-image: url('../assets/icone-edit.svg'); mask-repeat: no-repeat; mask-position: center; mask-size: 1.5rem;">
                    </div>
                </div>
                <div>
                    <h2 class="text-lg font-bold text-blue-600">Olá, Aécio</h2>
                    <p>Você leu <strong>X</strong> livros este mês<br />Turma: 3º ano B</p>
                </div>
            </div>


            <section class="mt-6">
                <h3 class="font-bold text-lg mb-2">Amigos</h3>
                <div class="bg-blue-200 p-4 rounded-lg overflow-x-auto whitespace-nowrap flex gap-4">
                    <template v-for="n in 10" :key="n">
                        <div
                            class="w-12 h-12 rounded-full bg-white inline-flex items-center justify-center overflow-hidden">
                            <img src="../assets/capivara-icone-amigo.png" alt="Amigo" class="w-10 h-10 object-cover" />
                        </div>
                    </template>
</div>
<div class="text-right mt-1">
    <button class="text-xs text-blue-600 hover:underline">Ver mais</button>
</div>
</section>


<section class="mt-6">
    <h3 class="font-bold text-lg mb-2">Últimas leituras</h3>
    <div class="bg-blue-200 p-4 rounded-lg overflow-x-auto whitespace-nowrap flex gap-4">
        <template v-for="n in 6" :key="'livro-' + n">
                        <img src="../assets/livros-teste/gameofthrones.webp" alt="Capa do livro"
                            class="h-48 rounded shadow" />
                    </template>
    </div>
    <div class="text-right mt-1">
        <button class="text-xs text-blue-600 hover:underline">Ver tudo</button>
    </div>
</section>
</main>
</div>-->
<template>
  <div class="min-h-screen bg-[#e6f7fa] font-sans">
    <header class="text-white bg-[#0f8ebd] flex justify-around py-3 items-center">
      <RouterLink to="/TelaInicial">
        <div class="text-5xl flex">
          <h1>Leia+</h1>
          <img src="../../assets/capivara.svg" alt="" />
        </div>
      </RouterLink>
    </header>

    <main class="p-12">
      <div class="bg-white rounded-lg shadow-md p-6">
        <div class="flex items-center gap-4 mb-6">
          <div class="w-20 h-20 bg-gray-300 rounded-full">
            <img src="../../assets/capivara.png" alt="Fundo" class="w-full h-full object-cover" />
          </div>
          <div>
            <p class="text-blue-600 font-bold text-lg">Olá, Coordenador</p>
            <p class="text-gray-600">Escola: Educandário São Judas Tadeu</p>
          </div>
        </div>

        <div class="flex gap-4 mb-6">
          <router-link to="/TelaDeLivrosCadastrados">
            <button class="bg-[#359DFF] text-white px-4 py-2 rounded hover:bg-blue-600 transition-colors">
              Adicionar Livros
            </button>
          </router-link>
          <router-link to="/TelaDeUsuariosCadastrados"><button
              class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600 transition-colors">
              Adicionar usuários
            </button>
          </router-link>

          <Dialog v-model:open="isAddTurmaDialogOpen">
            <DialogTrigger as-child>
              <button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600 transition-colors">
                Adicionar Turma
              </button>
            </DialogTrigger>
            <DialogContent>
              <form class="space-y-6" @submit="criarTurma">
                <DialogHeader>
                  <DialogTitle>
                    <h1 class="text-[#0084FF] font-bold text-[28px] mt-2">
                      Adicionar turma
                    </h1>
                  </DialogTitle>
                </DialogHeader>
                <FormField v-slot="{ componentField: nome }" name="nome" :validate-on-blur="!isFieldDirty">
                  <FormItem>
                    <FormLabel>Nome
                      <span class="text-red-500 font-bold">*</span>
                    </FormLabel>
                    <FormControl>
                      <input id="nome"
                        class="col-span-4 bg-[#F5F7FA] rounded-xs border border-gray-300 px-1.5 py-0.75 h-[38px]"
                        placeholder="Nome da turma" v-bind="nome" />
                    </FormControl>
                  </FormItem>
                </FormField>

                <div class="flex items-center justify-between mt-4">
                  <button
                    class="bg-white text-[#359DFF] px-4 py-2 rounded shadow ring-1 ring-[#359DFF] hover:bg-black/5 transition-colors"
                    @click="isAddTurmaDialogOpen = false">
                    Cancelar
                  </button>
                  <Button type="submit"
                    class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600 transition-colors">
                    Cadastrar
                  </Button>
                </div>
              </form>
            </DialogContent>
          </Dialog>


          <button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600 transition-colors"
            @click="handleLogout">
            Sair da conta
          </button>
        </div>

        <!--:to="`/turma/${turma.id}`" colocar quando tiver pronto-->
        <div class="space-y-4 mb-8">
          <router-link v-for="turma in turmas" :to="`/turma/${turma.id}`" class="block">
            <Turma :nome="turma.nome" />
          </router-link>
        </div>

        <div>
          <h2 class="text-lg font-bold mb-4">Biblioteca Geral:</h2>
          <div class="flex overflow-x-auto gap-4 bg-blue-100 p-4 rounded">
            <router-link v-for="livro in livros" :key="livro" :to="`/livro/${extrairISBN(livro)}`">
              <img :src="livro" class="w-[160px] h-[230px] rounded-sm object-cover" />
            </router-link>
          </div>

          
        </div>
      </div>
    </main>
  </div>
</template>

<style scoped></style>
