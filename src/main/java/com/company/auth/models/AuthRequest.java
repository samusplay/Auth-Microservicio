package com.company.auth.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {

    @NotBlank (message = "El username no puede estar vacío")
    private String username;

    @NotBlank (message = "La constraseña no puede estar vacía")
    private String password;
}
