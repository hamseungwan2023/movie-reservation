package com.movie.reservation.cinema;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 영화관 테이블
 */
@Data
public class Cinema {
    private Long id;
    private String name; // 영화관 이름 예) CGV 강남
    private String address; // 영화관 주소
    private String sido; // 영화관 주소의 시 or 도
    private String gungu; // 영화관 주소의 군 or 구
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
