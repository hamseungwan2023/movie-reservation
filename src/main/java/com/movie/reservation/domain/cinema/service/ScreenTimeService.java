package com.movie.reservation.domain.cinema.service;

import com.movie.reservation.domain.cinema.repository.ScreenTimeRepository;
import org.springframework.stereotype.Service;

@Service
public class ScreenTimeService {

    private final ScreenTimeRepository screenTimeRepository;

    public ScreenTimeService(ScreenTimeRepository screenTimeRepository) {
        this.screenTimeRepository = screenTimeRepository;
    }

//    public void
}
