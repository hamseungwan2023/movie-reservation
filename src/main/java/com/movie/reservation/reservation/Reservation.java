package com.movie.reservation.reservation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reservation {
    private Long id;
    private ReservationStatus reservationStatus;
    private Long screenTimeId;
    private Integer seatNumber;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
