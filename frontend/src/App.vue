<script setup>

import Layout from './layouts/Layout.vue'
import { RouterView } from 'vue-router';
import { Toaster } from '@/components/ui/sonner'
import 'vue-sonner/style.css'

</script>

<template>
  <Layout>
    <RouterView />
  </Layout>
</template>
