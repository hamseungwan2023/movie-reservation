package com.movie.reservation.domain.cinema.dto.request;

import lombok.Getter;

@Getter
public class UpdateScreenRequestDto {

    private String name;
    private Integer totalSeat;
}
