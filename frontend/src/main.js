import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import { createWebHistory, createRouter } from 'vue-router'
import Welcome from './pages/BemVindo.vue'
import SelecaoLogin from './pages/SelecaoLogin.vue'
import TelaInicial from './pages/TelaInicial.vue'
import TelaDeLivrosCadastrados from './pages/TelaDeLivrosCadastrados.vue'
import TelaDeUsuariosCadastrados from './pages/TelaDeUsuariosCadastrados.vue'
import TelaDeTurma from './pages/TelaDeTurma.vue'

const routes = [
  { path: '/', component: Welcome },
  { path: '/SelecaoLogin', component: SelecaoLogin },
  { path: '/TelaInicial', component: TelaInicial },
  { path: '/TelaDeLivrosCadastrados', component: TelaDeLivrosCadastrados},
  { path: '/TelaDeUsuariosCadastrados', component: TelaDeUsuariosCadastrados },
  { path: '/TelaDeTurma', component: TelaDeTurma},
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

createApp(App).use(router).mount('#app')