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

    @Column(nullable = false, name = "reservation_status")
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @Column(nullable = false, name = "screen_time_id")
    private Long screenTime;

    @Column(nullable = false, name = "seat_id")
    private Long seat;

    @JoinColumn(nullable = false, name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public void updateReservation(ReservationStatus reservationStatus, User user){
        this.reservationStatus = reservationStatus;
        this.user = user;
    }
}
