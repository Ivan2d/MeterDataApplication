package com.phaselock.meterdataapplication.dto.entity.read;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.phaselock.meterdataapplication.entity.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto to create meter reading entity.")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MeterTypeReadDto {
    @Schema(description = "Mentor type", example = "GAS")
    private Type type;
}
