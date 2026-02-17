package com.movie.reservation.screentime.service;

import com.movie.reservation.screentime.ScreenTime;
import com.movie.reservation.screentime.ScreenTimeRequest;
import com.movie.reservation.screentime.mapper.ScreenTimeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScreenTimeService {
    private final ScreenTimeMapper screenTimeMapper;

    public ScreenTimeService(ScreenTimeMapper screenTimeMapper) { this.screenTimeMapper = screenTimeMapper; }

    @Transactional
    public ScreenTime create(ScreenTimeRequest request) {
        ScreenTime screenTime = ScreenTime.builder()
                .startTime(request.startTime())
                .endTime(request.endTime())
                .screenId(request.screenId())
                .movieId(request.movieId())
                .build();
        screenTimeMapper.insert(screenTime);
        return screenTime;
    }

    public ScreenTime get(Long id) {
        ScreenTime screenTime = screenTimeMapper.findById(id);
        if (screenTime == null) throw new IllegalArgumentException("상영시간이 존재하지 않습니다.");
        return screenTime;
    }

    public List<ScreenTime> getByMovie(Long movieId) { return screenTimeMapper.findByMovieId(movieId); }
    public List<ScreenTime> getByScreen(Long screenId) { return screenTimeMapper.findByScreenId(screenId); }

    @Transactional
    public ScreenTime update(Long id, ScreenTimeRequest request) {
        get(id);
        ScreenTime screenTime = ScreenTime.builder()
                .id(id)
                .startTime(request.startTime())
                .endTime(request.endTime())
                .screenId(request.screenId())
                .movieId(request.movieId())
                .build();
        screenTimeMapper.update(screenTime);
        return get(id);
    }

    @Transactional
    public void delete(Long id) { screenTimeMapper.delete(id); }
}
