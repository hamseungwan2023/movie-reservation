package com.movie.reservation.domain.cinema.controller;

import com.movie.reservation.domain.cinema.dto.request.CinemaRequestDto;
import com.movie.reservation.domain.cinema.dto.request.UpdateCinemaRequestDto;
import com.movie.reservation.domain.cinema.dto.response.CinemaResponseDto;
import com.movie.reservation.domain.cinema.repository.mapper.CinemaMapper;
import com.movie.reservation.domain.cinema.service.CinemaService;
import com.movie.reservation.global.dto.DataResponse;
import com.movie.reservation.global.dto.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CinemaController {

    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @PostMapping("/cinemas")
    public ResponseEntity<MessageResponse> createCinema(@Valid @RequestBody CinemaRequestDto requestDto) {

        cinemaService.CreateCinema(requestDto);

        return ResponseEntity
                .ok(new MessageResponse(200, "영화관 생성 성공"));
    }

    @PatchMapping("/cinemas/{id}")
    public ResponseEntity<MessageResponse> updateCinema(@PathVariable("id") Long cinemaId,
                                                        @Valid @RequestBody UpdateCinemaRequestDto requestDto){

        cinemaService.updateCinema(cinemaId, requestDto);

        return ResponseEntity.ok(new MessageResponse(200,"영화관 수정 성공"));
    }

    @GetMapping("/cinemas/{id}")
    public ResponseEntity<DataResponse<CinemaResponseDto>> getCinema(@PathVariable("id") Long cinemaId) {
        return ResponseEntity.
                ok(new DataResponse<>(200, "해당 영화관 조회 성공", cinemaService.getCinema(cinemaId)));
    }

    @GetMapping("/cinemas")
    public ResponseEntity<DataResponse<Page<CinemaMapper>>> getCinemas(@RequestParam(defaultValue = "1", name = "page") int page) {
        return ResponseEntity.
                ok(new DataResponse<>(200, "영화관 전체 조회 성공", cinemaService.getCinemas(page)));
    }

    @GetMapping("/cinemas/sido/search")
    public ResponseEntity<DataResponse<Page<CinemaMapper>>> searchBySido(@RequestParam(defaultValue = "1", name = "page") int page,
                                                                         @RequestParam(name = "sido") String sido) {
        return ResponseEntity.
                ok(new DataResponse<>(200, "시,도별 조회 성공", cinemaService.searchBySido(sido, page)));
    }

    @GetMapping("/cinemas/sido/gungu/search")
    public ResponseEntity<DataResponse<Page<CinemaMapper>>> searchBySidoAndGungu(@RequestParam(defaultValue = "1", name = "page") int page,
                                                                                 @RequestParam(name = "sido") String sido,
                                                                                 @RequestParam(name = "gungu") String gungu) {
        return ResponseEntity.
                ok(new DataResponse<>(200, "시,도 군,구별 조회 성공", cinemaService.searchBySidoAndGungu(sido, gungu, page)));
    }
}
