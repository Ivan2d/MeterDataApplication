package com.phaselock.meterdataapplication.dto.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.phaselock.meterdataapplication.entity.MeterType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto to create meter entity.")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MeterCreateDto {
    @Schema(description = "Meter type", example = "GAS")
    @NotBlank(message = "House number can't be 0 or less then 0")
    private MeterType type;

    @Schema(description = "Meter readings", example = "5.0")
    @PositiveOrZero(message = "House number can't less then 0")
    private BigDecimal reading;

    @Schema(description = "Date of installation", example = "2023-05-20")
    private Date installationDate;

    @Schema(description = "Apartment's id", example = "1")
    @Positive(message = "Apartment id can't be 0 or less then 0")
    private Integer apartmentId;
}
