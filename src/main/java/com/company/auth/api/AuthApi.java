package com.company.auth.api;

import com.company.auth.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

import java.util.Map;

public interface AuthApi {

    //endpoint login
    @PostMapping("/login")
    ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request);

    //endpoint de registro
    @PostMapping("/register")
    ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request);

    //endpoint verificacion
    @PostMapping("/verify")
     ResponseEntity<Map<String, String>> verifyCode(@Valid @RequestBody VerifyRequest request);
}

