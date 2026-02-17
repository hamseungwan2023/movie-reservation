package com.movie.reservation.movie.service;

import com.movie.reservation.movie.Movie;
import com.movie.reservation.movie.MovieRequest;
import com.movie.reservation.movie.mapper.MovieMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieMapper movieMapper;

    public MovieService(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    public Movie create(MovieRequest request) {
        Movie movie = new Movie();
        movie.setTitle(request.title());
        movie.setDescription(request.description());
        movie.setGenre(request.genre());
        movie.setPoster(request.poster());
        movie.setDuration(request.duration());
        movieMapper.insert(movie);
        return movie;
    }

    public Movie get(Long id) {
        Movie movie = movieMapper.findById(id);
        if (movie == null) throw new IllegalArgumentException("영화가 존재하지 않습니다.");
        return movie;
    }

    public List<Movie> getAll() { return movieMapper.findAll(); }

    public Movie update(Long id, MovieRequest request) {
        Movie movie = get(id);
        movie.setTitle(request.title());
        movie.setDescription(request.description());
        movie.setGenre(request.genre());
        movie.setPoster(request.poster());
        movie.setDuration(request.duration());
        movieMapper.update(movie);
        return get(id);
    }

    public void delete(Long id) { movieMapper.delete(id); }
}
