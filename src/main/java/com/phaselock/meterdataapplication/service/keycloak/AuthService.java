package com.phaselock.meterdataapplication.service.keycloak;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.phaselock.meterdataapplication.dto.keycloak.UserAuthDto;
import com.phaselock.meterdataapplication.dto.keycloak.UserRegisterDto;
import com.phaselock.meterdataapplication.dto.keycloak.AccessTokenDto;
import com.phaselock.meterdataapplication.dto.keycloak.LogoutRequestDto;
import com.phaselock.meterdataapplication.dto.keycloak.LogoutResponseDto;
import com.phaselock.meterdataapplication.mapper.keycloak.AccessTokenMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleMappingResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import static com.phaselock.meterdataapplication.config.keycloak.KeycloakConfiguration.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final Keycloak keycloak;

    public void addUser(UserRegisterDto dto) {
        CredentialRepresentation credential = createPasswordCredentials(dto.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);
        UsersResource usersResource = keycloak.realm(REALM).users();
        try (var ignored = usersResource.create(user)) {
            if (ignored.getStatus() != 201) {
                String responseBody = ignored.readEntity(String.class);
                System.out.println("User creation response body: " + responseBody);
                throw new RuntimeException("Failed to create user");
            }
            RealmResource realmResource = keycloak.realm(REALM);
            List<UserRepresentation> users = realmResource.users().search(dto.getUsername());
            UserResource userResource = realmResource.users().get(users.getFirst().getId());
            RoleRepresentation role = realmResource.roles().get(dto.getUserRoleDto().name().toLowerCase()).toRepresentation();
            RoleMappingResource roleMappingResource = userResource.roles();
            roleMappingResource.realmLevel().add(Collections.singletonList(role));
        }
    }

    public AccessTokenDto loginUser(UserAuthDto authDto) {
        try (
                Keycloak keycloak = KeycloakBuilder.builder()
                        .serverUrl(AUTH_SERVER_URL)
                        .realm(REALM)
                        .clientId(CLIENT_ID)
                        .clientSecret(CLIENT_SECRET)
                        .username(authDto.getUsername())
                        .password(authDto.getPassword())
                        .grantType(OAuth2Constants.PASSWORD)
                        .build()
        ) {
            AccessTokenResponse accessToken = keycloak.tokenManager().getAccessToken();
            return AccessTokenMapper.mapToAccessTokenDto(accessToken);
        }
    }

    public LogoutResponseDto logoutUser(LogoutRequestDto logoutRequestDto) {
        try {
            try (var httpClient = HttpClients.createDefault()) {
                String logoutUrl = AUTH_SERVER_URL + "/realms/" + REALM + "/protocol/openid-connect/logout";
                HttpPost httpPost = new HttpPost(logoutUrl);
                httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair(OAuth2Constants.REFRESH_TOKEN, logoutRequestDto.getRefreshToken()));
                params.add(new BasicNameValuePair(OAuth2Constants.CLIENT_ID, CLIENT_ID));
                params.add(new BasicNameValuePair(OAuth2Constants.CLIENT_SECRET, CLIENT_SECRET));
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                return new LogoutResponseDto(
                        httpClient
                                .execute(httpPost)
                                .getStatusLine()
                                .getStatusCode()
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

}