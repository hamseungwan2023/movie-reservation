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
    private String name;
    private Integer totalSeat;
    private Long cinemaId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
