package com.celiahelp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Desactivar CSRF (API sin sesiones)
                .csrf(AbstractHttpConfigurer::disable)

                // Definición de reglas de autorización
                .authorizeHttpRequests(auth -> auth
                        // Swagger/OpenAPI
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                        // Sólo POST de incidencias es público
                        .requestMatchers(HttpMethod.POST, "/api/incidencias").permitAll()

                        // TODO: si tienes endpoints de login / auth, hazlos públicos
                        // .requestMatchers("/auth/**").permitAll()

                        // TODO: healthchecks, métricas…
                        // .requestMatchers("/actuator/**").permitAll()

                        // Todo lo demás en /api/incidencias/** requiere roles GESTOR o ADMIN
                        .requestMatchers("/api/incidencias/**").hasAnyRole("GESTOR", "ADMIN")

                        // Cualquier otra ruta (si la hubiera) también exige auth
                        .anyRequest().authenticated()
                )

                // Stateless: no mantenemos sesión
                .sessionManagement(sm -> sm
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Nuevo estilo para Resource Server JWT
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }

}
