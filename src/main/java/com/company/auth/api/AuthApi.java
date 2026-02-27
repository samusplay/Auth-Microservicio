package com.company.auth.api;


import com.company.auth.entity.Usuario;
import com.company.auth.models.AuthRequest;
import com.company.auth.models.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthApi {

    @PostMapping("/login")
    ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request);

    @PostMapping("/register")
    ResponseEntity<Usuario> register(@RequestBody AuthRequest request);

}
