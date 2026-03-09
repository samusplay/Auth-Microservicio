package com.company.auth.service;

import com.company.auth.entity.Usuario;
import com.company.auth.models.*;

public interface AuthService {
    //Metodo para la autenticacion
    AuthResponse login(AuthRequest request);

    //asigancion nuevo dto
    RegisterResponse register(RegisterRequest request);

    //nuevo metodo para verificar el codigo
    void verifyCode(VerifyRequest request);
}
