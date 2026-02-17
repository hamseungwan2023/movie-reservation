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
    private String title; // 영화 제목
    private String description; // 영화 설명
    private Genre genre; // 영화 장르
    private String poster; // 포스터 URL
    private Integer duration; // 상영 시간(분)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
