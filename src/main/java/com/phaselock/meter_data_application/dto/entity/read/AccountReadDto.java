package com.phaselock.meter_data_application.dto.entity.read;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto to read house entity.")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountReadDto {
    @Schema(description = "Number of account", example = "1")
    @Positive(message = "Account number can't be 0 or less then 0")
    private Integer accountNumber;
}