package com.movie.reservation.screentime;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 영화 상영시간
 * insert시 영화의 런타임으로 startTime 지정 시 endTime 계산 함
 * 추후 조인 없이 조회 위해 영화 이름 들어갈 수 있음
 */
@Data
public class ScreenTime {
    private Long id;
    private String startTime; // 상영 시작 시간
    private String endTime; // 상영이 끝나는 시간
    private Long screenId; // 상영관 id
    private Long movieId; // 영화 id
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
