package com.phaselock.meter_data_application.dto.entity.read;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto to create meter reading entity.")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MeterReadingReadDto {

    @Schema(description = "Meter reading", example = "100.0")
    @Positive(message = "Meter reading can't be 0 or less then 0")
    private BigDecimal reading;

    @Schema(description = "Date of meter reading", example = "2020-01-01T23:59:59")
    private LocalDateTime dateTime;
}