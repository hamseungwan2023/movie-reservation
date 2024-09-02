package com.movie.reservation.domain.cinema.service;

import com.movie.reservation.domain.cinema.dto.request.ScreenTImeRequestDto;
import com.movie.reservation.domain.cinema.dto.response.ScreenTimeResponseDto;
import com.movie.reservation.domain.cinema.entity.Screen;
import com.movie.reservation.domain.cinema.entity.ScreenTime;
import com.movie.reservation.domain.cinema.repository.ScreenTimeRepository;
import com.movie.reservation.domain.cinema.repository.mapper.ScreenTimeMapper;
import com.movie.reservation.domain.movie.entity.Movie;
import com.movie.reservation.domain.movie.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class ScreenTimeService {

    private final ScreenTimeRepository screenTimeRepository;
    private final MovieService movieService;
    private final ScreenService screenService;

    public ScreenTimeService(ScreenTimeRepository screenTimeRepository, MovieService movieService, ScreenService screenService) {
        this.screenTimeRepository = screenTimeRepository;
        this.movieService = movieService;
        this.screenService = screenService;
    }

    public void createScreenTime(Long screenId, Long movieId, ScreenTImeRequestDto requestDto) {

        final Screen screen = screenService.findScreen(screenId);
        final Movie movie = movieService.findMovie(movieId);
        final Date startTime = requestDto.getStartTime();
        final Integer runningTime = movie.getDuration();

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(Calendar.MINUTE, runningTime);
        final Date endTIme = calendar.getTime();

        final ScreenTime screenTime = ScreenTime.builder()
                .startTime(startTime)
                .endTime(endTIme)
                .screen(screen)
                .movie(movie)
                .build();
        screenTimeRepository.save(screenTime);
    }

    public Page<ScreenTimeResponseDto> searchScreenTimeByMovie(Long movieId, int page) {

        final Pageable pageable = PageRequest.of(page - 1, 20);

        return screenTimeRepository.searchScreenTImeByMovie(movieId, pageable);
    }

    public Page<ScreenTimeResponseDto> searchScreenTimeByStartTime(Long movieId, String time, int page) {

        final Pageable pageable = PageRequest.of(page - 1, 20);

        return screenTimeRepository.searchScreenTImeByStartTime(movieId, time, pageable);
    }
}
