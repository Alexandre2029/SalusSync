package com.tcc.SalusSync.dto;

public record MedicoDto(
        String nome,
        String cpf,
        String crm,
        String email,
        String senha,
        String especializacao) {
}
