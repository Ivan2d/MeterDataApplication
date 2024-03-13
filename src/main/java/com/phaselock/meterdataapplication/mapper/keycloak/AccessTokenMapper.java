package com.phaselock.meterdataapplication.mapper.keycloak;

import com.phaselock.meterdataapplication.dto.keycloak.AccessTokenDto;
import jakarta.validation.constraints.NotNull;
import org.keycloak.representations.AccessTokenResponse;

public class AccessTokenMapper {
    @NotNull
    public static AccessTokenDto mapToAccessTokenDto(AccessTokenResponse accessToken) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setIdToken(accessToken.getIdToken());
        accessTokenDto.setExpiresIn(accessToken.getExpiresIn());
        accessTokenDto.setNotBeforePolicy(accessToken.getNotBeforePolicy());
        accessTokenDto.setOtherClaims(accessToken.getOtherClaims());
        accessTokenDto.setRefreshExpiresIn(accessToken.getRefreshExpiresIn());
        accessTokenDto.setRefreshToken(accessToken.getRefreshToken());
        accessTokenDto.setScope(accessToken.getScope());
        accessTokenDto.setSessionState(accessToken.getSessionState());
        accessTokenDto.setToken(accessToken.getToken());
        accessTokenDto.setTokenType(accessToken.getTokenType());
        return accessTokenDto;
    }
}