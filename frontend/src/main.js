import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import { createWebHistory, createRouter } from 'vue-router'
import Welcome from './pages/Welcome.vue'
import SelecaoLogin from './pages/SelecaoLogin.vue'
import InicialSystem from './pages/InicialSystem.vue'

const routes = [
  { path: '/', component: Welcome },
  { path: '/SelecaoLogin', component: SelecaoLogin },
  { path: '/InicialSystem', component: InicialSystem },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

createApp(App).use(router).mount('#app')