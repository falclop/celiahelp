package com.celiahelp.dto.auth;

public record AuthResponse(
        String accessToken,
        String tokenType,   // p.ej. "Bearer"
        long expiresIn      // milis o segs hasta expiración
) {}
