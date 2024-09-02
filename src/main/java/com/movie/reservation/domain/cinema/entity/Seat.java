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
    @ManyToOne(fetch = FetchType.LAZY)
    private Screen screen;

    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;
}
