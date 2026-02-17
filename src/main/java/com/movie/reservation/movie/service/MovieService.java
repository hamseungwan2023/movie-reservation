package com.movie.reservation.movie.service;

import com.movie.reservation.movie.Movie;
import com.movie.reservation.movie.MovieRequest;
import com.movie.reservation.movie.mapper.MovieMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieService {
    private final MovieMapper movieMapper;

    public MovieService(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    @Transactional
    public Movie create(MovieRequest request) {
        Movie movie = new Movie(null, request.title(), request.description(), request.genre(), request.poster(), request.duration(), null, null);
        movieMapper.insert(movie);
        return movie;
    }

    public Movie get(Long id) {
        Movie movie = movieMapper.findById(id);
        if (movie == null) throw new IllegalArgumentException("영화가 존재하지 않습니다.");
        return movie;
    }

    public List<Movie> getAll() { return movieMapper.findAll(); }

    @Transactional
    public Movie update(Long id, MovieRequest request) {
        get(id);
        Movie movie = new Movie(id, request.title(), request.description(), request.genre(), request.poster(), request.duration(), null, null);
        movieMapper.update(movie);
        return get(id);
    }

    @Transactional
    public void delete(Long id) { movieMapper.delete(id); }
}
