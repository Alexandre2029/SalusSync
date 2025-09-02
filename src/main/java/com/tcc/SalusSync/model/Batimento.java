package com.tcc.SalusSync.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Batimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime hora;

    private int batimentosMinutos;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    public Batimento(LocalDateTime data, int batimentos, Usuario usuario) {
        this.hora = data;
        this.batimentosMinutos = batimentos;
        this.usuario = usuario;
    }

    public Batimento(){}
}
