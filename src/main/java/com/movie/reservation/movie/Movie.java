package com.movie.reservation.movie;

import java.time.LocalDateTime;

public record Movie(
        Long id,
        String title,
        String description,
        Genre genre,
        String poster,
        Integer duration,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
}
