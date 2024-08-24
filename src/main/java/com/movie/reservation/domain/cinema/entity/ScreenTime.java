package com.movie.reservation.domain.cinema.entity;

import com.movie.reservation.domain.movie.entity.Movie;
import com.movie.reservation.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ScreenTime extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp startTime;

    @Column(nullable = false)
    private Timestamp endTime;

    @JoinColumn(nullable = false, name = "screen_id")
    @ManyToOne
    private Screen screen;

    @JoinColumn(nullable = false, name = "movie_id")
    @ManyToOne
    private Movie movie;
}
