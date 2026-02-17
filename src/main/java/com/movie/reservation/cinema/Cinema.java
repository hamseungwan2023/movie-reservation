package com.movie.reservation.cinema;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Cinema {
    private Long id;
    private String name;
    private String address;
    private String sido;
    private String gungu;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
