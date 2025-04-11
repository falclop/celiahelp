package com.celiahelp.repository;

import com.celiahelp.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findByIncidenciaId(Long incidenciaId);
    // Puedes añadir filtros por incidencia, usuario, etc.
}
