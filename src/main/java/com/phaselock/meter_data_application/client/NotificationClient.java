package com.phaselock.meter_data_application.client;

import com.phaselock.meter_data_application.dto.otp.OtpCheckRequestDto;
import com.phaselock.meter_data_application.dto.otp.OtpCheckResponseDto;
import com.phaselock.meter_data_application.dto.otp.OtpSendRequestDto;
import com.phaselock.meter_data_application.dto.otp.OtpSendResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class NotificationClient {

    private final RestTemplate restTemplate;

    @Value("${notification.service.url}")
    private String notificationServiceUrl;

    public OtpSendResponseDto sendOtpMessage(OtpSendRequestDto otpSendRequestDto) {
        ResponseEntity<OtpSendResponseDto> responseEntity = restTemplate.postForEntity(
                notificationServiceUrl + "/otp/send",
                otpSendRequestDto,
                OtpSendResponseDto.class);
        return responseEntity.getBody();
    }

    public OtpCheckResponseDto checkOtpMessage(OtpCheckRequestDto otpCheckRequestDto) {
        ResponseEntity<OtpCheckResponseDto> responseEntity = restTemplate.postForEntity(
                notificationServiceUrl + "/otp/check",
                otpCheckRequestDto,
                OtpCheckResponseDto.class);
        return responseEntity.getBody();
    }
}
