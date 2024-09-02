package com.movie.reservation.domain.cinema.service;

import com.movie.reservation.domain.cinema.dto.request.ScreenRequestDto;
import com.movie.reservation.domain.cinema.dto.request.UpdateScreenRequestDto;
import com.movie.reservation.domain.cinema.dto.response.ScreenResponseDto;
import com.movie.reservation.domain.cinema.entity.Cinema;
import com.movie.reservation.domain.cinema.entity.Screen;
import com.movie.reservation.domain.cinema.repository.ScreenRepository;
import com.movie.reservation.domain.cinema.repository.mapper.ScreenMapper;
import com.movie.reservation.global.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ScreenService {

    private final ScreenRepository screenRepository;
    private final CinemaService cinemaService;
    private final SeatService seatService;

    public ScreenService(ScreenRepository screenRepository, CinemaService cinemaService, SeatService seatService) {
        this.screenRepository = screenRepository;
        this.cinemaService = cinemaService;
        this.seatService = seatService;
    }

    public void createScreen(Long cinemaId, ScreenRequestDto requestDto) {

        final Cinema cinema = cinemaService.findCinema(cinemaId);

        final Screen screen = Screen.builder()
                .name(requestDto.getName())
                .cinema(cinema)
                .totalSeat(requestDto.getTotalSeat())
                .build();

        screenRepository.save(screen);
        for(int i = 1; i<=screen.getTotalSeat(); i++){
            seatService.createSeat(screen,i);
        }
    }

    public ScreenResponseDto getScreen(Long screenId) {

        final Screen screen = findScreen(screenId);
        return ScreenResponseDto.builder()
                .totalSeat(screen.getTotalSeat())
                .name(screen.getName())
                .build();
    }

    public Page<ScreenMapper> getScreensByCinema(Long cinemaId, int page) {

        final Pageable pageable = PageRequest.of(page - 1, 10);

        return screenRepository.findAllByCinemaIdOrderByCreatedAtDesc(cinemaId, pageable);
    }

    public void updateScreen(Long screenId, UpdateScreenRequestDto requestDto) {

        final Screen screen = findScreen(screenId);

        if (requestDto.getName() != null) {
            screen.updateName(requestDto.getName());
        }
        if (requestDto.getTotalSeat() != null) {
            screen.updateTotalSeat(requestDto.getTotalSeat());
        }

        screenRepository.save(screen);
    }

    public Screen findScreen(Long id) {
        return screenRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 상영관은 존재하지 않습니다."));
    }
}
