package com.movie.reservation.domain.reservation.controller;

import com.movie.reservation.domain.reservation.service.ReservationService;
import com.movie.reservation.global.dto.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/screens/{screenId}/screenTimes/{screenTimeId}/reserve")
    public ResponseEntity<MessageResponse> reserveSeat(@PathVariable("screenId") Long screenId,
                                                       @PathVariable("screenTimeId") Long screenTimeId,
                                                       @AuthenticationPrincipal UserDetails userDetails,
                                                       @RequestParam(name = "seatNumber") int seatNumber) {

        reservationService.reserveSeat(screenId, seatNumber, screenTimeId, userDetails.getUsername());
        return ResponseEntity.ok(new MessageResponse(200, "좌석 예약 성공"));
    }

    @DeleteMapping("/reservations/{reservationId}/cancel")
    public ResponseEntity<MessageResponse> cancelReservation(@PathVariable("reservationId") Long reservationId,
                                                             @AuthenticationPrincipal UserDetails userDetails){

        reservationService.cancelReservation(reservationId, userDetails.getUsername());

        return ResponseEntity.ok(new MessageResponse(200, "예약 취소 성공"));
    }
}
