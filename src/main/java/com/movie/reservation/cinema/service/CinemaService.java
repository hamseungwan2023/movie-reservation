package com.movie.reservation.cinema.service;

import com.movie.reservation.cinema.Cinema;
import com.movie.reservation.cinema.CinemaRequest;
import com.movie.reservation.cinema.mapper.CinemaMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CinemaService {
    private final CinemaMapper cinemaMapper;

    public CinemaService(CinemaMapper cinemaMapper) {
        this.cinemaMapper = cinemaMapper;
    }

    @Transactional
    public Cinema create(CinemaRequest request) {
        Cinema cinema = Cinema.builder()
                .name(request.name())
                .address(request.address())
                .sido(request.sido())
                .gungu(request.gungu())
                .build();
        cinemaMapper.insert(cinema);
        return cinema;
    }

    public Cinema get(Long id) {
        Cinema cinema = cinemaMapper.findById(id);
        if (cinema == null) throw new IllegalArgumentException("영화관이 존재하지 않습니다.");
        return cinema;
    }

    public List<Cinema> getAll() { return cinemaMapper.findAll(); }

    @Transactional
    public Cinema update(Long id, CinemaRequest request) {
        get(id);
        Cinema cinema = Cinema.builder()
                .id(id)
                .name(request.name())
                .address(request.address())
                .sido(request.sido())
                .gungu(request.gungu())
                .build();
        cinemaMapper.update(cinema);
        return get(id);
    }

    @Transactional
    public void delete(Long id) { cinemaMapper.delete(id); }
}
