package com.celiahelp.repository;

import com.celiahelp.model.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {
    List<Incidencia> findByEstado(Incidencia.Estado estado);
}
