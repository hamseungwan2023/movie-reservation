package com.movie.reservation.reservation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReservationRequest(
        @NotNull Long screenTimeId,
        @NotNull Integer seatNumber,
        @NotBlank String username
) {
}
