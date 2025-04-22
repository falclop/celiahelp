package com.celiahelp.integration;

import com.celiahelp.dto.IncidenciaDTO;
import com.celiahelp.model.Usuario;
import com.celiahelp.repository.UsuarioRepository;
import com.celiahelp.repository.IncidenciaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.MySQLContainer;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IncidenciaIntegrationTest {

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("celiaHelp")
            .withUsername("celiahelp")
            .withPassword("celiahelp");

    @DynamicPropertySource
    static void propiedades(DynamicPropertyRegistry reg) {
        reg.add("spring.datasource.url", mysql::getJdbcUrl);
        reg.add("spring.datasource.username", mysql::getUsername);
        reg.add("spring.datasource.password", mysql::getPassword);
    }

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Test
    void flujoCompleto_postYget() {
        // Creamos un remitente en tabla usuarios (para pruebas de foreign key)
        Usuario u = new Usuario();
        u.setNombre("Test");
        u.setEmail("t@t.com");
        u.setPasswordHash("pw");
        usuarioRepository.save(u);

        IncidenciaDTO dto = new IncidenciaDTO(
                null,"Int","Desc","MEDIA","PENDIENTE",
                null,"N","e@mail", u.getId()
        );

        ResponseEntity<IncidenciaDTO> post = restTemplate.postForEntity(
                "/api/incidencias", dto, IncidenciaDTO.class);
        assertThat(post.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Long id = post.getBody().getId();
        assertThat(id).isNotNull();

        ResponseEntity<IncidenciaDTO> get = restTemplate.getForEntity(
                "/api/incidencias/" + id, IncidenciaDTO.class);
        assertThat(get.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(get.getBody().getTitulo()).isEqualTo("Int");
    }
}
