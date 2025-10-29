package com.tcc.SalusSync.dto.HealthData;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record CaloriasReturnDto(
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,
        int calorias

) {
}
