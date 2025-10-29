package com.tcc.SalusSync.dto.HealthData;

import java.util.List;

public record PressaoListDto(String cpf,
                             List<PressaoReturnDto> dados) {
}
