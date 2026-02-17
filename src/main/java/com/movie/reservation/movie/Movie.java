package com.movie.reservation.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
