package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    // Mostrar el Login personalizado
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Mostrar el formulario de Registro
    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    // Procesar el envío de datos del formulario de Registro
    @PostMapping("/registro")
    public String procesarRegistro(@RequestParam("username") String username,
                                   @RequestParam("password") String password) {
        try {
            usuarioService.registrarEstudiante(username, password);
            return "redirect:/registro?exito"; // Redirige mostrando la alerta verde
        } catch (Exception e) {
            return "redirect:/registro?error";
        }
    }
}