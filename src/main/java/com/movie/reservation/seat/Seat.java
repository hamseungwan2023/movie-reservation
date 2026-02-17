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
    private Long screenId; // 상영관 ID
    private String rowName; // 좌석 열 이름 예) A
    private Integer seatNumber; // 좌석 번호
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
