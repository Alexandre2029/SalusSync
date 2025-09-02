package com.tcc.SalusSync.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter

public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cpf;
    private String crm;
    private String email;
    private String senha;
    private String especializacao;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agenda> agenda= new ArrayList<>();

    public Medico(String nome, String cpf, String crm, String email, String senha, String especializacao) {
        this.nome = nome;
        this.cpf = cpf;
        this.crm = crm;
        this.email = email;
        this.senha= senha;
        this.especializacao = especializacao;

    }


    public Medico(){}
}


