package com.celiahelp.mapper;

import com.celiahelp.dto.LogDTO;
import com.celiahelp.model.Log;

public class LogMapper {

    public static LogDTO toDTO(Log log) {
        if (log == null) return null;
        return new LogDTO(
                log.getId(),
                log.getAccion(),
                log.getFecha(),
                UsuarioMapper.toDTO(log.getUsuario())
        );
    }

    public static Log toEntity(LogDTO dto) {
        if (dto == null) return null;
        Log log = new Log();
        log.setId(dto.getId());
        log.setAccion(dto.getAccion());
        log.setFecha(dto.getFecha());
        log.setUsuario(UsuarioMapper.toEntity(dto.getUsuario()));
        return log;
    }
}
