package com.movie.reservation.global.dto;

import lombok.Getter;

@Getter
public class DataResponse<T> {

    private final int statusCode;
    private final String message;
    private final T data;

    public DataResponse(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}