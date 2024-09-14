//package com.movie.reservation.global.service;
//
//import com.movie.reservation.domain.cinema.entity.Cinema;
//import com.movie.reservation.domain.cinema.entity.Screen;
//import com.movie.reservation.domain.cinema.entity.ScreenTime;
//import com.movie.reservation.domain.cinema.repository.CinemaRepository;
//import com.movie.reservation.domain.cinema.repository.ScreenRepository;
//import com.movie.reservation.domain.cinema.repository.ScreenTimeRepository;
//import com.movie.reservation.domain.cinema.service.CinemaService;
//import com.movie.reservation.domain.cinema.service.ScreenService;
//import com.movie.reservation.domain.movie.entity.Movie;
//import com.movie.reservation.domain.movie.service.MovieService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Calendar;
//import java.util.Date;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    private final CinemaService cinemaService;
//    private final ScreenTimeRepository screenTimeRepository;
//    private final ScreenService screenService;
//    private final MovieService movieService;
//
//    public DataLoader(CinemaService cinemaService, ScreenTimeRepository screenTimeRepository, ScreenService screenService, MovieService movieService) {
//        this.cinemaService = cinemaService;
//        this.screenService = screenService;
//        this.screenTimeRepository = screenTimeRepository;
//        this.movieService = movieService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Movie movie = movieService.findMovie(1L);
//        Screen screen = screenService.findScreen(1L);
//        Cinema cinema = cinemaService.findCinema(1L);
//        for (long i = 1L; i <= 1000000; i++) {
//
//            final Date startTime = new Date();
//            final Integer runningTime = movie.getDuration();
//            final Calendar calendar = Calendar.getInstance();
//            calendar.setTime(startTime);
//            calendar.add(Calendar.MINUTE, runningTime);
//            final Date endTime = calendar.getTime();
//
//            // Create a SimpleDateFormat instance with the desired format
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
//
//            // Format the Date objects to Strings
//            String formattedStartTime = sdf.format(startTime);
//            String formattedEndTime = sdf.format(endTime);
//
//            final ScreenTime screenTime = ScreenTime.builder()
//                    .startTime(formattedStartTime)
//                    .endTime(formattedEndTime)
//                    .screen(screen)
//                    .movie(movie)
//                    .build();
//
//            screenTimeRepository.save(screenTime);
//        }
//    }
//}
