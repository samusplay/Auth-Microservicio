package com.company.auth.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
//Dto Para el Login
public class AuthRequest {

    @NotBlank(message = "El username no puede estar vacío")
    private String username;

    @NotBlank(message = "El password no puede estar vacío")
    private String password;
}
