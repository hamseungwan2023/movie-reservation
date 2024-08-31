package com.movie.reservation.domain.cinema.service;

import com.movie.reservation.domain.cinema.dto.request.CinemaRequestDto;
import com.movie.reservation.domain.cinema.dto.request.UpdateCinemaRequestDto;
import com.movie.reservation.domain.cinema.dto.response.CinemaResponseDto;
import com.movie.reservation.domain.cinema.entity.Cinema;
import com.movie.reservation.domain.cinema.repository.CinemaRepository;
import com.movie.reservation.domain.cinema.repository.mapper.CinemaMapper;
import com.movie.reservation.global.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public void CreateCinema(CinemaRequestDto requestDto) {

        final Cinema cinema = Cinema.builder()
                .name(requestDto.getName())
                .address(requestDto.getAddress())
                .sido(requestDto.getSido())
                .gungu(requestDto.getGungu())
                .build();

        cinemaRepository.save(cinema);
    }

    public CinemaResponseDto getCinema(Long id) {

        final Cinema cinema = findCinema(id);

        return CinemaResponseDto.builder()
                .name(cinema.getName())
                .address(cinema.getAddress())
                .sido(cinema.getSido())
                .gungu(cinema.getGungu())
                .build();
    }

    public Page<CinemaMapper> getCinemas(int page) {

        final Pageable pageable = PageRequest.of(page - 1, 20);

        return cinemaRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public void updateCinema(Long id, UpdateCinemaRequestDto requestDto) {

        final Cinema existingCinema = findCinema(id);

        if (requestDto.getName() != null) {
            existingCinema.updateName(requestDto.getName());
        }

        if (requestDto.getAddress() != null) {
            existingCinema.updateAddress(requestDto.getAddress());
        }

        if (requestDto.getSido() != null) {
            existingCinema.updateSido(requestDto.getSido());
        }

        if (requestDto.getGungu() != null) {
            existingCinema.updateGungu(requestDto.getGungu());
        }

        cinemaRepository.save(existingCinema);
    }

    public Page<CinemaMapper> searchBySido(String sido, int page) {

        Pageable pageable = PageRequest.of(page - 1, 20);

        return cinemaRepository.searchBySido(sido, pageable);
    }

    public Page<CinemaMapper> searchBySidoAndGungu(String sido, String gungu, int page) {

        Pageable pageable = PageRequest.of(page - 1, 20);

        return cinemaRepository.searchBySidoAndGungu(sido, gungu, pageable);
    }

    public Cinema findCinema(Long id) {
        return cinemaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 영화관은 존재하지 않습니다."));
    }
}
