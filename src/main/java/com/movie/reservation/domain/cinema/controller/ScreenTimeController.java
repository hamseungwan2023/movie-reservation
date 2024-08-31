package com.movie.reservation.domain.cinema.controller;

import com.movie.reservation.domain.cinema.dto.request.ScreenTImeRequestDto;
import com.movie.reservation.domain.cinema.service.ScreenTimeService;
import com.movie.reservation.global.dto.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ScreenTimeController {

    private final ScreenTimeService screenTimeService;

    public ScreenTimeController(ScreenTimeService screenTimeService) {
        this.screenTimeService = screenTimeService;
    }

    @PostMapping("/screens/{screenId}/movies/{movieId}/screenTime")
    public ResponseEntity<MessageResponse> createScreenTIme(@PathVariable("screenId") Long screenId,
                                                            @PathVariable("movieId") Long movieId,
                                                            @Valid @RequestBody ScreenTImeRequestDto requestDto) {

        screenTimeService.createScreenTime(screenId, movieId, requestDto);

        return ResponseEntity.ok(new MessageResponse(200, "상영 시간 생성 성공"));
    }
}
