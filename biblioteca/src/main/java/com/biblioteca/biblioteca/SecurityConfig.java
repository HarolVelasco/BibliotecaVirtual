package com.biblioteca.biblioteca;

import com.biblioteca.biblioteca.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Encriptador oficial
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/registro", "/css/**", "/js/**").permitAll() // Permitir entrar a la pantalla de registro sin loguearse
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/estudiante/**").hasAnyRole("STUDENT", "ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login").permitAll() // Usaremos nuestra propia pantalla de Login bonita más adelante
                .defaultSuccessUrl("/", true) // Al loguearse, redirigir al index dinámico
            )
            .logout(logout -> logout.permitAll())
            .csrf(csrf -> csrf.disable());
            
        return http.build();
    }

    // Conectar Spring Security con los usuarios guardados en la BD de H2
    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository usuarioRepository) {
        return username -> usuarioRepository.findByUsername(username)
                .map(u -> User.withUsername(u.getUsername())
                        .password(u.getPassword())
                        .authorities(u.getRol()) // Asigna su rol (ROLE_ADMIN o ROLE_STUDENT)
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }
}