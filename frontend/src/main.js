import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import { createWebHistory, createRouter } from 'vue-router'
import BemVindo from './pages/BemVindo.vue'
import SelecaoLogin from './pages/SelecaoLogin.vue'
import TelaInicial from './pages/Coordenador/TelaInicial.vue'
import TelaDeLivrosCadastrados from './pages/Coordenador/TelaDeLivrosCadastrados.vue'
import TelaDeUsuariosCadastrados from './pages/Coordenador/TelaDeUsuariosCadastrados.vue'
import TelaDeTurma from './pages/Coordenador/TelaDeTurma.vue'
import Telacriaratividade from './pages/Coordenador/Telacriaratividade.vue'
import TelaTestes from './pages/TelaTestes.vue'
import TelaResolverAtividade from './pages/Aluno/TelaResolverAtividade.vue'
import TeladeLivroGeral from './pages/TeladeLivroGeral.vue'
import TelaResolverAtividadeCoordenador from './pages/Coordenador/TelaResolverAtividadeCoordenador.vue'


//import telas de aluno:
import TelaInicialAluno from './pages/Aluno/TelaInicialAluno.vue'
import TelaDeTurmaAluno from './pages/Aluno/TelaDeTurmaAluno.vue'

const routes = [
  { path: '/', component: BemVindo },
  { path: '/SelecaoLogin', component: SelecaoLogin },
  { path: '/TelaInicial', component: TelaInicial },
  { path: '/TeladeTurma', component: TelaDeTurma },
  { path: '/TelaDeLivrosCadastrados', component: TelaDeLivrosCadastrados},
  { path: '/TelaDeUsuariosCadastrados', component: TelaDeUsuariosCadastrados },
  { path: '/Telacriaratividade', component: Telacriaratividade},
  { path: '/TelaTestes', component: TelaTestes},
  { path: '/TelaResolverAtividade', component: TelaResolverAtividade},
  { path: '/TeladeLivroGeral', component: TeladeLivroGeral},
  //telas de aluno:
  { path: '/TelaInicialAluno', component: TelaInicialAluno},
  { path: '/TelaDeTurmaAluno', component: TelaDeTurmaAluno},
  { path: '/TelaResolverAtividadeCoordenador', component: TelaResolverAtividadeCoordenador},
  
  
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

createApp(App).use(router).mount('#app')