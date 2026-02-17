package com.movie.reservation.seat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SeatRequest(
        @NotNull Long screenId,
        @NotBlank String rowName,
        @NotNull Integer seatNumber
) {
}
