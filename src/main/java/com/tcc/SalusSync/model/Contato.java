package com.tcc.SalusSync.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String contatoEmergencia;

    @ManyToOne
    @JoinColumn(name = "usuario_cpf", referencedColumnName = "cpf")
    private Usuario usuario;


    public Contato( String contato, Usuario usuario) {
        this.contatoEmergencia = contato;
        this.usuario = usuario;
    }

    public Contato(){}
}
