package com.tcc.SalusSync.dto;

import java.util.List;

public record ContatoListDto(
        String cpf,
        List<ContatoReturnDto> dados
) {
}
