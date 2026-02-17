package com.movie.reservation.movie.mapper;

import com.movie.reservation.movie.Movie;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieMapper {
    void insert(Movie movie);
    Movie findById(Long id);
    List<Movie> findAll();
    void update(Movie movie);
    void delete(Long id);
}
