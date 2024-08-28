package com.movie.reservation.domain.movie.dto.response;

import com.movie.reservation.domain.movie.entity.Genre;
import com.movie.reservation.domain.movie.entity.Movie;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MovieResponseDto {

    private final String title;
    private final String description;
    private final Genre genre;
    private final String poster;
    private final Integer duration;

    @Builder
    public MovieResponseDto(String title, String description, Genre genre, String poster, Integer duration) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.poster = poster;
        this.duration = duration;
    }
}
