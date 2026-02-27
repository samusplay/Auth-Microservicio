package com.company.auth.security;

import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtService {

    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    //Crear el token

    public String generateToken(UserDetails userDetails){
        return Jwts.builder ()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())//Fecha actual
                .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*60*24)) //24 horas
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

     }
     //Extraer el username
    public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
// Validar el token
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername());
    }

    // Obtener clave
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

};
