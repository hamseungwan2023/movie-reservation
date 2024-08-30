package com.movie.reservation.domain.movie.dto.request;

import lombok.Getter;

@Getter
public class UpdateMovieRequestDto {

    private String title;
    private String description;
    private String genre;
    private Integer duration;
}
