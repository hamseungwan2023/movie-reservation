package com.movie.reservation.domain.cinema.repository;

import com.movie.reservation.domain.cinema.entity.QSeat;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.Optional;

public class SeatCustomRepositoryImpl implements SeatCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public SeatCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Optional<Long> findSeat(Long seatId) {

        QSeat seat = QSeat.seat;

        return Optional.ofNullable(jpaQueryFactory.select(seat.id)
                .from(seat)
                .where(seat.id.eq(seatId))
                .fetchOne());
    }
}
