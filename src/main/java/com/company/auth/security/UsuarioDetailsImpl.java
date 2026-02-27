package com.company.auth.security;

import com.company.auth.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

public class UsuarioDetailsImpl implements UserDetails{
    private final Usuario usuario;

    public UsuarioDetailsImpl(Usuario usuario) {this.usuario = usuario;}

    @Override
    public String getUsername() {return usuario.getEmail();}

    @Override
    public String getPassword() {return usuario.getPassword();}

    @Override
    public Collection<GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_"+usuario.getRol().name()));
    }
}
