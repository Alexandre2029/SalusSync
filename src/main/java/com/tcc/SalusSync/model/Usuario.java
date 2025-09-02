package com.tcc.SalusSync.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private double altura;
    private double peso;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agenda> agenda= new ArrayList<>();


    public Usuario(String nome, String cpf, String email, String senha, double altura, double peso) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha= senha;
        this.altura = altura;
        this.peso = peso;

    }

    public Usuario(){}


}
