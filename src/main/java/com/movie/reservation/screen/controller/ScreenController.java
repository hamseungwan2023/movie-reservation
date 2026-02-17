package com.movie.reservation.screen.controller;

import com.movie.reservation.common.ApiResponse;
import com.movie.reservation.screen.Screen;
import com.movie.reservation.screen.ScreenRequest;
import com.movie.reservation.screen.service.ScreenService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screens")
public class ScreenController {
    private final ScreenService screenService;

    public ScreenController(ScreenService screenService) { this.screenService = screenService; }

    @PostMapping
    public ApiResponse<Screen> create(@Valid @RequestBody ScreenRequest request) {
        return ApiResponse.ok("상영관 생성 성공", screenService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<Screen> get(@PathVariable Long id) { return ApiResponse.ok("조회 성공", screenService.get(id)); }

    @GetMapping
    public ApiResponse<List<Screen>> getByCinema(@RequestParam Long cinemaId) {
        return ApiResponse.ok("조회 성공", screenService.getByCinema(cinemaId));
    }

    @PutMapping("/{id}")
    public ApiResponse<Screen> update(@PathVariable Long id, @Valid @RequestBody ScreenRequest request) {
        return ApiResponse.ok("수정 성공", screenService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) { screenService.delete(id); return ApiResponse.ok("삭제 성공"); }
}
