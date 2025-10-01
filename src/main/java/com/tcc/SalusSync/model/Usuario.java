package com.tcc.SalusSync.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Entity
@Getter @Setter
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cpf;
    private String login;
    private String password;
    private double altura;
    private double peso;
    private String contadoEmergencia;
    private UserRole role;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Agenda> agenda= new ArrayList<>();


    public Usuario(String nome, String cpf, String email, String senha, double altura, double peso,String contadoEmergencia) {
        this.nome = nome;
        this.cpf = cpf;
        this.login = email;
        this.password = senha;
        this.altura = altura;
        this.peso = peso;
        this.contadoEmergencia = contadoEmergencia;
    }

    public Usuario(){}

    public Usuario(String login, String encryptedPassword, UserRole role, String cpf, String contato, String nome, double altura, double peso) {
        this.nome = nome;
        this.cpf = cpf;
        this.login = login;
        this.password = encryptedPassword;
        this.altura = altura;
        this.peso = peso;
        this.contadoEmergencia = contato;
        this.role = role;

    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_MEDICO"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        } else if (this.role == UserRole.MEDICO) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_MEDICO"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }


    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
