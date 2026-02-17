package com.movie.reservation.screentime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreenTime {
    private Long id;
    private String startTime; // 상영 시작 시각
    private String endTime; // 상영 종료 시각
    private Long screenId; // 상영관 ID
    private Long movieId; // 영화 ID
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
