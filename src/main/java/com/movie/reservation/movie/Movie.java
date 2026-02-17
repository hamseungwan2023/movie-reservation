package com.movie.reservation.movie;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Movie {
    private Long id;
    private String title;
    private String description;
    private Genre genre;
    private String poster;
    private Integer duration;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
