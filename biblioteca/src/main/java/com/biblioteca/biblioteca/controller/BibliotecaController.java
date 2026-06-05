package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Libro;
import com.biblioteca.biblioteca.repository.LibroRepository;
import com.biblioteca.biblioteca.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Set;

@SuppressWarnings("unused")
@Controller
public class BibliotecaController {

    private final LibroRepository libroRepository;

    private final UploadFileService uploadFileService;

    BibliotecaController(LibroRepository libroRepository, UploadFileService uploadFileService) {
        this.libroRepository = libroRepository;
        this.uploadFileService = uploadFileService;
    }

    @GetMapping("/")
    public String index(Authentication authentication) {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            return "redirect:/admin/libros";
        }
        return "redirect:/estudiante/libros";
    }
    

    // PANEL DE ADMINISTRADOR: Muestra la lista y el formulario de subida
  @GetMapping("/admin/libros")
    public String panelAdmin(Model model, Authentication authentication) {
        model.addAttribute("libros", libroRepository.findAll());
        // Enviamos el nombre del usuario actual al HTML
        model.addAttribute("usuarioLogueado", authentication.getName()); 
        return "admin-panel";
    }


    // Acción de subir un nuevo libro
    @PostMapping("/admin/libros/subir")
    public String subirLibro(@RequestParam("titulo") String titulo,
                             @RequestParam("autor") String autor,
                             @RequestParam("archivo") MultipartFile archivo) {
        try {
            if (!archivo.isEmpty()) {
                // 1. Guardar el archivo físico
                String nombreUnico = uploadFileService.saveFile(archivo);
                // 2. Guardar el registro en la base de datos H2
                Libro libro = new Libro(titulo, autor, nombreUnico, archivo.getContentType());
                libroRepository.save(libro);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/libros";
    }

    // Acción de eliminar un libro
    @GetMapping("/admin/libros/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id) {
        libroRepository.findById(id).ifPresent(libro -> {
            // 1. Borrar el archivo físico
            uploadFileService.deleteFile(libro.getNombreArchivo());
            // 2. Borrar de la base de datos
            libroRepository.delete(libro);
        });
        return "redirect:/admin/libros";
    }

    // PANEL DE ESTUDIANTE: Solo muestra la lista para leer o descargar
@GetMapping("/estudiante/libros")
    public String panelEstudiante(Model model, Authentication authentication) {
        model.addAttribute("libros", libroRepository.findAll());
        // Enviamos el nombre del usuario actual al HTML
        model.addAttribute("usuarioLogueado", authentication.getName());
        return "estudiante-panel";
    }
    // NUEVO: Ruta para ver el archivo en línea (inline) o descargarlo (attachment)
    @GetMapping("/estudiante/libros/archivo/{id}")
    public org.springframework.http.ResponseEntity<org.springframework.core.io.Resource> descargarArchivo(
            @PathVariable Long id, 
            @RequestParam(value = "descargar", defaultValue = "false") boolean descargar) {
        
        // 1. Buscar el registro del libro en la base de datos
        java.util.Optional<Libro> optLibro = libroRepository.findById(id);
        if (optLibro.isEmpty()) {
            return org.springframework.http.ResponseEntity.notFound().build();
        }
        
        Libro libro = optLibro.get();
        
        try {
            // 2. Cargar el archivo físico desde la carpeta uploads
            java.nio.file.Path path = java.nio.file.Paths.get("uploads").resolve(libro.getNombreArchivo());
            org.springframework.core.io.Resource resource = new org.springframework.core.io.UrlResource(path.toUri());
            
            if (!resource.exists() || !resource.isReadable()) {
                return org.springframework.http.ResponseEntity.notFound().build();
            }
            
            // 3. Configurar la disposición (Ver en línea vs Descargar)
            String disposicion = descargar ? "attachment" : "inline";
            
            // Quitamos el prefijo único del UUID para que el alumno lo baje con el nombre original lindo
            String nombreOriginalLimpio = libro.getNombreArchivo().substring(libro.getNombreArchivo().indexOf("_") + 1);

            return org.springframework.http.ResponseEntity.ok()
                    .contentType(org.springframework.http.MediaType.parseMediaType(libro.getTipoContenido()))
                    .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, 
                            disposicion + "; filename=\"" + nombreOriginalLimpio + "\"")
                    .body(resource);
                    
        } catch (java.net.MalformedURLException e) {
            e.printStackTrace();
            return org.springframework.http.ResponseEntity.internalServerError().build();
        }
    }
}