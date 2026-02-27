package com.company.auth.repository;

import com.company.auth.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    //metodos personalisados
 //Optinal+identidad+nombre+(que va dar,response)
    Optional<Usuario>findByUserName(String username);
    Optional<Usuario> findByEmail(String email);
}
