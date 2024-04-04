package com.phaselock.meterdataapplication.service.keycloak;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.phaselock.meterdataapplication.config.keycloak.KeycloakConfiguration.REALM;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChangeEmailService {
    private final Keycloak keycloak;

    public void updateEmailInKeycloak(String email, JwtAuthenticationToken jwt) {
        RealmResource realmResource = keycloak.realm(REALM);
        UsersResource usersResource = realmResource.users();
        UserResource userResource = usersResource.get(jwt.getToken().getSubject());
        UserRepresentation user = userResource.toRepresentation();
        user.setEmail(email);
        userResource.update(user);
    }

}
