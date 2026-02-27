package com.company.auth.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {

    @NotBlank (message = "El username no puede estar vacío")
    private String username;

    @NotBlank (message = "La constraseña no puede estar vacía")
    private String password;

    public String getNombre() {
        return "";
    }

    public String getEmail() {
        return "";
    }

    public String getTelefono() {
        return "";
    }

    public class UsuarioRequest {

        private String nombre;
        private String email;
        private String password;
        private String telefono;
    }
}