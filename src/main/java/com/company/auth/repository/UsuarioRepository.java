package com.company.auth.repository;

import com.company.auth.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findBynombre(String nombre);

    Optional<Usuario> findByEmail(String email);
}