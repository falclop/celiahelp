package com.celiahelp.dto;

import com.celiahelp.model.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RolDTO {
    private Long id;

    @NotBlank(message = "El tipo de rol no puede estar vacío")
    @Size(min = 3, max = 50, message = "El tipo de rol debe tener entre 3 y 50 caracteres")
    private String tipo;

    public RolDTO(Long id, Rol.Tipo tipo) {
    }

    public RolDTO(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

}
