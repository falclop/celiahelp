// src/main/java/com/celiahelp/controller/AuthController.java
package com.celiahelp.controller;

import com.celiahelp.dto.auth.AuthResponse;
import com.celiahelp.dto.auth.LoginRequest;
import com.celiahelp.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtTokenProvider tokenProvider;

    public AuthController(AuthenticationManager authManager,
                          JwtTokenProvider tokenProvider) {
        this.authManager = authManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest loginReq) {
        var authToken = new UsernamePasswordAuthenticationToken(
                loginReq.email(),
                loginReq.password()
        );
        var auth = authManager.authenticate(authToken);
        String token = tokenProvider.generateToken(auth);

        // Aquí le pasamos los tres argumentos: token, tipo y usuario
        return ResponseEntity.ok(
                new AuthResponse(
                        token,
                        "Bearer",                   // tipo de esquema
                        loginReq.email()         // nombre de usuario
                )
        );
    }
}
