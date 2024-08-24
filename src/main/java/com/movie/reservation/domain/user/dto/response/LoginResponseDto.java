package com.movie.reservation.domain.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponseDto {

    private final String username;
    private final String accessToken;
    private final String refreshToken;

    @Builder
    public LoginResponseDto(String username, String accessToken, String refreshToken) {
        this.username = username;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
