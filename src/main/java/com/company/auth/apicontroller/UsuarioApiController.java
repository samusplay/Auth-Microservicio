package com.company.auth.apicontroller;

import com.company.auth.api.AuthApi;
import com.company.auth.entity.Usuario;
import com.company.auth.models.AuthRequest;
import com.company.auth.models.AuthResponse;
import com.company.auth.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/prueba")
public class UsuarioApiController implements AuthApi {

    private final UsuarioService usuarioService;

    public UsuarioApiController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(usuarioService.login(request));
    }

    @Override
    public ResponseEntity<Usuario> register(@RequestBody AuthRequest request) {
        return new ResponseEntity<>(usuarioService.register(request), HttpStatus.CREATED);
    }
}
