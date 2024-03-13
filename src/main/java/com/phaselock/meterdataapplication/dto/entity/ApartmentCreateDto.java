package com.phaselock.meterdataapplication.dto.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto to create apartment entity.")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ApartmentCreateDto {
    @Schema(description = "Number of apartment", example = "1")
    @Positive(message = "Apartment number can't be 0 or less then 0")
    private Integer apartmentNumber;

    @Schema(description = "Number of apartment", example = "1")
    @Positive(message = "Apartment area can't be 0 or less then 0")
    private BigDecimal area;

    @Schema(description = "Address of apartment", example = "Some st., some house, some apartment")
    @NotBlank(message = "Apartment address may not be empty")
    private String address;
}
