package com.tcc.SalusSync.dto;

public record UsuarioDto(
        String nome,
        String cpf,
        String email,
        String senha,
        double altura,
        double peso,
        String contato            ) {
}
