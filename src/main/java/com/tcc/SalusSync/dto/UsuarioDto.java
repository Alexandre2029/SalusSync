package com.tcc.SalusSync.dto;

import java.util.Date;

public record UsuarioDto(
        String nome,
        String cpf,
        String email,
        String senha,
        double altura,
        double peso,
        String sexo,
        Date dataNascimento
) {
}
