/*
  Entidades JPA con Lombok para Celi@Help
*/

// Incidencia.java
package com.celiahelp.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "incidencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Prioridad prioridad = Prioridad.BAJA;

    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.PENDIENTE;

    @Column(name = "nombre_remitente")
    private String nombreRemitente;

    @Column(name = "email_remitente")
    private String emailRemitente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gestionada_por")
    private Usuario gestionadaPor;

    public enum Prioridad { BAJA, MEDIA, ALTA }
    public enum Estado    { PENDIENTE, COMPLETADA }
}