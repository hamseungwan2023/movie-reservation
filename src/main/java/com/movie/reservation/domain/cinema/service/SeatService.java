package com.movie.reservation.domain.cinema.service;

import com.movie.reservation.domain.cinema.entity.Screen;
import com.movie.reservation.domain.cinema.entity.Seat;
import com.movie.reservation.domain.cinema.repository.SeatRepository;
import com.movie.reservation.global.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public void createSeat(Screen screen, int i) {

        final Seat seat = Seat.builder()
                .screen(screen)
                .seatNumber(i)
                .build();

        seatRepository.save(seat);
    }

    public Seat findSeat(Long screenId, int seatNumber){
        return seatRepository.findSeat(screenId, seatNumber)
                .orElseThrow(()-> new NotFoundException("해당 좌석은 존재하지 않습니다."));
    }
}
