package com.company.auth.service;

import com.company.auth.entity.Usuario;
import com.company.auth.models.AuthRequest;
import com.company.auth.models.AuthResponse;
import com.company.auth.models.RegisterRequest;
import com.company.auth.models.VerifyRequest;

public interface AuthService {
    //Metodo para la autenticacion
    AuthResponse login(AuthRequest request);

    //asigancion nuevo dto
    Usuario register(RegisterRequest request);

    //nuevo metodo para verificar el codigo
    void verifyCode(VerifyRequest request);
}
