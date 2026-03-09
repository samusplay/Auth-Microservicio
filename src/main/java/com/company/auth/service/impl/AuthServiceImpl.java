package com.company.auth.service.impl;

import com.company.auth.entity.Role;
import com.company.auth.entity.Usuario;
import com.company.auth.exceptions.AccountNotVerifiedException;
import com.company.auth.exceptions.InvalidCredentialsException;
import com.company.auth.exceptions.VerificationException;
import com.company.auth.models.*;
import com.company.auth.repository.UsuarioRepository;
import com.company.auth.security.JwtProvider;
import com.company.auth.service.AuthService;
import com.company.auth.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtProvider jwtProvider;
    //inyeccion bean
    private final PasswordEncoder passwordEncoder;
    //servicio de email
    private final EmailService emailService;

    @Override
    public AuthResponse login(AuthRequest request) {

        // Obtenemos el usuario de la DB por su username (y si no existe, error 401)
        Usuario us = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException( "Credenciales incorrectas"));

        //validamos si la cuenta ya fue verificada por correo
        if (!us.isEnabled()) {
            throw new AccountNotVerifiedException( "Cuenta no verificada. Por favor revisa tu correo.");
        }

        // Validamos la contraseña exacto como texto plano
        if (!passwordEncoder.matches(request.getPassword(), us.getPassword())) {
            throw new InvalidCredentialsException( "Credenciales incorrectas");
        }

        // Generamos el json web token de respuesta
        String token = jwtProvider.generateToken(us.getUsername());
        return new AuthResponse(token);
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        // validar si ya el usuario existe
        if(usuarioRepository.findByUsername(request.getUsername()).isPresent()){
            throw new VerificationException("Usuario ya en uso");
        }
        // validar si el email ya esta en uso
        if(usuarioRepository.findByEmail(request.getEmail()).isPresent()){
            throw new VerificationException("El email ya esta registrado");
        }

        // instanciamos
        Usuario us = new Usuario();
        us.setUsername(request.getUsername());
        // asignar el email
        us.setEmail(request.getEmail());

        // guardar la contraseña hasheada
        us.setPassword(passwordEncoder.encode(request.getPassword()));

        // configuracion por defecto
        us.setRole(Role.ROLE_CUSTOMER);
        // inicia en false hasta que se verifique
        us.setEnabled(false);

        // Generamos un codigo aleatorio para que confirme
        String code = String.format("%06d", new Random().nextInt(999999));
        us.setVerificationCode(code);

        // 15 minutos para validar el codigo
        us.setCodeExpiration(LocalDateTime.now().plusMinutes(15));

        // guardamos en la base de datos (inactivo)
        Usuario savedUser = usuarioRepository.save(us);

        // disparamos el envio de correo
        emailService.sendVerificationEmail(us.getEmail(), code);

        // RETORNAMOS EL NUEVO DTO
        return RegisterResponse.builder()
                .message("Usuario registrado exitosamente. Por favor verifica tu correo.")
                .username(savedUser.getUsername())
                .build();
    }


    @Override
    public void verifyCode(VerifyRequest request) {
        //buscamos el usuario por correo
        Usuario us=usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuario no encontrado"));

        //validamos si ya estaba verificado
        if(us.isEnabled()){
            throw  new VerificationException("La cuenta ya esta verificada");
        }
        //validamos que el codigo no haya expirado
        if(us.getCodeExpiration().isBefore(LocalDateTime.now())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"El codigo ha expirado.Por favor solita uno nuevo");

        }
        //verificamos la base de datos el codigo
        if(!us.getVerificationCode().equals(request.getCode())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Codigo de verificacion incorrecto");
        }
        //activamos la cuenta y limpiamos
        us.setEnabled(true);
        us.setVerificationCode(null);
        us.setCodeExpiration(null);

        //guardamos cambios
        usuarioRepository.save(us);

    }
}
