package com.company.auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
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

        private Rol rol;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Rol getRol() {
        return rol;
    }


}
