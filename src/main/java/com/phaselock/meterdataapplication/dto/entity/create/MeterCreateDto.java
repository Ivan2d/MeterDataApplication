package com.phaselock.meterdataapplication.dto.entity.create;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.phaselock.meterdataapplication.entity.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto to create meter entity.")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MeterCreateDto {
    @Schema(description = "Date of installation", example = "2023-05-20")
    private LocalDate installationDate;

    @Schema(description = "End date", example = "2024-05-20")
    private LocalDate endDate;

    @Schema(description = "Apartment's id", example = "1")
    @Positive(message = "Apartment id can't be 0 or less then 0")
    private Integer apartmentId;

    @Schema(description = "Meter type ID", example = "1")
    @NotBlank(message = "Meter type ID can't be 0 or less then 0")
    private Integer typeId;
}
