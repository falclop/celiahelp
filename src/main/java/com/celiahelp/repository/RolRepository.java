package com.celiahelp.repository;

import com.celiahelp.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByTipo(Rol.Tipo tipo);
}
