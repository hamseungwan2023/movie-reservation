//package com.movie.reservation;
//
//import com.movie.reservation.domain.cinema.entity.Cinema;
//import com.movie.reservation.domain.cinema.entity.Screen;
//import com.movie.reservation.domain.cinema.entity.ScreenTime;
//import com.movie.reservation.domain.cinema.entity.Seat;
//import com.movie.reservation.domain.cinema.service.ScreenTimeService;
//import com.movie.reservation.domain.cinema.service.SeatService;
//import com.movie.reservation.domain.movie.entity.Genre;
//import com.movie.reservation.domain.movie.entity.Movie;
//import com.movie.reservation.domain.reservation.entity.Reservation;
//import com.movie.reservation.domain.reservation.entity.ReservationStatus;
//import com.movie.reservation.domain.reservation.repository.ReservationRepository;
//import com.movie.reservation.domain.reservation.service.ReservationService;
//import com.movie.reservation.domain.user.entity.IsWithDraw;
//import com.movie.reservation.domain.user.entity.User;
//import com.movie.reservation.domain.user.entity.UserRoleEnum;
//import com.movie.reservation.domain.user.repository.UserRepository;
//import com.movie.reservation.domain.user.service.UserService;
//import com.movie.reservation.global.exception.BadRequestException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.test.annotation.Commit;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.stream.IntStream;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest(classes = ReservationApplication.class)
//public class ReservationTest {
//
//    @Autowired
//    private ReservationService reservationService; // 실제 빈을 사용
//
//    @MockBean
//    private SimpMessagingTemplate simpMessagingTemplate; // 모킹된 빈
//
//    @MockBean
//    private ScreenTimeService screenTimeService; // 모킹된 빈
//
//    @MockBean
//    private SeatService seatService; // 모킹된 빈
//
//    @MockBean
//    private UserService userService; // 모킹된 빈
//
//    @MockBean
//    private ReservationRepository reservationRepository; // 모킹된 빈
//
//    @MockBean
//    private UserRepository userRepository;
//
//
//    private User[] users;
//    @BeforeEach
//    void setUp() {
//        users = new User[10000];  // 3명의 사용자를 만들고자 할 때 크기를 3으로 설정
//        for (int i = 0; i < users.length; i++) {
//            users[i] = User.builder()
//                    .username("user" + (i + 1))
//                    .password("password")
//                    .phone("010-1111-1111")
//                    .email("user" + i + "@email.com")
//                    .role(UserRoleEnum.USER)
//                    .isWithDraw(IsWithDraw.ACTIVE)
//                    .build();
//        }
//    }
//
//    @Test
//    @Commit
//    @DisplayName("동시성 제어 없음 : 좋아요 동시에 1000명이 눌렀을 때")
//    void concurrencyTestWithoutLock() throws InterruptedException {
//        int userCount = 10000;
//        CountDownLatch latch = new CountDownLatch(userCount);
//        AtomicInteger successfulReservations = new AtomicInteger(0);
//        AtomicInteger failedReservations = new AtomicInteger(0);
//
//        ExecutorService executorService = Executors.newFixedThreadPool(50);
//        List<User> userList = List.of(users);
//
//        Cinema cinema = Cinema.builder()
//                .id(1L)
//                .address("fdsa")
//                .sido("fdas")
//                .gungu("asf")
//                .name("fdsafa")
//                .build();
//
//        Screen screen = Screen.builder()
//                .id(1L)
//                .cinema(cinema)
//                .totalSeat(1)
//                .name("dsaf")
//                .build();
//
//        Movie movie = Movie.builder()
//                .id(1L)
//                .duration(123)
//                .genre(Genre.ACTION)
//                .description("fdsa")
//                .title("fdsa")
//                .build();
//
//        ScreenTime screenTime = ScreenTime.builder()
//                .id(1L)
//                .screen(screen)
//                .movie(movie)
//                .startTime("2024-08-31-19:00")
//                .endTime("2024-08-31-21:03")
//                .build();
//
//        Seat seat = Seat.builder()
//                .id(1L)
//                .seatNumber(1)
//                .screen(screen)
//                .build();
//
//        IntStream.range(0, userCount).forEach(i -> {
//            executorService.submit(() -> {
//                try {
//                    User user = userList.get(i);
//                    try {
//                        reservationService.reserveSeat(screen.getId(), seat.getSeatNumber(), screenTime.getId(), user.getUsername());
//                        successfulReservations.incrementAndGet();
//                    } catch (BadRequestException e) {
//                        failedReservations.incrementAndGet();
//                    }
//                } finally {
//                    latch.countDown();
//                }
//            });
//        });
//
//        latch.await(120, TimeUnit.SECONDS);
//        executorService.shutdown();
//        executorService.awaitTermination(2, TimeUnit.MINUTES);
//
//        // 결과 검증
//        assertEquals(1, successfulReservations.get(), "좌석은 하나만 예약되어야 합니다.");
//        assertEquals(999, failedReservations.get(), "999명의 사용자가 예약에 실패해야 합니다.");
//    }
//
//    @Test
//    @DisplayName("예약 단건 테스트")
//    void reservationTest() {
//
//    }
//}
