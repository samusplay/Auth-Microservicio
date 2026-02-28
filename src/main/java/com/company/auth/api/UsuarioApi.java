package com.company.auth.api;

import com.company.auth.models.AuthRequest;
import com.company.auth.models.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UsuarioApi {

    @PostMapping("/register")
    ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request);

    @PostMapping("/login")
    ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request);
}