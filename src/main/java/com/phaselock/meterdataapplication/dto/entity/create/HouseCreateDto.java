package com.phaselock.meterdataapplication.dto.entity.create;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto to create house entity.")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HouseCreateDto {
    @Schema(description = "Number of house", example = "1")
    @Positive(message = "House number can't be 0 or less then 0")
    private Integer houseNumber;

    @Schema(description = "Address of house", example = "Some st., some house")
    @NotBlank(message = "House address may not be empty")
    private String address;
}
