package com.celiahelp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO {
    private Long id;

    @NotNull(message = "La incidencia asociada es obligatoria")
    private Long incidenciaId;

    @NotNull(message = "El usuario asociado es obligatorio")
    private Long usuarioId;

    @NotNull(message = "La acción no puede ser nula")
    private String accion;
    private LocalDateTime fecha;

}
