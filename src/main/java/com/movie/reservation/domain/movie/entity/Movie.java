package com.movie.reservation.domain.movie.entity;

import com.movie.reservation.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Movie extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer duration;

    @Column
    private String poster;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateGenre(String genre) {
        this.genre = Genre.valueOf(genre);
    }

    public void updatePoster(String poster) {
        this.poster = poster;
    }

    public void updateDuration(Integer duration) {
        this.duration = duration;
    }
}
