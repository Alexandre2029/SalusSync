package com.tcc.SalusSync.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

    @Entity
    @Getter
    @Setter
    public class Pressao {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private int pressaoSistolica;
        private  int pressaoDiastolica;
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        private LocalDateTime hora;

        @ManyToOne
        @JoinColumn(name = "usuario_cpf", referencedColumnName = "cpf")
        private Usuario usuario;

        public Pressao(LocalDateTime data, int pressaoSistolica, int pressaoDiastolica, Usuario usuario) {
            this.hora = data;
            this.pressaoSistolica = pressaoSistolica;
            this.pressaoDiastolica = pressaoDiastolica;
            this.usuario = usuario;
        }

        public Pressao() {
        }
    }
