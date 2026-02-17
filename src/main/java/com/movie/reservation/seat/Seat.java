package com.movie.reservation.seat;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Seat {
    private Long id;
    private Long screenId;
    private String rowName;
    private Integer seatNumber;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
