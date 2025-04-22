package com.celiahelp.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO {
    private Long id;
    private Long incidenciaId;
    private Long usuarioId;
    private String accion;
    private LocalDateTime fecha;
}
