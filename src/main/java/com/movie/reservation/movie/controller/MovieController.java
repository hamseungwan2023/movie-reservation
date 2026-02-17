package com.movie.reservation.movie.controller;

import com.movie.reservation.common.ApiResponse;
import com.movie.reservation.movie.Movie;
import com.movie.reservation.movie.MovieRequest;
import com.movie.reservation.movie.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) { this.movieService = movieService; }

    @PostMapping
    public ApiResponse<Movie> create(@Valid @RequestBody MovieRequest request) {
        return ApiResponse.ok("영화 생성 성공", movieService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<Movie> get(@PathVariable Long id) { return ApiResponse.ok("조회 성공", movieService.get(id)); }

    @GetMapping
    public ApiResponse<List<Movie>> getAll() { return ApiResponse.ok("전체 조회 성공", movieService.getAll()); }

    @PutMapping("/{id}")
    public ApiResponse<Movie> update(@PathVariable Long id, @Valid @RequestBody MovieRequest request) {
        return ApiResponse.ok("수정 성공", movieService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) { movieService.delete(id); return ApiResponse.ok("삭제 성공"); }
}
