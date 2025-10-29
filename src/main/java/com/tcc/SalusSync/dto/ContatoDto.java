package com.tcc.SalusSync.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ContatoDto(
         String contato,
         String cpf
) {
}
