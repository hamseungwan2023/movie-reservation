package com.movie.reservation.screen;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Screen {
    private Long id;
    private String name;
    private Integer totalSeat;
    private Long cinemaId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
