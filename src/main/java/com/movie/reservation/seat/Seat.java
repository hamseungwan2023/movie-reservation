package com.movie.reservation.seat;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 좌석 테이블
 * 향후 rowName + seatNumber 컬럼 추가 가능성
 */
@Data
public class Seat {
    private Long id;
    private Long screenId; // 상영관 id
    private String rowName; // 열 이름 ex) A열 11번 좌석
    private Integer seatNumber; // 해당열의 몇번 좌석 인지
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
