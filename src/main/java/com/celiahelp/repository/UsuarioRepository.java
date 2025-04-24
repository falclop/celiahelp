package com.celiahelp.repository;

import com.celiahelp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    @Query("""
        select u
        from Usuario u
        join fetch u.rol
        where u.email = :email
        """)
    /*
     * Busca un usuario por su email (único).
     */
    Optional<Usuario> findByEmailWithRol(@Param("email") String email);
}