package com.movie.reservation.domain.reservation.repository;

import com.movie.reservation.domain.reservation.entity.QReservation;
import com.movie.reservation.domain.reservation.entity.Reservation;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.Optional;

public class ReservationCustomRepositoryImpl implements ReservationCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public ReservationCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Optional<Reservation> findReservation(Long screenTimeId, Long seatId) {

        QReservation  reservation = QReservation.reservation;
        ;

        return Optional.ofNullable(jpaQueryFactory.selectFrom(reservation)
                .where(reservation.screenTime.eq(screenTimeId).and(reservation.seat.eq(seatId)))
                .fetchOne());
    }
}
