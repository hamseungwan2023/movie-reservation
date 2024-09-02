package com.movie.reservation.domain.cinema.repository.mapper;

import java.util.Date;

public interface ScreenTimeMapper {
    Long getId();
    String getTitle();
    Integer getDuration();
    Date getStartTime();
    Date getEndTime();
    String getScreenName();
    String getCinemaName();
    String getAddress();

}
