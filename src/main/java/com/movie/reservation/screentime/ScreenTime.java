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
    private String startTime;
    private String endTime;
    private Long screenId;
    private Long movieId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
