package com.movie.reservation.domain.movie.controller;

import com.movie.reservation.domain.movie.dto.request.MovieRequestDto;
import com.movie.reservation.domain.movie.dto.response.MovieResponseDto;
import com.movie.reservation.domain.movie.entity.Movie;
import com.movie.reservation.domain.movie.repository.mapper.MovieMapper;
import com.movie.reservation.domain.movie.service.MovieService;
import com.movie.reservation.global.dto.DataResponse;
import com.movie.reservation.global.dto.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/movies")
    public ResponseEntity<MessageResponse> createMovie(@Valid @RequestBody MovieRequestDto requestDto) {

        movieService.createMovie(requestDto);

        return ResponseEntity.ok(new MessageResponse(200, "영화 생성 성공"));
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<DataResponse<MovieResponseDto>> getMovie(@PathVariable("id") Long movieId) {
        return ResponseEntity
                .ok(new DataResponse<>(200, "해당 영화 조회 성공", movieService.getMovie(movieId)));
    }

    @GetMapping("/movies")
    public ResponseEntity<DataResponse<Page<MovieMapper>>> getMovies(@RequestParam(name = "page", defaultValue = "1") int page) {
        return ResponseEntity
                .ok(new DataResponse<>(200, "영화 전체 조회 성공", movieService.getMovies(page)));
    }

    @PatchMapping("/movies/{id}")
    public ResponseEntity<MessageResponse> updateMovie(@PathVariable("id") Long movieId, MovieRequestDto requestDto){

        movieService.updateMovie(movieId, requestDto);

        return ResponseEntity.ok(new MessageResponse(200, "영화 수정 성공"));
    }

    @PostMapping("/movies/{id}/poster")
    public ResponseEntity<MessageResponse> uploadPoster(@PathVariable("id") Long movieId,
                                                        @RequestPart MultipartFile file) throws IOException {
        movieService.uploadPoster(movieId,file);

        return ResponseEntity.ok(new MessageResponse(200,"포스터 업로드 성공"));
    }
}
