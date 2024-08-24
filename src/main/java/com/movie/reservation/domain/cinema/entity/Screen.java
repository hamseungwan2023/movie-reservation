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
public class Screen extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "total_seat")
    private Integer totalSeat;

    @JoinColumn(nullable = false, name = "cinema_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cinema cinemaId;
}
