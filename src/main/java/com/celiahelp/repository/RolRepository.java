package com.celiahelp.repository;

import com.celiahelp.model.Rol;
import com.celiahelp.model.Rol.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    /**
     * Permite buscar un rol por su tipo (GESTOR, ADMIN).
     */
    Optional<Rol> findByTipo(Tipo tipo);
}