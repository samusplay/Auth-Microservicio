package com.company.auth.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="auth_prueba")
@Data
public class PruebaAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String desccripcion;
}
