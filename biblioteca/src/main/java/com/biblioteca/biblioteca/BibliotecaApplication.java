package com.biblioteca.biblioteca;

import com.biblioteca.biblioteca.model.Usuario;
import com.biblioteca.biblioteca.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	// Código que se ejecuta en automático al levantar el servidor
	@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository) {
		return args -> {
			// Si no existe ningún usuario admin creado, lo insertamos
			if (usuarioRepository.findByUsername("admin").isEmpty()) {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				Usuario admin = new Usuario("admin", encoder.encode("admin123"), "ROLE_ADMIN");
				usuarioRepository.save(admin);
				System.out.println("⚠️ Usuario Administrador por defecto creado ('admin' / 'admin123')");
			}
		};
	}
}