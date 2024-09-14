package com.movie.reservation.domain.reservation.service;

import com.movie.reservation.domain.cinema.entity.ScreenTime;
import com.movie.reservation.domain.cinema.entity.Seat;
import com.movie.reservation.domain.cinema.service.ScreenTimeService;
import com.movie.reservation.domain.cinema.service.SeatService;
import com.movie.reservation.domain.reservation.entity.Reservation;
import com.movie.reservation.domain.reservation.entity.ReservationStatus;
import com.movie.reservation.domain.reservation.repository.ReservationRepository;
import com.movie.reservation.domain.user.entity.User;
import com.movie.reservation.domain.user.service.UserService;
import com.movie.reservation.global.exception.BadRequestException;
import com.movie.reservation.global.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ScreenTimeService screenTimeService;
    private final SeatService seatService;
    private final UserService userService;

    public ReservationService(ReservationRepository reservationRepository, SimpMessagingTemplate simpMessagingTemplate, ScreenTimeService screenTimeService, SeatService seatService, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.screenTimeService = screenTimeService;
        this.seatService = seatService;
        this.userService = userService;
    }

    @Transactional
    public void reserveSeat(Long screenId, int seatNumber, Long screenTimeId, String username) {

        final User user = userService.findUser(username);
        final Optional<Reservation> existReservation = reservationRepository.findReservation(screenTimeId, screenId);

        if (existReservation.isPresent()) {
            if (existReservation.get().getReservationStatus().equals(ReservationStatus.CONFIRMED)) {
                throw new BadRequestException("이미 예약이 된 좌석 입니다.");
            } else if (existReservation.get().getReservationStatus().equals(ReservationStatus.CANCELLED)) {
                existReservation.get().updateReservation(ReservationStatus.CONFIRMED, user);
                simpMessagingTemplate.convertAndSend("/topic/seats/" + screenTimeId, existReservation);
                return;
            }
        }

        final ScreenTime screenTime = screenTimeService.findScreenTime(screenTimeId);
        final Seat seat = seatService.findSeat(screenId, seatNumber);
        final Reservation reservation = Reservation.builder()
                .seat(seat)
                .user(user)
                .screenTime(screenTime)
                .reservationStatus(ReservationStatus.CONFIRMED)
                .build();
        reservationRepository.save(reservation);

        simpMessagingTemplate.convertAndSend("/topic/seats/" + screenTimeId, reservation);
    }

    @Transactional
    public void cancelReservation(Long reservationId, String username) {

        final Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("해당 예약이 없습니다."));
        final User user = userService.findUser(username);

        if (reservation.getUser() == user) {
            reservation.updateReservation(ReservationStatus.CANCELLED, user);
            simpMessagingTemplate.convertAndSend("/topic/seats/" + reservation.getScreenTime().getId(), reservation);
        } else {
            throw new BadRequestException("본인의 예약만 취소 할 수 있습니다.");
        }
    }
}
