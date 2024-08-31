package com.movie.reservation.domain.cinema.dto.response;

import com.movie.reservation.domain.cinema.entity.Cinema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ScreenResponseDto {

    private final String name;
    private final Integer totalSeat;
//    private final Cinema cinemaId;

    @Builder
    public ScreenResponseDto(String name, Integer totalSeat) {
        this.name = name;
        this.totalSeat = totalSeat;
    }
}
