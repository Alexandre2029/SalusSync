package com.tcc.SalusSync.dto.HealthData;

import java.util.List;

public record BtmDataListDto(String cpf,
                             List<BtmReturnDto> dados)
{ }
