package com.tcc.SalusSync.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Calories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int calories;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "usuario_cpf")
    private Usuario usuario;

    public Calories(LocalDateTime data, int calories, Usuario usuario) {
        this.date = data;
        this.calories = calories;
        this.usuario = usuario;
    }

    public Calories(){}

}
