package com.company.auth.service.impl;

import com.company.auth.entity.Usuario;
import com.company.auth.models.AuthRequest;
import com.company.auth.models.AuthResponse;
import com.company.auth.repository.UsuarioRepository;
import com.company.auth.service.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public AuthResponse login(AuthRequest request){
        return null;
    }

    @Override
    public Usuario register(AuthRequest request) {
        return null;
    }
}