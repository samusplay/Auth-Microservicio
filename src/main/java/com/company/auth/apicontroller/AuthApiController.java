package com.company.auth.apicontroller;

import com.company.auth.api.AuthApi;
import com.company.auth.entity.Usuario;
import com.company.auth.models.AuthRequest;
import com.company.auth.models.AuthResponse;
import com.company.auth.models.RegisterRequest;
import com.company.auth.models.VerifyRequest;
import com.company.auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
//@RequestMapping("/auth")
public class AuthApiController implements AuthApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<AuthResponse> login(AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @Override
    public ResponseEntity<Usuario> register(RegisterRequest request) {
        //Delegamos al servicio
        return new ResponseEntity<>(authService.register(request),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> verifyCode(VerifyRequest request) {
        authService.verifyCode(request);
        return ResponseEntity.ok("Cuenta verificada exitosamente.Ya puedes iniciar sesion");
    }


}
