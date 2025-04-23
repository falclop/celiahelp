package com.celiahelp.mapper;

import com.celiahelp.dto.IncidenciaDTO;
import com.celiahelp.model.Incidencia;
import com.celiahelp.model.Usuario;
import com.celiahelp.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class IncidenciaMapperTest {

    @Test
    void toDTO_and_back() {
        // Setup entidad
        Usuario usuario = new Usuario();
        usuario.setId(10L);

        Incidencia entidad = new Incidencia();
        entidad.setId(1L);
        entidad.setTitulo("Test");
        entidad.setDescripcion("Descripción");
        entidad.setPrioridad(Incidencia.Prioridad.ALTA);
        entidad.setEstado(Incidencia.Estado.COMPLETADA);
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        entidad.setFechaCreacion(now);
        entidad.setNombreRemitente("Alice");
        entidad.setEmailRemitente("alice@test.com");
        entidad.setGestionadaPor(usuario);

        // toDTO
        IncidenciaDTO dto = IncidenciaMapper.toDTO(entidad);
        assertEquals(1L, dto.getId());
        assertEquals("Test", dto.getTitulo());
        assertEquals("Descripción", dto.getDescripcion());
        assertEquals("ALTA", dto.getPrioridad());
        assertEquals("COMPLETADA", dto.getEstado());
        assertEquals(now, dto.getFechaCreacion());
        assertEquals("Alice", dto.getNombreRemitente());
        assertEquals("alice@test.com", dto.getEmailRemitente());
        assertEquals(10L, dto.getGestionadaPorId());

        // toEntity
        UsuarioRepository repo = Mockito.mock(UsuarioRepository.class);
        Mockito.when(repo.findById(10L)).thenReturn(Optional.of(usuario));
        Incidencia vuelta = IncidenciaMapper.toEntity(dto, repo);

        assertEquals(entidad.getId(), vuelta.getId());
        assertEquals(entidad.getTitulo(), vuelta.getTitulo());
        assertEquals(entidad.getDescripcion(), vuelta.getDescripcion());
        assertEquals(entidad.getPrioridad(), vuelta.getPrioridad());
        assertEquals(entidad.getEstado(), vuelta.getEstado());
        assertEquals(entidad.getNombreRemitente(), vuelta.getNombreRemitente());
        assertEquals(entidad.getEmailRemitente(), vuelta.getEmailRemitente());
        assertSame(usuario, vuelta.getGestionadaPor());
    }
}
