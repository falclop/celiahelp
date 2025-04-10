package com.celiahelp.repository;

import com.celiahelp.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
    // Puedes añadir filtros por incidencia, usuario, etc.
}
