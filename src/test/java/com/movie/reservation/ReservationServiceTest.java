package com.movie.reservation;

import com.movie.reservation.domain.cinema.entity.ScreenTime;
import com.movie.reservation.domain.cinema.entity.Seat;
import com.movie.reservation.domain.cinema.service.ScreenTimeService;
import com.movie.reservation.domain.cinema.service.SeatService;
import com.movie.reservation.domain.reservation.entity.Reservation;
import com.movie.reservation.domain.reservation.repository.ReservationRepository;
import com.movie.reservation.domain.reservation.service.ReservationService;
import com.movie.reservation.domain.user.entity.User;
import com.movie.reservation.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.mockito.Mockito.*;

class ReservationServiceTest {

    @Mock
    private ScreenTimeService screenTimeService;

    @Mock
    private SeatService seatService;

    @Mock
    private UserService userService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReserveSeat() {
        // Given
        Long seatId = 1L;
        int seatNumber = 10;
        Long screenTimeId = 1L;
        String username = "user1";

        ScreenTime mockScreenTime = new ScreenTime();
        Seat mockSeat = new Seat();
        User mockUser = new User();

        when(screenTimeService.findScreenTime(screenTimeId)).thenReturn(mockScreenTime);
        when(seatService.findSeat(seatId, seatNumber)).thenReturn(mockSeat);
        when(userService.findUser(username)).thenReturn(mockUser);

        // When
        reservationService.reserveSeat(seatId, seatNumber, screenTimeId, username);

        // Then
        verify(reservationRepository, times(1)).save(any(Reservation.class));
        verify(simpMessagingTemplate, times(1)).convertAndSend(eq("/topic/seats/" + screenTimeId), any(Reservation.class));
    }
}
