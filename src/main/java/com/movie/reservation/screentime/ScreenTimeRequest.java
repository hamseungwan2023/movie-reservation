package com.movie.reservation.screentime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ScreenTimeRequest(
        @NotBlank String startTime,
        @NotBlank String endTime,
        @NotNull Long screenId,
        @NotNull Long movieId
) {
}
