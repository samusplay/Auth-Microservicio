package com.company.auth.service.impl;

import com.company.auth.entity.Usuario;
import com.company.auth.models.AuthRequest;
import com.company.auth.models.AuthResponse;
import com.company.auth.repository.UsuarioRepository;
import com.company.auth.security.JwtService;
import com.company.auth.security.UserDetailsImpl;
import com.company.auth.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // ✅ REGISTER
    @Override
    public AuthResponse register(AuthRequest request) {

        var usuario = Usuario.builder()
                .nombre(request.getNombre())      // si tu AuthRequest lo tiene
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .telefono(request.getTelefono())  // si lo tienes en el request
                .build();

        usuarioRepository.save(usuario);

        var jwtToken = jwtService.generateToken(
                new UserDetailsImpl(usuario)
        );

        return new AuthResponse(jwtToken);
    }

    // ✅ LOGIN
    @Override
    public AuthResponse login(AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        var jwtToken = jwtService.generateToken(
                new UserDetailsImpl(usuario)
        );

        return new AuthResponse(jwtToken);
    }
}