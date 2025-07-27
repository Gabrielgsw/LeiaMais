package com.leiamais;

import com.leiamais.models.Aluno;
import com.leiamais.models.Cargo;
import com.leiamais.models.Coordenacao;
import com.leiamais.models.Professor;
import com.leiamais.models.Turma;
import com.leiamais.models.Usuario;
import com.leiamais.models.Livro;
import com.leiamais.repositories.LivroRepository;
import com.leiamais.repositories.TurmaRepository;
import com.leiamais.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class LeiaMaisApplication {

    private final LivroRepository livroRepository;
	private final TurmaRepository turmaRepository;

	public LeiaMaisApplication(LivroRepository livroRepository, TurmaRepository turmaRepository) {
		this.livroRepository = livroRepository;
		this.turmaRepository = turmaRepository;
	}
	

	public static void main(String[] args) {
		SpringApplication.run(LeiaMaisApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("/**")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}

	@Bean
	@Profile("!test")
	public CommandLineRunner initData(UsuarioRepository usuarioRepository) { // Removed PasswordEncoder
		return args -> {
			if (usuarioRepository.findByNome("coordenador").isEmpty()) {
				Coordenacao adminUser = new Coordenacao();
				adminUser.setNome("coordenador");
				adminUser.setSenha("senha123");
				adminUser.setEmail("coordenador@example.com");
				adminUser.setCargo(Cargo.COORDENADOR);
				adminUser.setCpf("111.111.111-11");
				adminUser.setDataNascimento(null);

				usuarioRepository.save(adminUser);
				System.out.println("Initial 'Coordenador' user created: coordenador@example.com/senha123");
			}

			if (usuarioRepository.findByNome("machado").isEmpty()) {
				Aluno machado = new Aluno();
				machado.setNome("machado");
				machado.setSenha("senha123");
				machado.setEmail("machadodeassis@example.com");
				machado.setCargo(Cargo.ALUNO);
				machado.setCpf("222.222.222-22");
				machado.setMatricula("123456789");
				machado.setDataNascimento(null);
				machado.setPontos(26);

				usuarioRepository.save(machado);
			}

			if (usuarioRepository.findByNome("professor").isEmpty()) {
				Professor professor = new Professor();
				professor.setNome("professor");
				professor.setSenha("senha123");
				professor.setEmail("professor@example.com");
				professor.setCargo(Cargo.PROFESSOR);
				professor.setCpf("333.333.333-33");
				professor.setDataNascimento(null);

				usuarioRepository.save(professor);
			}

			if (usuarioRepository.findByNome("artur").isEmpty()) {
				Aluno artur = new Aluno();
				artur.setNome("artur");
				artur.setSenha("senha123");
				artur.setEmail("artur@example.com");
				artur.setCargo(Cargo.ALUNO);
				artur.setCpf("444.444.444-44");
				artur.setMatricula("123456710");
				artur.setDataNascimento(null);
				artur.setPontos(7);

				usuarioRepository.save(artur);
			}

			if (usuarioRepository.findByNome("aecio").isEmpty()) {
				Aluno aecio = new Aluno();
				aecio.setNome("aecio");
				aecio.setSenha("senha123");
				aecio.setEmail("aecio@example.com");
				aecio.setCargo(Cargo.ALUNO);
				aecio.setCpf("555.555.555-55");
				aecio.setMatricula("123456711");
				aecio.setDataNascimento(null);
				aecio.setPontos(9);

				usuarioRepository.save(aecio);
			}

			if (usuarioRepository.findByNome("gabriel").isEmpty()) {
				Aluno gabriel = new Aluno();
				gabriel.setNome("gabriel");
				gabriel.setSenha("senha123");
				gabriel.setEmail("gabriel@example.com");
				gabriel.setCargo(Cargo.ALUNO);
				gabriel.setCpf("777.777.777-77");
				gabriel.setMatricula("123456712");
				gabriel.setDataNascimento(null);
				gabriel.setPontos(12);

				usuarioRepository.save(gabriel);
			}

			if (usuarioRepository.findByNome("heitor").isEmpty()) {
				Aluno heitor = new Aluno();
				heitor.setNome("heitor");
				heitor.setSenha("senha123");
				heitor.setEmail("heitor@example.com");
				heitor.setCargo(Cargo.ALUNO);
				heitor.setCpf("888.888.888-88");
				heitor.setMatricula("123456713");
				heitor.setDataNascimento(null);
				heitor.setPontos(3);

				usuarioRepository.save(heitor);
			}

			if (usuarioRepository.findByNome("giovanna").isEmpty()) {
				Aluno giovanna = new Aluno();
				giovanna.setNome("giovanna");
				giovanna.setSenha("senha123");
				giovanna.setEmail("giovanna@example.com");
				giovanna.setCargo(Cargo.ALUNO);
				giovanna.setCpf("999.999.999-99");
				giovanna.setMatricula("123456714");
				giovanna.setDataNascimento(null);
				giovanna.setPontos(15);

				usuarioRepository.save(giovanna);
			}

			// Livro, Turma
			if (livroRepository.findByISBN("9788522005239").isEmpty()) {
				Livro livro = new Livro();
				livro.setTitulo("O pequeno príncipe");
				livro.setISBN("9788522005239");
				livro.setLivroUrl("1mZa8gQGbms46Uh2_vjUv6riagwUTfLLs");
				livro.setAutor("Antoine de Saint-Exupéry");
				
				livroRepository.save(livro);
			}

			if (turmaRepository.findByNome("Turma A").isEmpty()) {
				Turma turma = new Turma();
				turma.setNome("Turma A");
				//turma.setAlunosMatriculados(new ArrayList<>());
				//turma.setProfessores(new ArrayList<>());
				//turma.setLivros(new ArrayList<>());
				//turma.setAtividades(new ArrayList<>());

				turmaRepository.save(turma);
			}
		};
	}

}
