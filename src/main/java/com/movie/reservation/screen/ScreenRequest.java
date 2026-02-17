package com.movie.reservation.screen;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ScreenRequest(
        @NotBlank String name,
        @NotNull Integer totalSeat,
        @NotNull Long cinemaId
) {
}
