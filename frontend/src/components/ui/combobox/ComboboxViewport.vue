<script setup>
import { reactiveOmit } from "@vueuse/core";
import { ComboboxViewport, useForwardProps } from "reka-ui";
import { cn } from "@/lib/utils";

const props = defineProps({
  nonce: { type: String, required: false },
  asChild: { type: Boolean, required: false },
  as: { type: null, required: false },
  class: { type: null, required: false },
});

const delegatedProps = reactiveOmit(props, "class");

const forwarded = useForwardProps(delegatedProps);
</script>

<template>
  <ComboboxViewport
    data-slot="combobox-viewport"
    v-bind="forwarded"
    :class="
      cn(
        'max-h-[300px] scroll-py-1 overflow-x-hidden overflow-y-auto',
        props.class,
      )
    "
  >
    <slot />
  </ComboboxViewport>
</template>
