<script setup>
import { ref, watch } from 'vue';
import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogFooter,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
} from "@/components/ui/dialog";
defineProps(['numeroatividade', 'atividadename'])

const isDialogOpen = ref(false);

</script>

<template>
    <div class="flex justify-between px-4 py-2 bg-blue-100 rounded-md mb-2">
        <span >Atividade {{ String(numeroatividade).padStart(2, '0') }}</span>
        <span class="flex justify-center gap-3">
            <button><img src="../assets/botoes/botao_editar.svg" alt="Editar" /></button>
            <Dialog v-model:open="isDialogOpen">
                <DialogTrigger as-child>
                    <button>
                        <img src="/src/assets/botoes/botao_excluir.svg" alt="Excluir" />
                    </button>
                </DialogTrigger>
                <DialogContent>
                    <DialogHeader>
                        <DialogTitle>
                            <h1 class="text-[#0084FF] font-bold text-[24px] mt-2">
                                Tem certeza que deseja excluir esta atividade ?
                            </h1>
                        </DialogTitle>
                        <div>
                            <p>
                                <span class="font-bold">Título da atividade:</span>
                                {{ atividadename }}
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