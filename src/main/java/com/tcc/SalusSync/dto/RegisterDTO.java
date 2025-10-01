package com.tcc.SalusSync.dto;

import com.tcc.SalusSync.model.UserRole;

public record RegisterDTO(String login,
                          String password,
                          UserRole role,
                          String nome,
                          String cpf,
                          double altura,
                          double peso,
                          String contato
) {
}
