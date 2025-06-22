<script setup lang="ts">
import { ref } from 'vue';
import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogFooter,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
} from "@/components/ui/dialog";

defineProps(["id", "nome", "cpf", "tipo"]);

const emit = defineEmits<{
    deleteUser: [id: number];
}>();

// Crie uma variável reativa para controlar o estado da dialog
const isDialogOpen = ref(false);
</script>

<template>
    <div class="grid grid-cols-4 items-center px-4 py-2 bg-blue-100 mb-2 rounded-md">
        <span>{{ nome }}</span>
        <span>{{ cpf }}</span>
        <span>{{ tipo }}</span>
        <span class="flex justify-center gap-3">
            <button>
                <img src="../assets/botoes/botao_editar.svg" alt="Editar" />
            </button>
            <!--aqui vai todo o conteúdo/popup-->
            <Dialog v-model:open="isDialogOpen">
                <DialogTrigger as-child>
                    <button>
                        <img src="/src/assets/botoes/botao_excluir.svg" alt="Excluir" />
                    </button>
                </DialogTrigger>
                <DialogContent>
                    <DialogHeader>
                        <DialogTitle>
                            <h1 class="text-[#0084FF] font-bold text-[24px]  mt-2">
                                Tem certeza que deseja excluir este usuário?
                            </h1>

                        </DialogTitle>
                        <div>
                            <p>
                                <span class="font-bold">Nome:</span>
                                {{ nome }}
                            </p>
                            <p>
                                <span class="font-bold ">CPF:</span>
                                {{ cpf }}
                            </p>
                        </div>
                    </DialogHeader>
                    <div class="flex items-center justify-between mt-4">
                        <button
                            class="bg-white text-[#359DFF] px-4 py-2 rounded shadow ring-1 ring-[#359DFF] hover:bg-black/5 transition-colors"
                            @click="isDialogOpen = false"> Cancelar
                        </button>
                        <Button class="bg-[#359DFF] text-white px-4 py-2 rounded shadow hover:bg-blue-600"
                            @click="$emit('deleteUser', id)">
                            Excluir
                        </Button>
                    </div>


                </DialogContent>
            </Dialog>

        </span>
    </div>
</template>
