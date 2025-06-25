package com.leiamais;

import com.leiamais.models.Cargo;
import com.leiamais.models.Coordenacao;
import com.leiamais.models.Usuario;
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
				System.out.println("Initial 'Coordenador' user created: coordenador/senha123");

			}
		};
	}

}
