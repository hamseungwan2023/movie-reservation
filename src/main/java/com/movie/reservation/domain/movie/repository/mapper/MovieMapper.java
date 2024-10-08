package com.movie.reservation.domain.movie.repository.mapper;

import com.movie.reservation.domain.movie.entity.Genre;

public interface MovieMapper {

    Long getId();
    String getTitle();
    String getDescription();
    Genre getGenre();
    Integer getDuration();
    String getPoster();
}
