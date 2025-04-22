package com.celiahelp.repository;

import com.celiahelp.model.Incidencia;
import com.celiahelp.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class IncidenciaRepositoryTest {

    @Autowired
    private IncidenciaRepository incidenciaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void saveAndFindById() {
        // Creamos y persistimos un usuario
        Usuario u = new Usuario();
        u.setNombre("Bob");
        u.setEmail("bob@example.com");
        u.setPasswordHash("secret");
        u = usuarioRepository.save(u);

        // Creamos incidencia referenciando al usuario
        Incidencia inc = new Incidencia();
        inc.setTitulo("Título");
        inc.setDescripcion("Desc");
        inc.setFechaCreacion(LocalDateTime.now());
        inc.setPrioridad(Incidencia.Prioridad.MEDIA);
        inc.setEstado(Incidencia.Estado.PENDIENTE);
        inc.setNombreRemitente("Carol");
        inc.setEmailRemitente("carol@example.com");
        inc.setGestionadaPor(u);

        Incidencia saved = incidenciaRepository.save(inc);
        assertThat(saved.getId()).isNotNull();

        Incidencia found = incidenciaRepository.findById(saved.getId()).orElseThrow();
        assertThat(found.getTitulo()).isEqualTo("Título");
        assertThat(found.getGestionadaPor().getId()).isEqualTo(u.getId());
    }
}
