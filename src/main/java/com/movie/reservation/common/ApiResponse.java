package com.movie.reservation.common;

public record ApiResponse<T>(int statusCode, String message, T data) {
    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse<>(200, message, data);
    }

    public static ApiResponse<Void> ok(String message) {
        return new ApiResponse<>(200, message, null);
    }
}
