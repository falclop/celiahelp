package com.celiahelp.mapper;

import com.celiahelp.dto.LogDTO;
import com.celiahelp.model.Incidencia;
import com.celiahelp.model.Log;
import com.celiahelp.model.Usuario;

import java.time.LocalDateTime;

public class LogMapper {

    public static LogDTO toDTO(Log log) {
        return new LogDTO(
                log.getId(),
                log.getIncidencia() != null ? log.getIncidencia().getId() : null,
                log.getUsuario() != null ? log.getUsuario().getId() : null,
                log.getAccion().name(),
                log.getFecha()
        );
    }

    public static Log toEntity(LogDTO dto, Incidencia incidencia, Usuario usuario) {
        Log log = new Log();
        log.setId(dto.getId());
        log.setAccion(Log.Accion.valueOf(dto.getAccion()));
        log.setFecha(dto.getFecha() != null ? dto.getFecha() : LocalDateTime.now());
        log.setIncidencia(incidencia);
        log.setUsuario(usuario);
        return log;
    }
}
