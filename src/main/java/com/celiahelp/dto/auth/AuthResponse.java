package com.celiahelp.dto.auth;

public record AuthResponse(
        String accessToken,
        String tokenType,   // p.ej. "Bearer"
        @jakarta.validation.constraints.NotBlank(message = "El usuario no puede estar vacío") String expiresIn// milis o segs hasta expiración
) {}
