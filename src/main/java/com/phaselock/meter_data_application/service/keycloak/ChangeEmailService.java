package com.phaselock.meter_data_application.service.keycloak;

import com.phaselock.meter_data_application.client.NotificationClient;
import com.phaselock.meter_data_application.dto.keycloak.change_email.ChangeEmailConfirmRequestDto;
import com.phaselock.meter_data_application.dto.keycloak.change_email.ChangeEmailConfirmResponseDto;
import com.phaselock.meter_data_application.dto.keycloak.change_email.ChangeEmailInitiateResponseDto;
import com.phaselock.meter_data_application.dto.otp.*;
import com.phaselock.meter_data_application.exception.OtpCheckException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.phaselock.meter_data_application.config.KeycloakConfiguration.REALM;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChangeEmailService {
    public static final String CONFIRM_CHANGE_EMAIL_MESSAGE = "Your email changed!";
    public static final String BAD_EMAIL_MESSAGE = "Bad email: ";

    private final Keycloak keycloakAdmin;

    private final NotificationClient notificationClient;

    public ChangeEmailInitiateResponseDto initiate(String email, Jwt principal) {
        log.info(String.format("%s initiate change email to %s", principal.getClaim("email"), email));
        OtpSendRequestDto otpSendRequestDto = createOtpSendRequestDto(email, principal.getClaim("user_id"));
        OtpSendResponseDto otpSendResponseDto = notificationClient.sendOtpMessage(otpSendRequestDto);
        return new ChangeEmailInitiateResponseDto(UUID.fromString(otpSendResponseDto.getUuid()));
    }

    public ChangeEmailConfirmResponseDto confirm(ChangeEmailConfirmRequestDto request, Jwt principal) {
        return new ChangeEmailConfirmResponseDto(executeChangeEmail(request, principal.getClaim("user_id")));
    }

    private String executeChangeEmail(ChangeEmailConfirmRequestDto request, String userUid) {
        OtpCheckRequestDto otpCheckRequestDto = new OtpCheckRequestDto(
                request.otpUid().toString(),
                request.otpCode(),
                request.email()
        );
        OtpCheckResponseDto responseDto = notificationClient.checkOtpMessage(otpCheckRequestDto);
        checkOtpResult(responseDto);
        updateEmailInKeycloak(userUid, request.email());
        return request.email();
    }

    private void checkOtpResult(OtpCheckResponseDto responseDto) throws OtpCheckException {
        if (!responseDto.isValid()) {
            log.error("Otp code incorrect");
            throw new OtpCheckException("Otp code incorrect");
        }
    }

    private OtpSendRequestDto createOtpSendRequestDto(String email, String userUid) {
        OtpDetailsRequestDto otpDetails = new OtpDetailsRequestDto(
                4,
                300,
                3,
                60,
                true
        );

        return new OtpSendRequestDto(
                email,
                UUID.randomUUID().toString(),
                "Verification code: %s",
                CONFIRM_CHANGE_EMAIL_MESSAGE,
                userUid,
                email,
                otpDetails
        );
    }

    private void updateEmailInKeycloak(String userUid, String email) {
        RealmResource realmResource = keycloakAdmin.realm(REALM);
        UsersResource usersResource = realmResource.users();
        UserResource userResource = usersResource.get(userUid);
        UserRepresentation user = userResource.toRepresentation();
        user.setEmail(email);
        userResource.update(user);
    }
}