package com.movie.reservation.screen;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 상영관 테이블
 * 향후 프론트 연결 편의성위해 row, column 추가 가능성
 */
@Data
public class Screen {
    private Long id;
    private String name; // 상영관 이름
    private Integer totalSeat; // 상영관의 총 좌석 수
    private Long cinemaId; // 영화관 id값
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
