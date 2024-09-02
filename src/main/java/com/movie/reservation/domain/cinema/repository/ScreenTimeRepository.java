package com.movie.reservation.domain.cinema.repository;

import com.movie.reservation.domain.cinema.entity.ScreenTime;
import com.movie.reservation.domain.cinema.repository.mapper.ScreenTimeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenTimeRepository extends JpaRepository<ScreenTime, Long>, ScreenTimeCustomRepository {
}
