package com.movie.reservation.seat.mapper;

import com.movie.reservation.seat.Seat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SeatMapper {
    void insert(Seat seat);
    Seat findById(Long id);
    List<Seat> findByScreenId(Long screenId);
    Seat findByScreenAndPosition(Long screenId, String rowName, Integer seatNumber);
    void delete(Long id);
}
