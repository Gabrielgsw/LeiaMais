# ⋆˚📚˖° Leia+ ⋆˚📚˖°
Sistema de incentivo à leitura paradidática com gamificação

## Cadeira
Engenharia de Software

## Integrantes do grupo
* Aécio José  
* Artur Guimarães  
* Gabriel Germano  
* Giovanna Neves  
* Heitor Santana  

## Descrição Geral do Projeto
O **Leia+** é um sistema web voltado para escolas, com o objetivo de promover o acesso a livros paradidáticos e incentivar o hábito da leitura entre os alunos de forma acessível, interativa e gamificada.  
Ele busca solucionar problemas relacionados à desmotivação dos alunos e à falta de acesso aos livros adotados no ambiente escolar, proporcionando um ambiente virtual de aprendizagem mais atrativo.

A plataforma poderá ser utilizada tanto por professores, que podem criar atividades relacionadas aos livros, quanto por alunos, que terão acesso aos livros, comentários e sistema de pontuação conforme sua interação com a leitura.

---

## Requisitos Funcionais

### 1. Gerenciamento de Usuários e Acesso
**Descrição:**  
Apenas o **administrador** pode cadastrar novos usuários (professores e alunos). Todos os usuários cadastrados podem acessar o sistema com seu login e têm interfaces distintas.

**Critérios de aceitação:**
* O sistema deve impedir o cadastro de usuários com e-mails já existentes.
* Somente o administrador tem acesso à tela de cadastro de usuários.
* Todos os usuários fazem login via e-mail e senha.
* Após o login, o sistema redireciona o usuário conforme seu tipo de perfil:
  * **Administrador:** painel de controle com acesso à gestão de usuários, livros e relatórios.
  * **Professor:** gerenciamento de turmas, atividades e acompanhamento do desempenho dos alunos.
  * **Aluno:** visualização de livros, atividades, comentários, pontuação e conquistas.

### 2. Gerenciamento de Livros Paradidáticos
**Descrição:**  
Apenas o administrador pode cadastrar, editar ou excluir livros. Professores apenas associam livros existentes às suas turmas.

**Critérios de aceitação:**
* O sistema deve impedir o cadastro de livros com o mesmo ISBN.
* Os campos obrigatórios no cadastro de livros são: título, autor, editora e o ISBN.
* Professores podem visualizar os livros cadastrados, mas não têm acesso à edição ou exclusão.
* Livros associados a atividades não podem ser excluídos até que as atividades sejam desfeitas.

### 3. Atividades Pedagógicas
**Descrição:**  
Professores podem criar atividades vinculadas a livros já cadastrados.

**Critérios de aceitação:**
* Apenas professores têm permissão para criar, editar ou excluir atividades.
* Toda atividade deve estar associada a um livro previamente cadastrado.
* Alunos visualizam e realizam apenas as atividades da sua turma.
* O sistema registra data, pontuação e desempenho do aluno em cada atividade.

### 4. Leitura e Interação
**Descrição:**  
Alunos podem acessar os livros, deixar comentários e avaliar as obras.

**Critérios de aceitação:**
* Alunos têm acesso aos livros associados à sua turma e ao banco de livros do sistema.
* O sistema deve permitir comentar e avaliar cada livro.
* Deve haver moderação automática para palavras inadequadas.

### 5. Gamificação
**Descrição:**  
Sistema de pontos e recompensas para motivar os alunos a participarem ativamente.

**Critérios de aceitação:**
* Alunos ganham pontos por leitura de livros e conclusão de atividades.
* Rankings por turma devem ser atualizados automaticamente com base na pontuação.
* Alunos visualizam suas conquistas em uma área específica do perfil.
* Conquistas devem ser simbólicas.

### 6. Histórico de Participação
**Descrição:**  
Registro das ações realizadas pelos alunos, com acesso por professores e administradores.

**Critérios de aceitação:**
* Alunos visualizam seu histórico com livros lidos, atividades feitas e pontos acumulados.
* Professores visualizam o desempenho de todos os alunos da sua turma.
* Administradores conseguem acompanhar dados gerais do sistema.
