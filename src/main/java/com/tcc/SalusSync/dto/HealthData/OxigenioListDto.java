package com.tcc.SalusSync.dto.HealthData;

import java.util.List;

public record OxigenioListDto(String cpf,
                              List<OxigenioReturnDto> dados) {
}
