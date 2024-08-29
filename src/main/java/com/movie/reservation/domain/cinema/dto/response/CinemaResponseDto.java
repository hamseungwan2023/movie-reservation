package com.movie.reservation.domain.cinema.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CinemaResponseDto {

    private final String name;
    private final String address;
    private final String sido;
    private final String gungu;

    @Builder
    public CinemaResponseDto(String name, String address, String sido, String gungu) {
        this.name = name;
        this.address = address;
        this.sido = sido;
        this.gungu = gungu;
    }
}
