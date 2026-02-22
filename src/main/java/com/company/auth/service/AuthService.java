package com.company.auth.service;

import com.company.auth.entity.Usuario;
import com.company.auth.models.AuthRequest;
import com.company.auth.models.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest request);

    Usuario register(AuthRequest request);
}
