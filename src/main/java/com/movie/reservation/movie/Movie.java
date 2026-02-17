package com.movie.reservation.movie;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 영화 테이블
 */
@Data
public class Movie {
    private Long id;
    private String title; // 영화 제목
    private String description; // 영화 설명
    private Genre genre; // 장르
    private String poster; // 포스터의 URL
    private Integer duration; // 영화의 런타임
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
