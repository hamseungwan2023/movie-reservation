package com.movie.reservation.domain.reservation.repository;

import com.movie.reservation.domain.reservation.entity.Reservation;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ReservationCustomRepository {
    Optional<Reservation> findReservation(Long screenTimeId, Long seatId);
}
