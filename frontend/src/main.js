import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import { createWebHistory, createRouter } from 'vue-router'
import Welcome from './pages/BemVindo.vue'
import SelecaoLogin from './pages/SelecaoLogin.vue'
import InicialSystem from './pages/TelaInicial.vue'
import BookScreen from './pages/TelaDeLivrosCadastrados.vue'
import UserScreen from './pages/TelaDeUsuariosCadastrados.vue'

const routes = [
  { path: '/', component: Welcome },
  { path: '/SelecaoLogin', component: SelecaoLogin },
  { path: '/InicialSystem', component: InicialSystem },
  { path: '/BookScreen', component: BookScreen },
  { path: '/UserScreen', component: UserScreen },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

createApp(App).use(router).mount('#app')