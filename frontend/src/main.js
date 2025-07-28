import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import PrimeVue from 'primevue/config';
import Aura from '@primeuix/themes/aura';
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
import Telaleitura from './pages/Telaleitura.vue'
import TelaCorrigirAtividadeCoordenador from './pages/Coordenador/TelaCorrigirAtividadeCoordenador.vue'
import TeladeRanking from './pages/Coordenador/TeladeRanking.vue'
import TelaDeTurmaCoordenador from './pages/Coordenador/TelaDeTurmaCoordenador.vue';

//import telas de aluno:
import TelaInicialAluno from './pages/Aluno/TelaInicialAluno.vue'
import TelaDeTurmaAluno from './pages/Aluno/TelaDeTurmaAluno.vue'
import TelaBiblioteca from './pages/TelaBiblioteca.vue'
import TeladeRankingAluno from './pages/Aluno/TeladeRankingAluno.vue';

//import telas de professor:
import TelaInicialProfessor from './pages/Professor/TelaInicialProfessor.vue';
import TelaDeTurmaProfessor from './pages/Professor/TelaDeTurmaProfessor.vue';
import TelaCriarAtividadeProfessor from './pages/Professor/TelaCriarAtividadeProfessor.vue';
import TelaAlunosCorrecao from './pages/Professor/TelaAlunosCorrecao.vue';
import TelaCorrigirAtividadeProfessor from './pages/Professor/TelaCorrigirAtividadeProfessor.vue';
import TelaDeLivroAluno from './pages/Aluno/TelaDeLivroAluno.vue';
import TelaDeRankingProfessor from './pages/Professor/TelaDeRankingProfessor.vue';
import TelaDeLivroProfessor from './pages/Professor/TelaDeLivroProfessor.vue';

const routes = [
  { path: '/', component: BemVindo },
  { path: '/SelecaoLogin', component: SelecaoLogin },
  { path: '/TelaInicial', component: TelaInicial },
  { path: '/turma/:id', component: TelaDeTurma },
  { path: '/turma/:id/criar-atividade', component: Telacriaratividade },
  { path: '/TelaDeLivrosCadastrados', component: TelaDeLivrosCadastrados },
  { path: '/TelaDeUsuariosCadastrados', component: TelaDeUsuariosCadastrados },
  { path: '/TelaTestes', component: TelaTestes },
  { path: '/TelaResolverAtividade', component: TelaResolverAtividade },
  { path: '/livro/:isbn', component: TeladeLivroGeral },
  { path: '/Telaleitura', component: Telaleitura },
  { path: '/TelaCorrigirAtividadeCoordenador', component: TelaCorrigirAtividadeCoordenador },
  { path: '/TeladeRanking', component: TeladeRanking },
  { path: '/TelaDeTurmaCoordenador', component: TelaDeTurmaCoordenador },
  
   
  //telas de aluno:
  { path: '/TelaInicialAluno', component: TelaInicialAluno },
  { path: '/turma/minhaturma/:id', component: TelaDeTurmaAluno },
  { path: '/TelaResolverAtividadeCoordenador', component: TelaResolverAtividadeCoordenador },
  { path: '/TelaBiblioteca', component: TelaBiblioteca },
  { path: '/TeladeRankingAluno', component: TeladeRankingAluno },
  { path: '/livro/aluno/:isbn', component: TelaDeLivroAluno },

  //telas de professor:
  { path: '/TelaInicialProfessor', component: TelaInicialProfessor },
  { path: '/TelaDeTurmaProfessor', component: TelaDeTurmaProfessor },
  {path: '/TelaCriarAtividadeProfessor/:id/criar-atividade', component: TelaCriarAtividadeProfessor },
  {path: '/TelaAlunosCorrecao', component: TelaAlunosCorrecao },
  {path: '/TelaCorrigirAtividadeProfessor', component: TelaCorrigirAtividadeProfessor },
  {path: '/TelaDeRankingProfessor', component: TelaDeRankingProfessor },
  { path: '/livro/professor/:isbn', component: TelaDeLivroProfessor},

]

const router = createRouter({
  history: createWebHistory(),
  routes,
})


createApp(App).use(PrimeVue, {
  theme: {
    preset: Aura,
    darkModeSelector:'.my-app-dark'
  }
}).use(router).mount('#app')