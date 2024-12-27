package com.movie.reservation.domain.cinema.repository;

import java.util.Optional;

public interface SeatCustomRepository {
    Optional<Long> findSeat(Long seatId);
}
