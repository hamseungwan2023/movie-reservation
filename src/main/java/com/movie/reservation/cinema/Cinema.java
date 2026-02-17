package com.movie.reservation.cinema;

import java.time.LocalDateTime;

public record Cinema(
        Long id,
        String name,
        String address,
        String sido,
        String gungu,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
}
