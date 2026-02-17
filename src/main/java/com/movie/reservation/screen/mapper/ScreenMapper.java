package com.movie.reservation.screen.mapper;

import com.movie.reservation.screen.Screen;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScreenMapper {
    void insert(Screen screen);
    Screen findById(Long id);
    List<Screen> findByCinemaId(Long cinemaId);
    void update(Screen screen);
    void delete(Long id);
}
