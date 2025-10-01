package com.tcc.SalusSync.dto;

import com.tcc.SalusSync.model.UserRole;

public record RegisterMedicoDTO(

        String login,
        String password,
        UserRole role,
        String nome,
        String cpf,
        String crm,
        String especializacao
) {
}
