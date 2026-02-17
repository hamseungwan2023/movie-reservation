package com.movie.reservation.seat.service;

import com.movie.reservation.seat.Seat;
import com.movie.reservation.seat.SeatRequest;
import com.movie.reservation.seat.mapper.SeatMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeatService {
    private final SeatMapper seatMapper;

    public SeatService(SeatMapper seatMapper) { this.seatMapper = seatMapper; }

    @Transactional
    public Seat create(SeatRequest request) {
        Seat seat = Seat.builder()
                .screenId(request.screenId())
                .rowName(request.rowName())
                .seatNumber(request.seatNumber())
                .build();
        seatMapper.insert(seat);
        return seat;
    }

    public Seat get(Long id) {
        Seat seat = seatMapper.findById(id);
        if (seat == null) throw new IllegalArgumentException("좌석이 존재하지 않습니다.");
        return seat;
    }

    public List<Seat> getByScreen(Long screenId) { return seatMapper.findByScreenId(screenId); }

    @Transactional
    public void delete(Long id) { seatMapper.delete(id); }
}
