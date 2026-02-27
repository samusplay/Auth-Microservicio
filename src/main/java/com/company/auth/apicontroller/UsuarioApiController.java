package com.company.auth.apicontroller;

import com.company.auth.api.UsuarioApi;
import com.company.auth.entity.Usuario;
import com.company.auth.models.AuthRequest;
import com.company.auth.models.AuthResponse;
import com.company.auth.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth/prueba")
public class UsuarioApiController implements UsuarioApi {

    private final UsuarioService usuarioService;

    @Override
    public ResponseEntity<AuthResponse> login(AuthRequest request) {
        return ResponseEntity.ok(usuarioService.login(request));
    }

    @Override
    public ResponseEntity<Usuario> register(AuthRequest request) {
        return new ResponseEntity<>(usuarioService.register(request), HttpStatus.CREATED);
    }
}