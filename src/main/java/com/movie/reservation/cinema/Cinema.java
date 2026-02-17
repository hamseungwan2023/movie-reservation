package com.movie.reservation.cinema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cinema {
    private Long id;
    private String name;
    private String address;
    private String sido;
    private String gungu;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
