package com.celiahelp.dto;

import com.celiahelp.model.Rol;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RolDTO {
    private Long id;
    private String tipo;

    public RolDTO(Long id, Rol.Tipo tipo) {
    }

    public RolDTO(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

}
