package com.movie.reservation.reservation.service;

import com.movie.reservation.reservation.Reservation;
import com.movie.reservation.reservation.ReservationRequest;
import com.movie.reservation.reservation.ReservationStatus;
import com.movie.reservation.reservation.mapper.ReservationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationMapper reservationMapper) { this.reservationMapper = reservationMapper; }

    public Reservation reserve(ReservationRequest request) {
        Reservation existing = reservationMapper.findByScreenTimeAndSeat(request.screenTimeId(), request.seatNumber());
        if (existing != null && existing.getReservationStatus() == ReservationStatus.CONFIRMED) {
            throw new IllegalArgumentException("이미 예약된 좌석입니다.");
        }

        Reservation reservation = new Reservation();
        reservation.setScreenTimeId(request.screenTimeId());
        reservation.setSeatNumber(request.seatNumber());
        reservation.setUsername(request.username());
        reservation.setReservationStatus(ReservationStatus.CONFIRMED);
        reservationMapper.insert(reservation);
        return reservation;
    }

    public void cancel(Long id) {
        Reservation reservation = reservationMapper.findById(id);
        if (reservation == null) throw new IllegalArgumentException("예약이 존재하지 않습니다.");
        reservationMapper.updateStatus(id, ReservationStatus.CANCELLED.name());
    }

    public List<Reservation> getByUsername(String username) {
        return reservationMapper.findByUsername(username);
    }
}
