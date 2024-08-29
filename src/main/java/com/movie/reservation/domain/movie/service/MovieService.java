package com.movie.reservation.domain.movie.service;

import com.movie.reservation.domain.movie.dto.request.MovieRequestDto;
import com.movie.reservation.domain.movie.dto.request.UpdateMovieRequestDto;
import com.movie.reservation.domain.movie.dto.response.MovieResponseDto;
import com.movie.reservation.domain.movie.entity.Genre;
import com.movie.reservation.domain.movie.entity.Movie;
import com.movie.reservation.domain.movie.repository.MovieRepository;
import com.movie.reservation.domain.movie.repository.mapper.MovieMapper;
import com.movie.reservation.global.exception.NotFoundException;
import com.movie.reservation.global.service.S3Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final S3Service s3Service;

    public MovieService(MovieRepository movieRepository, S3Service s3Service) {
        this.movieRepository = movieRepository;
        this.s3Service = s3Service;
    }

    public void createMovie(MovieRequestDto requestDto) {

        final Movie movie = Movie.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .duration(requestDto.getDuration())
                .genre(Genre.valueOf(requestDto.getGenre()))
                .build();

        movieRepository.save(movie);
    }

    public MovieResponseDto getMovie(Long id) {

        final Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 영화를 찾을 수 없습니다."));

        return MovieResponseDto.builder()
                .title(movie.getTitle())
                .description(movie.getDescription())
                .genre(movie.getGenre())
                .duration(movie.getDuration())
                .poster(movie.getPoster())
                .build();
    }

    public Page<MovieMapper> getMovies(int page) {

        final Pageable pageable = PageRequest.of(page - 1, 20);

        return movieRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public void updateMovie(Long id, UpdateMovieRequestDto requestDto) {

        final Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 영화는 존재하지 않습니다."));

        if (requestDto.getTitle() != null) {
            existingMovie.updateTitle(requestDto.getTitle());
        }

        if (requestDto.getDescription() != null) {
            existingMovie.updateDescription(requestDto.getDescription());
        }

        if (requestDto.getGenre() != null) {
            existingMovie.updateGenre(requestDto.getGenre());
        }

        if (requestDto.getDuration() != null){
            existingMovie.updateDuration(requestDto.getDuration());
        }

        movieRepository.save(existingMovie);
    }

    public void uploadPoster(Long id, MultipartFile file) throws IOException {

        final Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 영화는 존재하지 않습니다."));

        final String poster = s3Service.s3Upload(file);

        movie.updatePoster(poster);
        movieRepository.save(movie);
    }
}
