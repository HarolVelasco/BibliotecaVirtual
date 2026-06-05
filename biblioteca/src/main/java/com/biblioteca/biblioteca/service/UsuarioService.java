package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.model.Usuario;
import com.biblioteca.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Instanciamos el encriptador de contraseñas
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Usuario registrarEstudiante(String username, String password) {
        // Encriptar la contraseña antes de guardarla
        String passwordEncriptada = encoder.encode(password);
        
        // Por defecto, todos los que se registren en el formulario web serán ESTUDIANTES
        Usuario nuevoUsuario = new Usuario(username, passwordEncriptada, "ROLE_STUDENT");
        
        return usuarioRepository.save(nuevoUsuario);
    }
}