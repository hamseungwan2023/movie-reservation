package com.movie.reservation.domain.cinema.repository;

import com.movie.reservation.domain.cinema.entity.ScreenTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenTimeRepository extends JpaRepository<ScreenTime, Long> {
}
