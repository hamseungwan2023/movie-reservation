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
    private ReservationStatus reservationStatus; // 예약 상태(CONFIRMED/CANCELLED)
    private Long screenTimeId; // 상영 시간 ID
    private Integer seatNumber; // 예약 좌석 번호
    private String username; // 예약자 이름
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
