package com.biblioteca.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String titulo;
    private String autor;
    private String nombreArchivo; // El nombre físico del PDF en tu disco duro
    private String tipoContenido; // Guardará si es "application/pdf", etc.

    // Constructor vacío (Obligatorio para Hibernate/JPA)
    public Libro() {}

    public Libro(String titulo, String autor, String nombreArchivo, String tipoContenido) {
        this.titulo = titulo;
        this.autor = autor;
        this.nombreArchivo = nombreArchivo;
        this.tipoContenido = tipoContenido;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getNombreArchivo() { return nombreArchivo; }
    public void setNombreArchivo(String nombreArchivo) { this.nombreArchivo = nombreArchivo; }
    public String getTipoContenido() { return tipoContenido; }
    public void setTipoContenido(String tipoContenido) { this.tipoContenido = tipoContenido; }
}