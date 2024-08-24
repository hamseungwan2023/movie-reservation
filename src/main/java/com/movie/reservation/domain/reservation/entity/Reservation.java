package com.movie.reservation.domain.reservation.entity;

import com.movie.reservation.domain.cinema.entity.ScreenTime;
import com.movie.reservation.domain.cinema.entity.Seat;
import com.movie.reservation.domain.user.entity.User;
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
public class Reservation extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @JoinColumn(nullable = false, name = "screen_time_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ScreenTime screenTime;

    @JoinColumn(nullable = false, name = "seat_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Seat seat;

    @JoinColumn(nullable = false, name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
