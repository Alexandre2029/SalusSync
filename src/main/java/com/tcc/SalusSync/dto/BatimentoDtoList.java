package com.tcc.SalusSync.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record BatimentoDtoList( @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
                                LocalDateTime data,
                                int batimentos) {

}
