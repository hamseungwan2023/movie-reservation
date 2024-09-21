package com.movie.reservation;

import com.movie.reservation.domain.cinema.entity.Cinema;
import com.movie.reservation.domain.cinema.entity.Screen;
import com.movie.reservation.domain.cinema.entity.ScreenTime;
import com.movie.reservation.domain.cinema.entity.Seat;
import com.movie.reservation.domain.cinema.service.ScreenTimeService;
import com.movie.reservation.domain.cinema.service.SeatService;
import com.movie.reservation.domain.movie.entity.Genre;
import com.movie.reservation.domain.movie.entity.Movie;
import com.movie.reservation.domain.reservation.entity.Reservation;
import com.movie.reservation.domain.reservation.entity.ReservationStatus;
import com.movie.reservation.domain.reservation.repository.ReservationRepository;
import com.movie.reservation.domain.reservation.service.ReservationService;
import com.movie.reservation.domain.user.entity.IsWithDraw;
import com.movie.reservation.domain.user.entity.User;
import com.movie.reservation.domain.user.entity.UserRoleEnum;
import com.movie.reservation.domain.user.service.UserService;
import com.movie.reservation.global.exception.BadRequestException;
import com.movie.reservation.global.exception.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private RedissonClient redissonClient;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ScreenTimeService screenTimeService;

    @Mock
    private SeatService seatService;

    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;

    @InjectMocks
    private ReservationService reservationService;

    private RLock lock;


    Cinema cinema = Cinema.builder()
            .id(1L)
            .address("fdsa")
            .sido("fdas")
            .gungu("asf")
            .name("fdsafa")
            .build();

    Screen screen = Screen.builder()
            .id(1L)
            .cinema(cinema)
            .totalSeat(1)
            .name("dsaf")
            .build();

    Movie movie = Movie.builder()
            .id(1L)
            .duration(123)
            .genre(Genre.ACTION)
            .description("fdsa")
            .title("fdsa")
            .build();

    ScreenTime screenTime = ScreenTime.builder()
            .id(1L)
            .screen(screen)
            .movie(movie)
            .startTime("2024-08-31-19:00")
            .endTime("2024-08-31-21:03")
            .build();

    Seat seat = Seat.builder()
            .id(1L)
            .seatNumber(1)
            .screen(screen)
            .build();

    User user = User.builder()
            .username("user")
            .password("password")
            .phone("010-1111-1111")
            .email("user" + "@email.com")
            .role(UserRoleEnum.USER)
            .isWithDraw(IsWithDraw.ACTIVE)
            .build();

    private boolean firstLockAttempt = true; // 첫 번째 락 시도 여부 확인을 위한 플래그


    @BeforeEach
    public void setUp() throws InterruptedException {
        MockitoAnnotations.openMocks(this);
        reservationRepository.save(Reservation.builder()
                .id(1L)
                .reservationStatus(ReservationStatus.CANCELLED)
                .user(user)
                .seat(seat)
                .screenTime(screenTime)
                .build());

//        lock = mock(RLock.class);
//        when(redissonClient.getLock(anyString())).thenReturn(lock);
//
//        doAnswer(new Answer<Boolean>() {
//            @Override
//            public Boolean answer(InvocationOnMock invocation) throws Throwable {
//                if (firstLockAttempt) {
//                    firstLockAttempt = false;
//                    return true; // 첫 번째 락 시도는 성공
//                }
//                return false; // 이후 락 시도는 실패
//            }
//        }).when(lock).tryLock(5, 10, TimeUnit.SECONDS);
    }
    @AfterEach
    public void tearDown() {
        reservationRepository.deleteAll();
    }

    @Test
    @DisplayName("예약 요청")
    void reservation() {
        // given
        Mockito.when(reservationRepository.findById(1L)).thenReturn(Optional.of(Reservation.builder()
                .id(1L)
                .reservationStatus(ReservationStatus.CANCELLED)
                .user(user)
                .seat(seat)
                .screenTime(screenTime)
                .build()));

        // when
        reservationService.reserveSeat(1L, 1, 1L, "user");

        // then
        final Reservation reservation = reservationRepository.findById(1L)
                .orElseThrow(() -> new NotFoundException("해당 예약은 존재하지 않습니다."));
        reservation.updateReservation(ReservationStatus.CONFIRMED, user);
        assertEquals(ReservationStatus.CONFIRMED, reservation.getReservationStatus());
        assertEquals("user", reservation.getUser().getUsername());
    }

    private final ConcurrentHashMap<Integer, Boolean> requestStatus = new ConcurrentHashMap<>();

    @Test
    @DisplayName("대량 예약 요청 처리")
    void bulkReservationTest() {
        int numberOfRequests = 100;
        AtomicBoolean firstRequestSuccess = new AtomicBoolean(true);

        // Mock 데이터 설정
        Reservation initialReservation = Reservation.builder()
                .id(1L)
                .reservationStatus(ReservationStatus.CANCELLED)
                .user(user)
                .seat(seat)
                .screenTime(screenTime)
                .build();

        AtomicInteger a = new AtomicInteger();
        AtomicInteger b = new AtomicInteger();
        Mockito.when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(initialReservation));
        Mockito.when(reservationRepository.save(Mockito.any(Reservation.class))).thenAnswer(invocation -> {
            Reservation savedReservation = invocation.getArgument(0);
            if (firstRequestSuccess.get()) {
                savedReservation.updateReservation(ReservationStatus.CONFIRMED, user);
                a.getAndIncrement();
                firstRequestSuccess.set(false); // Only the first request should succeed
            } else {
                b.getAndIncrement();
                savedReservation.updateReservation(ReservationStatus.CANCELLED, user);
            }
            System.out.println(a.get());
            System.out.println(b.get());
            return savedReservation;
        });

        // when
        IntStream.range(0, numberOfRequests).parallel().forEach(i -> {
            try {
                reservationService.reserveSeat(screen.getId(), seat.getSeatNumber(), screenTime.getId(), user.getUsername());
            } catch (Exception e) {
                // Handle exceptions if needed
            }
        });

        // then
        // Check all reservations
        long confirmedCount = reservationRepository.findAll().stream()
                .filter(reservation -> reservation.getReservationStatus() == ReservationStatus.CONFIRMED)
                .count();

        long cancelledCount = reservationRepository.findAll().stream()
                .filter(reservation -> reservation.getReservationStatus() == ReservationStatus.CANCELLED)
                .count();

        System.out.println("확인된 예약 수: " + confirmedCount);
        System.out.println("취소된 예약 수: " + cancelledCount);

        assertEquals(1, confirmedCount, "단 한 명만 성공해야 합니다.");
        assertEquals(numberOfRequests - 1, cancelledCount, "그 외의 예약은 모두 취소되어야 합니다.");

        // verify save method was called
        Mockito.verify(reservationRepository, times(numberOfRequests)).save(Mockito.any(Reservation.class));
    }

    @Test
    public void testConcurrentSeatReservation() throws InterruptedException {
        int numberOfThreads = 10;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            String username = "user" + i;
            executor.submit(() -> {
                try {
                    reservationService.reserveSeat(1L, 1, 1L, username);
                    System.out.println("예약 성공한 유저: " + username);
                } catch (Exception e) {
                    System.out.println("예약 실패한 유저: " + username + ", 이유: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();
    }


}
