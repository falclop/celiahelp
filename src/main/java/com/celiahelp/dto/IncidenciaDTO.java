package com.celiahelp.dto;

import java.time.LocalDateTime;

public class IncidenciaDTO {

    private Long id;
    private String titulo;
    private String descripcion;
    private String prioridad;
    private String estado;
    private LocalDateTime fechaCreacion;
    private String nombreRemitente;
    private String emailRemitente;
    private Long gestionadaPorId;

    public IncidenciaDTO() {}

    public IncidenciaDTO(Long id, String titulo, String descripcion,
                         String prioridad, String estado,
                         LocalDateTime fechaCreacion,
                         String nombreRemitente, String emailRemitente,
                         Long gestionadaPorId) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.nombreRemitente = nombreRemitente;
        this.emailRemitente = emailRemitente;
        this.gestionadaPorId = gestionadaPorId;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getNombreRemitente() { return nombreRemitente; }
    public void setNombreRemitente(String nombreRemitente) { this.nombreRemitente = nombreRemitente; }

    public String getEmailRemitente() { return emailRemitente; }
    public void setEmailRemitente(String emailRemitente) { this.emailRemitente = emailRemitente; }

    public Long getGestionadaPorId() { return gestionadaPorId; }
    public void setGestionadaPorId(Long gestionadaPorId) { this.gestionadaPorId = gestionadaPorId; }
}
