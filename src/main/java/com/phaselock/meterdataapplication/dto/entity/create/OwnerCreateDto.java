package com.phaselock.meterdataapplication.dto.entity.create;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto to create owner entity.")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OwnerCreateDto {
    @Schema(description = "Owner's last name", example = "Stanislav")
    @NotBlank(message = "Owner name may not be empty")
    @Size(max = 32)
    @Pattern(regexp = "^[A-Za-z]*(([,.] |[ '-])[A-Za-z][a-z]*)*(\\.?)$")
    private String firstName;

    @Schema(description = "Merchant member's first name", example = "Petrov", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Merchant member name may not be empty")
    @Size(max = 32)
    @Pattern(regexp = "^[A-Za-z]*(([,.] |[ '-])[A-Za-z][a-z]*)*(\\.?)$")
    private String lastName;

    @Schema(description = "Owner's phone", example = "15002252525")
    @Pattern(regexp = "^[0-9\\-+]{7,15}$")
    private String phone;
}
