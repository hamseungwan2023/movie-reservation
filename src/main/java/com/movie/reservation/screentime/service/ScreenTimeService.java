package com.movie.reservation.screentime.service;

import com.movie.reservation.screentime.ScreenTime;
import com.movie.reservation.screentime.ScreenTimeRequest;
import com.movie.reservation.screentime.mapper.ScreenTimeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenTimeService {
    private final ScreenTimeMapper screenTimeMapper;

    public ScreenTimeService(ScreenTimeMapper screenTimeMapper) { this.screenTimeMapper = screenTimeMapper; }

    public ScreenTime create(ScreenTimeRequest request) {
        ScreenTime screenTime = new ScreenTime();
        screenTime.setStartTime(request.startTime());
        screenTime.setEndTime(request.endTime());
        screenTime.setScreenId(request.screenId());
        screenTime.setMovieId(request.movieId());
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

    public ScreenTime update(Long id, ScreenTimeRequest request) {
        ScreenTime screenTime = get(id);
        screenTime.setStartTime(request.startTime());
        screenTime.setEndTime(request.endTime());
        screenTime.setScreenId(request.screenId());
        screenTime.setMovieId(request.movieId());
        screenTimeMapper.update(screenTime);
        return get(id);
    }

    public void delete(Long id) { screenTimeMapper.delete(id); }
}
