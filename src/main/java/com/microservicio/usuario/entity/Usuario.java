package com.microservicio.usuario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
    @Data
    @Builder
    @Table(name="users")
    @NoArgsConstructor
    @AllArgsConstructor
    public class Usuario {
        @Id
        @GeneratedValue
        @Column(name="user_id")
        private long Id;

        @Column(nullable = false)
        private String nombre;

        @Column(nullable = false,unique = true)
        private String email;

        @Column (nullable = false)
        private String password;

        @Column(length = 20)
        private String telefono;

        @Column (name = "Creado", updatable = false)
        @org.hibernate.annotations.CreationTimestamp
        private LocalDateTime createdAt;


}
