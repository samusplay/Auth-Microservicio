package com.microservicio.usuario.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDto {

    @NotBlank(message="El nombre es obligatorio")
    private String nombre;

    @NotBlank(message="El email es obligatorio")
    private String email;

    @NotBlank (message="La contraseña es obligatoria")
    private String password;

    @NotBlank (message="El teléfono es obligatorio")
    @Size(max= 20, message="El teléfono no se puede exceder de 20 carácteres")
    @Pattern(regexp = "^\\+\\d{7,15}$", message = "El teléfono debe tener formato internacional (Ej: +573001234567)")
    private  String telefono;
    }
