package com.tcc.SalusSync.dto;

public record MedicoDto(
        String nome,
        String cpf,
        String crm,
        String login,
        String password,
        String especializacao) {
}
