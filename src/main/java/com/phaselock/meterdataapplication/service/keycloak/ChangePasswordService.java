package com.phaselock.meterdataapplication.service.keycloak;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static com.phaselock.meterdataapplication.config.keycloak.KeycloakConfiguration.REALM;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChangePasswordService {
    private final Keycloak keycloak;

    public void changeKeycloakUserPassword(JwtAuthenticationToken principal, String newPassword) {
        RealmResource realmResource = keycloak.realm(REALM);
        UsersResource usersResource = realmResource.users();
        UserResource userResource = usersResource.get(principal.getToken().getSubject());
        UserRepresentation representation = userResource.toRepresentation();

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(newPassword);
        credential.setTemporary(false);
        representation.setCredentials(Collections.singletonList(credential));
        userResource.update(representation);
    }

}
