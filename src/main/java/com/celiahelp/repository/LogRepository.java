// LogRepository.java
package com.celiahelp.repository;

import com.celiahelp.model.Log;
import com.celiahelp.model.Incidencia;
import com.celiahelp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {
    // Encuentra todos los logs asociados a una incidencia
    List<Log> findByIncidencia(Incidencia incidencia);

    // Encuentra todos los logs asociados a un usuario
    List<Log> findByUsuario(Usuario usuario);
}
