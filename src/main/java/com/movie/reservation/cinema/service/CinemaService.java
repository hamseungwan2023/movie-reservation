package com.movie.reservation.cinema.service;

import com.movie.reservation.cinema.Cinema;
import com.movie.reservation.cinema.CinemaRequest;
import com.movie.reservation.cinema.mapper.CinemaMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {
    private final CinemaMapper cinemaMapper;

    public CinemaService(CinemaMapper cinemaMapper) {
        this.cinemaMapper = cinemaMapper;
    }

    public Cinema create(CinemaRequest request) {
        Cinema cinema = new Cinema();
        cinema.setName(request.name());
        cinema.setAddress(request.address());
        cinema.setSido(request.sido());
        cinema.setGungu(request.gungu());
        cinemaMapper.insert(cinema);
        return cinema;
    }

    public Cinema get(Long id) {
        Cinema cinema = cinemaMapper.findById(id);
        if (cinema == null) throw new IllegalArgumentException("영화관이 존재하지 않습니다.");
        return cinema;
    }

    public List<Cinema> getAll() { return cinemaMapper.findAll(); }

    public Cinema update(Long id, CinemaRequest request) {
        Cinema cinema = get(id);
        cinema.setName(request.name());
        cinema.setAddress(request.address());
        cinema.setSido(request.sido());
        cinema.setGungu(request.gungu());
        cinemaMapper.update(cinema);
        return get(id);
    }

    public void delete(Long id) { cinemaMapper.delete(id); }
}
