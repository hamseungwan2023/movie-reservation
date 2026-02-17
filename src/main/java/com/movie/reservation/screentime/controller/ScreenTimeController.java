package com.movie.reservation.screentime.controller;

import com.movie.reservation.common.ApiResponse;
import com.movie.reservation.screentime.ScreenTime;
import com.movie.reservation.screentime.ScreenTimeRequest;
import com.movie.reservation.screentime.service.ScreenTimeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screen-times")
public class ScreenTimeController {
    private final ScreenTimeService screenTimeService;

    public ScreenTimeController(ScreenTimeService screenTimeService) { this.screenTimeService = screenTimeService; }

    @PostMapping
    public ApiResponse<ScreenTime> create(@Valid @RequestBody ScreenTimeRequest request) {
        return ApiResponse.ok("상영시간 생성 성공", screenTimeService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<ScreenTime> get(@PathVariable Long id) { return ApiResponse.ok("조회 성공", screenTimeService.get(id)); }

    @GetMapping("/by-movie")
    public ApiResponse<List<ScreenTime>> getByMovie(@RequestParam Long movieId) {
        return ApiResponse.ok("조회 성공", screenTimeService.getByMovie(movieId));
    }

    @GetMapping("/by-screen")
    public ApiResponse<List<ScreenTime>> getByScreen(@RequestParam Long screenId) {
        return ApiResponse.ok("조회 성공", screenTimeService.getByScreen(screenId));
    }

    @PutMapping("/{id}")
    public ApiResponse<ScreenTime> update(@PathVariable Long id, @Valid @RequestBody ScreenTimeRequest request) {
        return ApiResponse.ok("수정 성공", screenTimeService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) { screenTimeService.delete(id); return ApiResponse.ok("삭제 성공"); }
}
