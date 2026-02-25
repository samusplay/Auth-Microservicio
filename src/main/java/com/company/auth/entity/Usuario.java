package com.company.auth.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    //estado cuenta
    @Column(nullable = false)
    private boolean enabled = false;

    //codigo de verificacion
    @Column(name = "verification_code")
    private String verificationCode;

    //hora y expiracion del codigo
    @Column(name = "code_expiration")
    private LocalDateTime codeExpiration;
}
