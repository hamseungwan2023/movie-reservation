package com.movie.reservation.reservation.mapper;

import com.movie.reservation.reservation.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {
    void insert(Reservation reservation);
    Reservation findById(Long id);
    Reservation findByScreenTimeAndSeat(@Param("screenTimeId") Long screenTimeId,
                                        @Param("seatNumber") Integer seatNumber);
    List<Reservation> findByUsername(String username);
    void updateStatus(@Param("id") Long id,
                      @Param("reservationStatus") String reservationStatus);
    void delete(Long id);
}
