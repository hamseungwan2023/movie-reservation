package com.movie.reservation.screentime.mapper;

import com.movie.reservation.screentime.ScreenTime;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScreenTimeMapper {
    void insert(ScreenTime screenTime);
    ScreenTime findById(Long id);
    List<ScreenTime> findByMovieId(Long movieId);
    List<ScreenTime> findByScreenId(Long screenId);
    void update(ScreenTime screenTime);
    void delete(Long id);
}
