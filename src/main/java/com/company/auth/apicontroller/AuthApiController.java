package com.company.auth.apicontroller;

import com.company.auth.api.AuthApi;
import com.company.auth.models.*;
import com.company.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
//@RequestMapping("/auth")
public class AuthApiController implements AuthApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<AuthResponse> login(AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @Override
    public ResponseEntity<RegisterResponse> register(RegisterRequest request) {
        RegisterResponse response=authService.register(request);
        //devolver respuesta
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<Map<String, String>> verifyCode(@Valid VerifyRequest request) {
        //devolver al servicio
        authService.verifyCode(request);

        //devolvemos la respuesta
        Map<String, String> response = new HashMap<>();
        response.put("message", "Cuenta verificada exitosamente. Ya puedes iniciar sesion");

        return ResponseEntity.ok(response);
    }


}
