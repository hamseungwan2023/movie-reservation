package com.movie.reservation.reservation;

import java.time.LocalDateTime;

public record Reservation(
        Long id,
        ReservationStatus reservationStatus,
        Long screenTimeId,
        Integer seatNumber,
        String username,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
}
