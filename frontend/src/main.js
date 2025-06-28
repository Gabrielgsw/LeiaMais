import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import { createWebHistory, createRouter } from 'vue-router'
import Welcome from './pages/BemVindo.vue'
import SelecaoLogin from './pages/SelecaoLogin.vue'
import TelaInicial from './pages/TelaInicial.vue'
import TelaDeLivrosCadastrados from './pages/TelaDeLivrosCadastrados.vue'
import TelaDeUsuariosCadastrados from './pages/TelaDeUsuariosCadastrados.vue'
import TeladeTurma from './pages/TelaDeTurma.vue'
import Telacriaratividade from './pages/Telacriaratividade.vue'
import TelaTestes from './pages/TelaTestes.vue'
import TelaResolverAtividade from './pages/TelaResolverAtividade.vue'
import TeladeLivroGeral from './pages/TeladeLivroGeral.vue'

const routes = [
  { path: '/', component: Welcome },
  { path: '/SelecaoLogin', component: SelecaoLogin },
  { path: '/TelaInicial', component: TelaInicial },
  { path: '/TeladeTurma', component: TeladeTurma },
  { path: '/TelaDeLivrosCadastrados', component: TelaDeLivrosCadastrados},
  { path: '/TelaDeUsuariosCadastrados', component: TelaDeUsuariosCadastrados },
  { path: '/Telacriaratividade', component: Telacriaratividade},
  { path: '/TelaTestes', component: TelaTestes},
  { path: '/TelaResolverAtividade', component: TelaResolverAtividade},
  { path: '/TeladeLivroGeral', component: TeladeLivroGeral},
  
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

createApp(App).use(router).mount('#app')