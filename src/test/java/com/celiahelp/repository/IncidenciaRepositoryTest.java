// src/test/java/com/celiahelp/repository/IncidenciaRepositoryTest.java
package com.celiahelp.repository;

import com.celiahelp.model.Incidencia;
import com.celiahelp.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IncidenciaRepositoryTest {

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("foo")
            .withPassword("foo");

    @DynamicPropertySource
    static void mysqlProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",      mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void saveAndFindById() {
        // 1) Creamos y persistimos un usuario
        Usuario u = new Usuario();
        u.setNombre("Bob");
        u.setEmail("bob@example.com");
        u.setPasswordHash("secret");
        u = usuarioRepository.save(u);

        // 2) Creamos incidencia referenciando al usuario
        Incidencia inc = new Incidencia();
        inc.setTitulo("Título");
        inc.setDescripcion("Desc");
        inc.setFechaCreacion(LocalDateTime.now());
        inc.setPrioridad(Incidencia.Prioridad.MEDIA);
        inc.setEstado(Incidencia.Estado.PENDIENTE);
        inc.setNombreRemitente("Carol");
        inc.setEmailRemitente("carol@example.com");
        inc.setGestionadaPor(u);

        // 3) Salvamos y comprobamos
        Incidencia saved = incidenciaRepository.save(inc);
        assertThat(saved.getId()).isNotNull();

        Incidencia found = incidenciaRepository.findById(saved.getId()).orElseThrow();
        assertThat(found.getTitulo()).isEqualTo("Título");
        assertThat(found.getGestionadaPor().getId()).isEqualTo(u.getId());
    }
}
