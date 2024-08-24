package com.movie.reservation.domain.cinema.entity;

import com.movie.reservation.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Seat extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false, name = "screen_id")
    @ManyToOne
    private Screen screen;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;
}
