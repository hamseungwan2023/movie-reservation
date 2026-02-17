package com.movie.reservation.screentime;

import java.time.LocalDateTime;

public record ScreenTime(
        Long id,
        String startTime,
        String endTime,
        Long screenId,
        Long movieId,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
}
