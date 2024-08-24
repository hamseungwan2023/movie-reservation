package com.movie.reservation.domain.user.dto.response;


import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private final String username;
    private final String phone;
    private final String email;

    @Builder
    public UserResponseDto(String username, String phone, String email) {
        this.username = username;
        this.phone = phone;
        this.email = email;
    }
}
