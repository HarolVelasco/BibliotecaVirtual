package com.biblioteca.biblioteca.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileService {

    // Nombre de la carpeta donde se guardarán los archivos en tu computadora
    private final String folder = "uploads";

    public String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("El archivo está vacío");
        }

        // Crear la carpeta 'uploads' si no existe
        Path rootPath = Paths.get(folder);
        if (!Files.exists(rootPath)) {
            Files.createDirectories(rootPath);
        }

        // Generar un nombre único para el archivo (así evitamos que se sobrescriban si se llaman igual)
        String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = rootPath.resolve(uniqueFilename);

        // Guardar el archivo físicamente en el disco duro
        Files.copy(file.getInputStream(), filePath);

        return uniqueFilename; // Retornamos el nombre único para guardarlo en la base de datos
    }

    public void deleteFile(String filename) {
        Path filePath = Paths.get(folder).resolve(filename);
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            System.err.println("No se pudo eliminar el archivo físico: " + e.getMessage());
        }
    }
}