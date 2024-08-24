package com.movie.reservation.global.dto;

import lombok.Getter;

@Getter
public class MessageResponse {

    private final int statusCode;
    private final String message;

    public MessageResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
