package com.company.auth.service.impl;

import com.company.auth.entity.Usuario;
import com.company.auth.models.AuthRequest;
import com.company.auth.models.AuthResponse;
import com.company.auth.repository.UsuarioRepository;
import com.company.auth.security.JwtProvider;
import com.company.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtProvider jwtProvider;

    @Override
    public Usuario register(AuthRequest request) {
        if (usuarioRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Verifica la peticion, recurso ya existe (Usuario)");
        }

        Usuario us = new Usuario();
        us.setUsername(request.getUsername());
        // Guardando contraseña en texto plano
        us.setPassword(request.getPassword());

        return usuarioRepository.save(us);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        // Obtenemos el usuario de la DB por su username (y si no existe, error 401)
        Usuario us = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas"));

        // Validamos la contraseña exacto como texto plano
        if (!us.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas");
        }

        // Generamos el json web token de respuesta
        String token = jwtProvider.generateToken(us.getUsername());
        return new AuthResponse(token);
    }
}
