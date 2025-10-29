package com.tcc.SalusSync.dto;

import com.tcc.SalusSync.model.UserRole;

import java.util.Date;

public record RegisterDTO(String login,
                          String password,
                          UserRole role,
                          String nome,
                          String cpf,
                          double altura,
                          double peso,
                          String sexo,
                          Date dataNascimento
) {
}
