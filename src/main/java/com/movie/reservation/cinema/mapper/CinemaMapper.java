package com.movie.reservation.cinema.mapper;

import com.movie.reservation.cinema.Cinema;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CinemaMapper {
    void insert(Cinema cinema);
    Cinema findById(Long id);
    List<Cinema> findAll();
    void update(Cinema cinema);
    void delete(Long id);
}
