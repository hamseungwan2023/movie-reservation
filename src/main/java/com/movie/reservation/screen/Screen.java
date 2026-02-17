package com.movie.reservation.screen;

import java.time.LocalDateTime;

public record Screen(
        Long id,
        String name,
        Integer totalSeat,
        Long cinemaId,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
}
