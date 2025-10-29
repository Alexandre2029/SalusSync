package com.tcc.SalusSync.dto.HealthData;

import java.util.List;

public record CaloriasListDto(
        String cpf,
        List<CaloriasReturnDto> dados

) {
}
