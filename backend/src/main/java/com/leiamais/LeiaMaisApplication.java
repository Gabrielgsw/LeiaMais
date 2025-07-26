package com.leiamais;

import com.leiamais.models.Aluno;
import com.leiamais.models.Cargo;
import com.leiamais.models.Coordenacao;
import com.leiamais.models.Professor;
import com.leiamais.models.Usuario;
import com.leiamais.models.Livro;
import com.leiamais.repositories.LivroRepository;
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

    LeiaMaisApplication(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
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
						.allowedHeaders("*");
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

			if (usuarioRepository.findByNome("aluno").isEmpty()) {
				Aluno aluno = new Aluno();
				aluno.setNome("aluno");
				aluno.setSenha("senha123");
				aluno.setEmail("aluno@example.com");
				aluno.setCargo(Cargo.ALUNO);
				aluno.setCpf("222.222.222-22");
				aluno.setMatricula("123456789");
				aluno.setDataNascimento(null);

				usuarioRepository.save(aluno);
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
				Aluno aluno = new Aluno();
				aluno.setNome("artur");
				aluno.setSenha("senha123");
				aluno.setEmail("artur@example.com");
				aluno.setCargo(Cargo.ALUNO);
				aluno.setCpf("444.444.444-44");
				aluno.setMatricula("123456710");
				aluno.setDataNascimento(null);

				usuarioRepository.save(aluno);
			}

			if (usuarioRepository.findByNome("aecio").isEmpty()) {
				Aluno aluno = new Aluno();
				aluno.setNome("aecio");
				aluno.setSenha("senha123");
				aluno.setEmail("aecio@example.com");
				aluno.setCargo(Cargo.ALUNO);
				aluno.setCpf("555.555.555-55");
				aluno.setMatricula("123456711");
				aluno.setDataNascimento(null);

				usuarioRepository.save(aluno);
			}

			if (usuarioRepository.findByNome("gabriel").isEmpty()) {
				Aluno aluno = new Aluno();
				aluno.setNome("gabriel");
				aluno.setSenha("senha123");
				aluno.setEmail("gabriel@example.com");
				aluno.setCargo(Cargo.ALUNO);
				aluno.setCpf("777.777.777-77");
				aluno.setMatricula("123456712");
				aluno.setDataNascimento(null);

				usuarioRepository.save(aluno);
			}

			if (usuarioRepository.findByNome("heitor").isEmpty()) {
				Aluno aluno = new Aluno();
				aluno.setNome("heitor");
				aluno.setSenha("senha123");
				aluno.setEmail("heitor@example.com");
				aluno.setCargo(Cargo.ALUNO);
				aluno.setCpf("888.888.888-88");
				aluno.setMatricula("123456713");
				aluno.setDataNascimento(null);

				usuarioRepository.save(aluno);
			}

			if (usuarioRepository.findByNome("giovanna").isEmpty()) {
				Aluno aluno = new Aluno();
				aluno.setNome("giovanna");
				aluno.setSenha("senha123");
				aluno.setEmail("giovanna@example.com");
				aluno.setCargo(Cargo.ALUNO);
				aluno.setCpf("999.999.999-99");
				aluno.setMatricula("123456714");
				aluno.setDataNascimento(null);

				usuarioRepository.save(aluno);
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
		};
	}

}
