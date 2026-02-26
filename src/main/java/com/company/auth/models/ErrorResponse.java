package com.company.auth.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ErrorResponse {
    private int status;          // Código HTTP (400, 401, 403, etc.)
    private String message;     // Mensaje amigable para el usuario
    private LocalDateTime timestamp;
    private String path;        // La URL que falló
    private Map<String, String> validationErrors;
}
