package com.movie.reservation.cinema.controller;

import com.movie.reservation.cinema.Cinema;
import com.movie.reservation.cinema.CinemaRequest;
import com.movie.reservation.cinema.service.CinemaService;
import com.movie.reservation.common.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinemas")
public class CinemaController {
    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) { this.cinemaService = cinemaService; }

    @PostMapping
    public ApiResponse<Cinema> create(@Valid @RequestBody CinemaRequest request) {
        return ApiResponse.ok("영화관 생성 성공", cinemaService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<Cinema> get(@PathVariable Long id) { return ApiResponse.ok("조회 성공", cinemaService.get(id)); }

    @GetMapping
    public ApiResponse<List<Cinema>> getAll() { return ApiResponse.ok("전체 조회 성공", cinemaService.getAll()); }

    @PutMapping("/{id}")
    public ApiResponse<Cinema> update(@PathVariable Long id, @Valid @RequestBody CinemaRequest request) {
        return ApiResponse.ok("수정 성공", cinemaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) { cinemaService.delete(id); return ApiResponse.ok("삭제 성공"); }
}
