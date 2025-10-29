package com.tcc.SalusSync.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Oxigenio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int oxigenio;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime hora;
    @ManyToOne
    @JoinColumn(name = "usuario_cpf", referencedColumnName = "cpf")
    private Usuario usuario;


    public Oxigenio(LocalDateTime data, int oxigenioSaturacao, Usuario usuario) {
        this.hora = data;
        this.oxigenio=oxigenioSaturacao;
        this.usuario = usuario;
    }

    public Oxigenio(){}
}
