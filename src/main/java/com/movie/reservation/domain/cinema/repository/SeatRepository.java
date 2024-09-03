package com.movie.reservation.domain.cinema.repository;

import com.movie.reservation.domain.cinema.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query("select s " +
            "from Seat s " +
            "join s.screen scr " +
            "where scr.id = :screenId and s.seatNumber = :seatNumber ")
    Optional<Seat> findSeat(@Param("screenId") Long screenId, @Param("seatNumber") int seatNumber);
}
