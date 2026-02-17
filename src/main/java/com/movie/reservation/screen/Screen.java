package com.movie.reservation.screen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Screen {
    private Long id;
    private String name; // 상영관 이름 예) 1관
    private Integer totalSeat; // 전체 좌석 수
    private Long cinemaId; // 소속 영화관 ID
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
