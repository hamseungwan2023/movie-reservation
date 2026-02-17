package com.movie.reservation.reservation.controller;

import com.movie.reservation.common.ApiResponse;
import com.movie.reservation.reservation.Reservation;
import com.movie.reservation.reservation.ReservationRequest;
import com.movie.reservation.reservation.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) { this.reservationService = reservationService; }

    @PostMapping
    public ApiResponse<Reservation> reserve(@Valid @RequestBody ReservationRequest request) {
        return ApiResponse.ok("예약 성공", reservationService.reserve(request));
    }

    @PatchMapping("/{id}/cancel")
    public ApiResponse<Void> cancel(@PathVariable Long id) {
        reservationService.cancel(id);
        return ApiResponse.ok("예약 취소 성공");
    }

    @GetMapping
    public ApiResponse<List<Reservation>> getByUsername(@RequestParam String username) {
        return ApiResponse.ok("조회 성공", reservationService.getByUsername(username));
    }
}
