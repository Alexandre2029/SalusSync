package com.tcc.SalusSync.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Calorias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int calorias;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime hora;

    @ManyToOne
    @JoinColumn(name = "usuario_cpf", referencedColumnName = "cpf")
    private Usuario usuario;

    public Calorias(LocalDateTime data, int calories, Usuario usuario) {
        this.hora = data;
        this.calorias = calories;
        this.usuario = usuario;
    }

    public Calorias(){}

}
