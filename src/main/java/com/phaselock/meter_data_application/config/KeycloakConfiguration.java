package com.phaselock.meter_data_application.config;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

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
    public static String ROLES;

    @Value("${keycloak.auth-server-url}")
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

    @Value("${keycloak.admin-client-id}")
    public void setAdminClientId(String adminCli) {
        ADMIN_CLIENT_ID = adminCli;
    }

    @Value("${keycloak.admin-realm}")
    public void setAdminRealm(String adminRealm) {
        ADMIN_REALM = adminRealm;
    }

    @Value("${keycloak.admin-username}")
    public void setAdminUsername(String adminUsername) {
        ADMIN_USERNAME = adminUsername;
    }

    @Value("${keycloak.admin-password}")
    public void setAdminPassword(String adminPassword) {
        ADMIN_PASSWORD = adminPassword;
    }

    @Value("${keycloak.roles}")
    public void setRoles(String roles) {
        ROLES = roles;
    }
    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(AUTH_SERVER_URL)
                .realm(ADMIN_REALM)
                .clientId(ADMIN_CLIENT_ID)
                .username(ADMIN_USERNAME)
                .password(ADMIN_PASSWORD)
                .build();
    }
}