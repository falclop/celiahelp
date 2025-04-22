package com.celiahelp.mapper;

import com.celiahelp.dto.IncidenciaDTO;
import com.celiahelp.model.Incidencia;
import com.celiahelp.model.Usuario;
import com.celiahelp.repository.UsuarioRepository;

public class IncidenciaMapper {

    public static IncidenciaDTO toDTO(Incidencia incidencia) {
        if (incidencia == null) return null;

        return new IncidenciaDTO(
                incidencia.getId(),
                incidencia.getTitulo(),
                incidencia.getDescripcion(),
                incidencia.getPrioridad() != null ? incidencia.getPrioridad().name() : null,
                incidencia.getEstado() != null ? incidencia.getEstado().name() : null,
                incidencia.getFechaCreacion(),
                incidencia.getNombreRemitente(),
                incidencia.getEmailRemitente(),
                incidencia.getGestionadaPor() != null ? incidencia.getGestionadaPor().getId() : null
        );
    }

    public static Incidencia toEntity(IncidenciaDTO dto, UsuarioRepository usuarioRepository) {
        if (dto == null) return null;

        Incidencia incidencia = new Incidencia();

        incidencia.setId(dto.getId());
        incidencia.setTitulo(dto.getTitulo());
        incidencia.setDescripcion(dto.getDescripcion());

        // Convertir String a Enum
        if (dto.getPrioridad() != null) {
            try {
                incidencia.setPrioridad(Incidencia.Prioridad.valueOf(dto.getPrioridad()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Valor de prioridad no válido: " + dto.getPrioridad());
            }
        }

        if (dto.getEstado() != null) {
            try {
                incidencia.setEstado(Incidencia.Estado.valueOf(dto.getEstado()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Valor de estado no válido: " + dto.getEstado());
            }
        }

        // NO copiar fechaCreacion desde el DTO
        // incidencia.setFechaCreacion(dto.getFechaCreacion());

        incidencia.setNombreRemitente(dto.getNombreRemitente());
        incidencia.setEmailRemitente(dto.getEmailRemitente());

        if (dto.getGestionadaPorId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getGestionadaPorId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getGestionadaPorId()));
            incidencia.setGestionadaPor(usuario);
        }

        return incidencia;
    }
}
