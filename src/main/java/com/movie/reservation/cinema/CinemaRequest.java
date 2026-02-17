package com.movie.reservation.cinema;

import jakarta.validation.constraints.NotBlank;

public record CinemaRequest(
        @NotBlank String name,
        @NotBlank String address,
        @NotBlank String sido,
        @NotBlank String gungu
) {
}
