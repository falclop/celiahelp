package com.celiahelp.dto;

public record UsuarioDTO(
        Long id,
        String nombre,
        String email,
        String passwordHash,
        Long rolId
) {}
