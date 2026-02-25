package com.company.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Configurando el sistema para Encriptar las contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilitar CSRF es correcto para APIs REST con JWT
                .csrf(AbstractHttpConfigurer::disable)

                // Mantener la sesión STATELESS es perfecto para microservicios y JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 2. Ajustamos las reglas de autorización
                .authorizeHttpRequests(auth -> auth
                        // Solo exponemos las rutas del controlador de autenticación
                        .requestMatchers("/register", "/login", "/verify").permitAll()

                        // Cualquier otro endpoint interno de este microservicio exigirá autenticación
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
