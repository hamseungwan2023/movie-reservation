package com.movie.reservation.movie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovieRequest(
        @NotBlank String title,
        @NotBlank String description,
        @NotNull Genre genre,
        String poster,
        @NotNull Integer duration
) {
}
