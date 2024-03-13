package com.phaselock.meterdataapplication.config.keycloak;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static org.keycloak.OAuth2Constants.*;

@Component
public class KeycloakConfiguration {
    public static String AUTH_SERVER_URL;
    public static String REALM;
    public static String CLIENT_ID;
    public static String CLIENT_SECRET;
    public static String ADMIN_REALM;
    public static String ADMIN_CLIENT_ID;
    public static String ADMIN_USERNAME;
    public static String ADMIN_PASSWORD;
    public static String USERNAME_CLAIM;
    public static String ROLES;
    public static String CLAIM_REALM_ACCESS;
    public static String RESOURCE_ACCESS;

    @Value("${keycloak.server-url}")
    public void setAuthServerUrl(String authServerUrl) {
        AUTH_SERVER_URL = authServerUrl;
    }

    @Value("${keycloak.realm}")
    public void setRealm(String realm) {
        REALM = realm;
    }

    @Value("${keycloak.client-id}")
    public void setClientId(String clientId) {
        CLIENT_ID = clientId;
    }

    @Value("${keycloak.client-secret}")
    public void setClientSecret(String clientSecret) {
        CLIENT_SECRET = clientSecret;
    }

    @Value("${keycloak.admin-realm}")
    public void setAdminRealm(String adminRealm) {
        ADMIN_REALM = adminRealm;
    }

    @Value("${keycloak.admin-client-id}")
    public void setAdminClientId(String clientId) {
        ADMIN_CLIENT_ID = clientId;
    }

    @Value("${keycloak.admin-username}")
    public void setAdminUsername(String adminUsername) {
        ADMIN_USERNAME = adminUsername;
    }

    @Value("${keycloak.admin-password}")
    public void setAdminPassword(String adminPassword) {
        ADMIN_PASSWORD = adminPassword;
    }

    @Value("${keycloak.username-claim}")
    public void setUsernameClaim(String usernameClaim) {
        USERNAME_CLAIM = usernameClaim;
    }

    @Value("${keycloak.roles}")
    public void setRoles(String roles) {
        ROLES = roles;
    }

    @Value("${keycloak.claim-realm-access}")
    public void setClaimRealmAccess(String claimRealmAccess) {
        CLAIM_REALM_ACCESS = claimRealmAccess;
    }

    @Value("${keycloak.resource-access}")
    public void setResourceAccess(String resourceAccess) {
        RESOURCE_ACCESS = resourceAccess;
    }

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(AUTH_SERVER_URL)
                .realm(REALM)
                .grantType(CLIENT_CREDENTIALS)
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .username(ADMIN_USERNAME)
                .password(ADMIN_PASSWORD)
                .build();
    }
}