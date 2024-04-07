package com.phaselock.meter_data_application.dto.entity.create;

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
@Schema(description = "Dto to create payment history entity.")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentHistoryCreateDto {
    @Schema(description = "Payment amount", example = "102.2")
    @Positive(message = "Payment amount can't be 0 or less then 0")
    private BigDecimal amount;
    @Schema(description = "Date of meter reading", example = "2020-01-01T23:59:59")
    private LocalDateTime date;
    @Schema(description = "Account ID", example = "1")
    @Positive(message = "Account ID can't be 0 or less then 0")
    private Integer accountId;
}
