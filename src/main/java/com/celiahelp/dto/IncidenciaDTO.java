package com.celiahelp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class IncidenciaDTO {

    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 100, message = "El título no puede tener más de 100 caracteres")
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    private String prioridad; // Se validará en el mapper con Enum.valueOf

    private String estado;

    private LocalDateTime fechaCreacion;

    @NotBlank(message = "El nombre del remitente es obligatorio")
    private String nombreRemitente;

    @Email(message = "El email debe tener un formato válido")
    @NotBlank(message = "El email del remitente es obligatorio")
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
