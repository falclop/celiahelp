// src/main/java/com/celiahelp/security/CustomUserDetailsService.java
package com.celiahelp.security;

import com.celiahelp.model.Usuario;
import com.celiahelp.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado con email: " + email)
                );

        User.UserBuilder builder = User.withUsername(usuario.getEmail())
                .password(usuario.getPasswordHash());

        // Si tu usuario tiene un rol (Entidad Rol con campo 'nombre'), conviértelo en autoridad
        if (usuario.getRol() != null) {
            // Spring añade prefijo "ROLE_" automáticamente cuando usas .roles(...)
            builder.roles(String.valueOf(usuario.getRol().getTipo()));
        }

        return builder.build();
    }
}
