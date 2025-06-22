<script setup>
import { cn } from "@/lib/utils";

const props = defineProps({
  class: { type: null, required: false },
});
</script>

<template>
  <div
    data-slot="dialog-footer"
    :class="
      cn('flex gap-2 sm:flex-row', props.class)
    "
  >
    <slot />
  </div>
</template>
