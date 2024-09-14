package com.movie.reservation.domain.cinema.repository;

import com.movie.reservation.domain.cinema.dto.response.ScreenTimeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScreenTimeCustomRepository {
    Page<ScreenTimeResponseDto> searchScreenTImeByMovie(Long movieId, Pageable pageable);

    Page<ScreenTimeResponseDto> searchScreenTImeByStartTime(Long movieId, Long screenId, String time, Pageable pageable);
}
