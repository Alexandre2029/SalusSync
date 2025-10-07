package com.tcc.SalusSync.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record AgendaDto(String descricao,
                        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
                        LocalDateTime data,
                        Long usuarioId,
                        String medicoCpf

                        ) {



}
