package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Listo, esto ya nos da los métodos para buscar, guardar y borrar en H2
}