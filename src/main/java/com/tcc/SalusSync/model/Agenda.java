package com.tcc.SalusSync.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime data;
    private String situacao;

   @ManyToOne
   @JoinColumn(name = "usuario_id")
   @JsonBackReference
    private Usuario usuario;
   @ManyToOne
   @JoinColumn(name = "medico_cpf")
    private Medico medico;


    public Agenda(String descricao, LocalDateTime data, Usuario usuario, Medico medico) {

        this.descricao = descricao;
        this.data = data;
        this.usuario= usuario;
        this.medico = medico;
        this.situacao = "PENDENTE DE CONFIRMAÇÃO";
    }

    public Agenda(){}
}
