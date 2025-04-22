package com.celiahelp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class IncidenciaDTO {

    // Getters y Setters
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

}
