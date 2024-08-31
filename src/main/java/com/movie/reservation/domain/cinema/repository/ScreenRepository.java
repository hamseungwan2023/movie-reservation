package com.movie.reservation.domain.cinema.repository;

import com.movie.reservation.domain.cinema.entity.Screen;
import com.movie.reservation.domain.cinema.repository.mapper.ScreenMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {

    @Query("SELECT s FROM Screen s WHERE s.cinema.id = :cinemaId")
    Page<ScreenMapper> findAllByCinemaIdOrderByCreatedAtDesc(@Param("cinemaId") Long cinemaId, Pageable pageable);
}
