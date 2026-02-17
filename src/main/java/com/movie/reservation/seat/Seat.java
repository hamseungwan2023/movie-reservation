package com.movie.reservation.seat;

import java.time.LocalDateTime;

public record Seat(
        Long id,
        Long screenId,
        String rowName,
        Integer seatNumber,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
}
