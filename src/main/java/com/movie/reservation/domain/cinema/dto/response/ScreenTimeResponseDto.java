package com.movie.reservation.domain.cinema.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class ScreenTimeResponseDto {

    private final Long id;
    private final String title;
    private final Integer duration;
    private final String startTime;
    private final String endTime;
    private final String screenName;
    private final String cinemaName;
    private final String address;

    public ScreenTimeResponseDto(Long id,
                                 String title,
                                 Integer duration,
                                 String startTime,
                                 String endTime,
                                 String screenName,
                                 String cinemaName,
                                 String address) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.screenName = screenName;
        this.cinemaName = cinemaName;
        this.address = address;
    }
}
