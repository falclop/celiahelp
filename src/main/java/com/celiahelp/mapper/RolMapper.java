package com.celiahelp.mapper;

import com.celiahelp.dto.RolDTO;
import com.celiahelp.model.Rol;

public class RolMapper {

    public static RolDTO toDTO(Rol rol) {
        if (rol == null) return null;
        return new RolDTO(rol.getId(), rol.getTipo());
    }

    public static Rol toEntity(RolDTO dto) {
        if (dto == null) return null;
        Rol rol = new Rol();
        rol.setId(dto.getId());
        rol.setTipo(dto.getTipo());
        return rol;
    }
}
