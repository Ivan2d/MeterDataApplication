package com.phaselock.meter_data_application.service.keycloak;

import com.phaselock.meter_data_application.client.NotificationClient;
import com.phaselock.meter_data_application.dto.keycloak.change_password.ChangePasswordDto;
import com.phaselock.meter_data_application.dto.keycloak.change_password.ConfirmChangePasswordDto;
import com.phaselock.meter_data_application.dto.otp.*;
import com.phaselock.meter_data_application.exception.IncorrectPasswordException;
import com.phaselock.meter_data_application.exception.OtpCheckException;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotAuthorizedException;
import java.util.Collections;
import java.util.UUID;

import static com.phaselock.meter_data_application.config.KeycloakConfiguration.*;

@Service
@RequiredArgsConstructor
public class ChangePasswordService {
    private static final String MESSAGE = "Changing the password from authorized user";

    private final Keycloak keycloakAdmin;
    private final NotificationClient notificationClient;

    public OtpSendResponseDto initiateChangePassword(ChangePasswordDto changePasswordDto, Jwt principal) {
        validateNewPassword(changePasswordDto.getNewPassword(), changePasswordDto.getConfirmNewPassword());
        checkCurrentPasswordWithInput(changePasswordDto.getCurrentPassword(), principal);
        OtpSendRequestDto requestDto = createOtpSendRequestDto(principal.getClaim("email"), principal.getClaim("user_id"));
        return notificationClient.sendOtpMessage(requestDto);
    }

    public void confirmChangePassword(ConfirmChangePasswordDto request, Jwt principal) {
        OtpCheckRequestDto otpCheckRequestDto = new OtpCheckRequestDto(
                request.getOtpUid().toString(),
                request.getOtpCode(),
                request.getDestination()
        );

        validateNewPassword(request.getNewPassword(), request.getConfirmNewPassword());
        OtpCheckResponseDto responseDto = notificationClient.checkOtpMessage(otpCheckRequestDto);
        if (!responseDto.isValid()) {
            throw new OtpCheckException("Otp code incorrect");
        }
        changeKeycloakUserPassword(principal, request.getNewPassword());
    }

    private void checkCurrentPasswordWithInput(String currentPassword, Jwt principal) {
        try (Keycloak checkClientPassword = Keycloak.getInstance(
                AUTH_SERVER_URL, REALM, principal.getClaim("preferred_username"), currentPassword, CLIENT_ID, CLIENT_SECRET
        )) {
            checkClientPassword.tokenManager().getAccessToken();
        } catch (NotAuthorizedException e) {
            throw new IncorrectPasswordException("You entered wrong current password");
        }
    }

    private void validateNewPassword(String newPassword, String confirmNewPassword) {
        if (!newPassword.equals(confirmNewPassword)) {
            throw new IncorrectPasswordException("newPassword does not match confirmNewPassword");
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
                MESSAGE,
                userUid,
                email,
                otpDetails
        );
    }

    private void changeKeycloakUserPassword(Jwt principal, String newPassword) {
        RealmResource realmResource = keycloakAdmin.realm(REALM);
        UsersResource usersResource = realmResource.users();
        UserResource userResource = usersResource.get(principal.getClaim("user_id"));
        UserRepresentation representation = userResource.toRepresentation();
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(newPassword);
        credential.setTemporary(false);
        representation.setCredentials(Collections.singletonList(credential));
        userResource.update(representation);
    }
}
