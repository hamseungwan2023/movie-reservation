package com.movie.reservation.seat.controller;

import com.movie.reservation.common.ApiResponse;
import com.movie.reservation.seat.Seat;
import com.movie.reservation.seat.SeatRequest;
import com.movie.reservation.seat.service.SeatService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {
    private final SeatService seatService;

    public SeatController(SeatService seatService) { this.seatService = seatService; }

    @PostMapping
    public ApiResponse<Seat> create(@Valid @RequestBody SeatRequest request) {
        return ApiResponse.ok("좌석 생성 성공", seatService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<Seat> get(@PathVariable Long id) { return ApiResponse.ok("조회 성공", seatService.get(id)); }

    @GetMapping
    public ApiResponse<List<Seat>> getByScreen(@RequestParam Long screenId) {
        return ApiResponse.ok("조회 성공", seatService.getByScreen(screenId));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) { seatService.delete(id); return ApiResponse.ok("삭제 성공"); }
}
