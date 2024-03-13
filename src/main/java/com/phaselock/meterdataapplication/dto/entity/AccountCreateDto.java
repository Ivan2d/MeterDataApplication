package com.phaselock.meterdataapplication.dto.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto to create house entity.")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountCreateDto {
    @Schema(description = "Number of account", example = "1")
    @Positive(message = "Account number can't be 0 or less then 0")
    private Integer accountNumber;

    @Schema(description = "Account balance", example = "100.0")
    @Positive(message = "Account balance can't be 0 or less then 0")
    private BigDecimal balance;

    @Schema(description = "Apartment's id", example = "1")
    @Positive(message = "Apartment id can't be 0 or less then 0")
    private Integer apartmentId;
}
