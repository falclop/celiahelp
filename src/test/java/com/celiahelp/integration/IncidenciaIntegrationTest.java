// src/test/java/com/celiahelp/integration/IncidenciaIntegrationTest.java
package com.celiahelp.integration;

import com.celiahelp.dto.IncidenciaDTO;
import com.celiahelp.model.Usuario;
import com.celiahelp.repository.UsuarioRepository;
import com.celiahelp.repository.IncidenciaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.MySQLContainer;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IncidenciaIntegrationTest {

    @Container
    static MySQLContainer<?> mysql =
            new MySQLContainer<>("mysql:8.0")
                    .withUsername("foo")
                    .withPassword("foo")
                    .withDatabaseName("celiaHelp");

    @DynamicPropertySource
    static void propiedades(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",      mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Test
    void flujoCompleto_postYget() {
        // 1) Creamos un usuario para FK
        Usuario u = new Usuario();
        u.setNombre("Test");
        u.setEmail("t@t.com");
        u.setPasswordHash("pw");
        usuarioRepository.save(u);

        // 2) Armamos DTO, usamos URL completa
        IncidenciaDTO dto = new IncidenciaDTO(
                null,
                "Int",
                "Desc",
                "MEDIA",
                "PENDIENTE",
                null,
                "N",
                "e@mail",
                u.getId()
        );

        ResponseEntity<IncidenciaDTO> post = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/incidencias",
                dto,
                IncidenciaDTO.class
        );
        assertThat(post.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Long id = post.getBody().getId();
        assertThat(id).isNotNull();

        // 3) Recuperamos por ID
        ResponseEntity<IncidenciaDTO> get = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/incidencias/" + id,
                IncidenciaDTO.class
        );
        assertThat(get.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(get.getBody().getTitulo()).isEqualTo("Int");
    }
}
