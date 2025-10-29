package com.tcc.SalusSync.dto.HealthData;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record BtmDataDto(
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,
        int batimento,
        String cpf
) {
}
