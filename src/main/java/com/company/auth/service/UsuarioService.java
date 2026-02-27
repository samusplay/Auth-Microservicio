package com.company.auth.service;

import com.company.auth.models.AuthRequest;
import com.company.auth.models.AuthResponse;

public interface UsuarioService {

    AuthResponse register(AuthRequest request);

    AuthResponse login(AuthRequest request);
}