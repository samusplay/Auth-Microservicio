package com.company.auth.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Respuesta luego de iniciar Sesion
public class AuthResponse {
    private String token;
}
