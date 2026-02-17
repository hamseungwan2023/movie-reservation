package com.movie.reservation.reservation;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 영화 예매 테이블
 */
@Data
public class Reservation {
    private Long id;
    private ReservationStatus reservationStatus; // 예약 상태 (confirm, cancel)
    private Long screenTimeId; // 상영시간 id값
    private Integer seatNumber; // 좌석 번호, (향후 좌석의 id값 으로 변경 가능성 있음)
    private String username; // 예약자 이름
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
