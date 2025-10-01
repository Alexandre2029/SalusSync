package com.tcc.SalusSync.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Getter @Setter
public class Medico implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cpf;
    private String crm;
    private String login;
    private String password;
    private String especializacao;
    private UserRole role;
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agenda> agenda= new ArrayList<>();

    public Medico(String nome, String login, String encryptedPassword, UserRole role, String cpf, String crm, String especializacao) {
        this.nome = nome;
        this.cpf = cpf;
        this.crm = crm;
        this.login = login;
        this.password = encryptedPassword;
        this.especializacao = especializacao;
        this.role= role;


    }


    public Medico(){}



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_MEDICO"));
    }

    @Override
    public String getUsername() {
        return login;
    }


    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}


