package com.movie.reservation.domain.cinema.controller;

import com.movie.reservation.domain.cinema.dto.request.ScreenRequestDto;
import com.movie.reservation.domain.cinema.dto.request.UpdateScreenRequestDto;
import com.movie.reservation.domain.cinema.dto.response.ScreenResponseDto;
import com.movie.reservation.domain.cinema.repository.mapper.ScreenMapper;
import com.movie.reservation.domain.cinema.service.ScreenService;
import com.movie.reservation.global.dto.DataResponse;
import com.movie.reservation.global.dto.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ScreenController {

    private final ScreenService screenService;

    public ScreenController(ScreenService screenService) {
        this.screenService = screenService;
    }

    @PostMapping("/cinemas/{cinemaId}/screens")
    public ResponseEntity<MessageResponse> createScreen(@PathVariable("cinemaId") Long cinemaId,
                                                        @Valid @RequestBody ScreenRequestDto requestDto) {

        screenService.createScreen(cinemaId, requestDto);

        return ResponseEntity
                .ok(new MessageResponse(200, "상영관 생성 성공"));
    }

    @GetMapping("/cinemas/{cinemaId}/screens")
    public ResponseEntity<DataResponse<Page<ScreenMapper>>> getScreensByCinema(@PathVariable("cinemaId") Long cinemaId,
                                                                               @RequestParam(name = "page", defaultValue = "1") int page) {
        return ResponseEntity
                .ok(new DataResponse<>(200,
                        "해당 영화관에 있는 상영관 조회 성공",
                        screenService.getScreensByCinema(cinemaId, page)));
    }

    @GetMapping("/screens/{screenId}")
    public ResponseEntity<DataResponse<ScreenResponseDto>> getScreen(@PathVariable("screenId") Long screenId) {
        return ResponseEntity
                .ok(new DataResponse<>(200, "해당 상영관 조회 성공", screenService.getScreen(screenId)));
    }

    @PatchMapping("/screens/{screenId}")
    public ResponseEntity<MessageResponse> updateScreen(@PathVariable("screenId") Long screenId,
                                                        @Valid @RequestBody UpdateScreenRequestDto requestDto) {

        screenService.updateScreen(screenId, requestDto);

        return ResponseEntity.ok(new MessageResponse(200, "상영관 수정 성공"));
    }
}
