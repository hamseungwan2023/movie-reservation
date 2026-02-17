package com.movie.reservation.screentime;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScreenTime {
    private Long id;
    private String startTime;
    private String endTime;
    private Long screenId;
    private Long movieId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
