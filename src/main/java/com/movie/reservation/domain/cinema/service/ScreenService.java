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

    public ScreenService(ScreenRepository screenRepository, CinemaService cinemaService) {
        this.screenRepository = screenRepository;
        this.cinemaService = cinemaService;
    }

    public void createScreen(Long cinemaId, ScreenRequestDto requestDto) {

        final Cinema cinema = cinemaService.cinemaGetById(cinemaId);

        final Screen screen = Screen.builder()
                .name(requestDto.getName())
                .cinema(cinema)
                .totalSeat(requestDto.getTotalSeat())
                .build();

        screenRepository.save(screen);
    }

    public ScreenResponseDto getScreen(Long id) {

        final Screen screen = screenRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 상영관은 존재하지 않습니다."));

        return ScreenResponseDto.builder()
//                .cinemaId(screen.getCinemaId())
                .totalSeat(screen.getTotalSeat())
                .name(screen.getName())
                .build();
    }

    public Page<ScreenMapper> getScreensByCinema(Long cinemaId, int page) {

        final Pageable pageable = PageRequest.of(page - 1, 10);

        return screenRepository.findAllByCinemaIdOrderByCreatedAtDesc(cinemaId, pageable);
    }

    public void updateScreen(Long screenId, UpdateScreenRequestDto requestDto) {

        final Screen screen = screenRepository.findById(screenId)
                .orElseThrow(() -> new NotFoundException("해당 상영관을 찾을 수 없습니다."));

        if (requestDto.getName() != null) {
            screen.updateName(requestDto.getName());
        }
        if (requestDto.getTotalSeat() != null) {
            screen.updateTotalSeat(requestDto.getTotalSeat());
        }

        screenRepository.save(screen);
    }
}
