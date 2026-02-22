package com.company.auth.security;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {
    // Genera automáticamente una clave para firmar.
    // En producción se colocaría esta clave en application.yaml
    private final SecretKey key = Jwts.SIG.HS256.key().build();

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000)) // Expira en 1 hora
                .signWith(key)
                .compact();
    }
}
