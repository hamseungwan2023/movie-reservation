package com.movie.reservation.domain.cinema.repository;

import com.movie.reservation.domain.cinema.entity.Cinema;
import com.movie.reservation.domain.cinema.repository.mapper.CinemaMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    Page<CinemaMapper> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query("SELECT c FROM Cinema c WHERE c.sido = :sido")
    Page<CinemaMapper> searchBySido(@Param("sido") String sido, Pageable pageable);

    @Query("SELECT c FROM Cinema c WHERE c.sido = :sido AND c.gungu = :gungu")
    Page<CinemaMapper> searchBySidoAndGungu(@Param("sido") String sido, @Param("gungu") String gungu, Pageable pageable);
}
