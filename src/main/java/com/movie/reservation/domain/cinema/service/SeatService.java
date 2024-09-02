package com.movie.reservation.domain.cinema.service;

import com.movie.reservation.domain.cinema.entity.Screen;
import com.movie.reservation.domain.cinema.entity.Seat;
import com.movie.reservation.domain.cinema.repository.SeatRepository;
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
}
