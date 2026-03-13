package com.company.auth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {
    //clave estatica para que lo decifre el gateway
    private final String SECRET_WORD = "proyecto_delivery_universidad_super_secreta_2026_clave_segura";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_WORD.getBytes());

    public String generateToken(String username,Long userId) {
        return Jwts.builder()
                .subject(username)
                .claim("userId",userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000)) // Expira en 1 hora
                .signWith(key)
                .compact();
    }
}
