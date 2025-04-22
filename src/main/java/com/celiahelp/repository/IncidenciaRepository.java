package com.celiahelp.repository;

import com.celiahelp.model.Incidencia;
import com.celiahelp.model.Incidencia.Estado;
import com.celiahelp.model.Incidencia.Prioridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {
    /**
     * Obtiene todas las incidencias con un estado específico.
     */
    List<Incidencia> findByEstado(Estado estado);

    /**
     * Obtiene todas las incidencias con una prioridad determinada.
     */
    List<Incidencia> findByPrioridad(Prioridad prioridad);

    /**
     * Obtiene incidencias creadas por un remitente específico.
     */
    List<Incidencia> findByNombreRemitente(String nombreRemitente);
}