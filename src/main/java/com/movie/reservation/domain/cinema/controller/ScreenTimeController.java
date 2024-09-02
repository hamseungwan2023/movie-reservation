package com.movie.reservation.domain.cinema.controller;

import com.movie.reservation.domain.cinema.dto.request.ScreenTImeRequestDto;
import com.movie.reservation.domain.cinema.dto.response.ScreenTimeResponseDto;
import com.movie.reservation.domain.cinema.repository.mapper.ScreenTimeMapper;
import com.movie.reservation.domain.cinema.service.ScreenTimeService;
import com.movie.reservation.global.dto.DataResponse;
import com.movie.reservation.global.dto.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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

    @GetMapping("/movies/{movieId}/screenTimes")
    public ResponseEntity<DataResponse<Page<ScreenTimeResponseDto>>> searchScreenTimeByMovieId(@PathVariable("movieId") Long movieId,
                                                                                               @RequestParam(name = "page", defaultValue = "1") int page) {
        return ResponseEntity.ok
                (new DataResponse<>(200,
                        "해당 영화에 대한 상영시간 조회 성공",
                        screenTimeService.searchScreenTimeByMovie(movieId, page)));
    }

    @GetMapping("/movies/{movieId}/day/screenTimes")
    public ResponseEntity<DataResponse<Page<ScreenTimeResponseDto>>> searchScreenTimeByStartTime(@PathVariable("movieId") Long movieId,
                                                                                                 @RequestParam(name = "page", defaultValue = "1") int page,
                                                                                                 @RequestParam(name = "day") String day) {
        return ResponseEntity.ok
                (new DataResponse<>(200,
                        "해당 영화와 상영날짜를 이용한 상영시간 조회 성공",
                        screenTimeService.searchScreenTimeByStartTime(movieId, day, page)));
    }
}
