package com.phaselock.meter_data_application.dto.entity.read;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto to create meter entity.")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MeterReadDto {
    @Schema(description = "Date of installation", example = "2023-05-20")
    private LocalDate installationDate;

    @Schema(description = "End date", example = "2023-05-20")
    private LocalDate endDate;
}
