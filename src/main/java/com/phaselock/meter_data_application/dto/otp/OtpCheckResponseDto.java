package com.phaselock.meter_data_application.dto.otp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtpCheckResponseDto {
    @JsonProperty("attempts")
    private Long attempts;
    @JsonProperty("valid")
    private boolean valid;
}
