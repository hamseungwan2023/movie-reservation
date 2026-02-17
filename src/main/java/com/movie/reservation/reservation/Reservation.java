package com.movie.reservation.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private Long id;
    private ReservationStatus reservationStatus;
    private Long screenTimeId;
    private Integer seatNumber;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
