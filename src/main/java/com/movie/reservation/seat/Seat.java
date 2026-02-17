package com.movie.reservation.seat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    private Long id;
    private Long screenId;
    private String rowName;
    private Integer seatNumber;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
