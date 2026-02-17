package com.movie.reservation.screen.service;

import com.movie.reservation.screen.Screen;
import com.movie.reservation.screen.ScreenRequest;
import com.movie.reservation.screen.mapper.ScreenMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScreenService {
    private final ScreenMapper screenMapper;

    public ScreenService(ScreenMapper screenMapper) { this.screenMapper = screenMapper; }

    @Transactional
    public Screen create(ScreenRequest request) {
        Screen screen = new Screen(null, request.name(), request.totalSeat(), request.cinemaId(), null, null);
        screenMapper.insert(screen);
        return screen;
    }

    public Screen get(Long id) {
        Screen screen = screenMapper.findById(id);
        if (screen == null) throw new IllegalArgumentException("상영관이 존재하지 않습니다.");
        return screen;
    }

    public List<Screen> getByCinema(Long cinemaId) { return screenMapper.findByCinemaId(cinemaId); }

    @Transactional
    public Screen update(Long id, ScreenRequest request) {
        get(id);
        Screen screen = new Screen(id, request.name(), request.totalSeat(), request.cinemaId(), null, null);
        screenMapper.update(screen);
        return get(id);
    }

    @Transactional
    public void delete(Long id) { screenMapper.delete(id); }
}
