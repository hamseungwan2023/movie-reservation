package com.movie.reservation.reservation.mapper;

import com.movie.reservation.reservation.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    void insert(Reservation reservation);
    Reservation findById(Long id);
    Reservation findByScreenTimeAndSeat(Long screenTimeId, Integer seatNumber);
    List<Reservation> findByUsername(String username);
    void updateStatus(Long id, String reservationStatus);
    void delete(Long id);
}
