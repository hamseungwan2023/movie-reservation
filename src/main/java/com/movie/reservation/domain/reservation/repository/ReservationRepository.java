package com.movie.reservation.domain.reservation.repository;

import com.movie.reservation.domain.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r " +
            "FROM Reservation r " +
            "WHERE r.screenTime.id = :screenTimeId AND r.seat.id = :seatId")
    Optional<Reservation> findReservation(@Param("screenTimeId") Long screenTimeId, @Param("seatId") Long seatId);
}
