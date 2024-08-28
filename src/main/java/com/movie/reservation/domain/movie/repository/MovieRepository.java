package com.movie.reservation.domain.movie.repository;

import com.movie.reservation.domain.movie.entity.Movie;
import com.movie.reservation.domain.movie.repository.mapper.MovieMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Page<MovieMapper> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
