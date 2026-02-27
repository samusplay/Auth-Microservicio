package com.company.auth.api;

import com.company.auth.models.AuthRequest;
import com.company.auth.models.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UsuarioApi {

    ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request);

    ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request);
}