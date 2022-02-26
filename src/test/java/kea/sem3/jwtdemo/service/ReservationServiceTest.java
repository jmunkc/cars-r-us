package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.ReservationRequest;
import kea.sem3.jwtdemo.dto.ReservationResponse;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.entity.Reservation;
import kea.sem3.jwtdemo.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    ReservationRepository reservationRepository;

    ReservationService reservationService;

    @BeforeEach
    void setUp() {
        reservationService = new ReservationService((reservationRepository));
        }

    @Test
    void getReservations() {
        Mockito.when(reservationRepository.findAll()).thenReturn(List.of(
                new Reservation(1, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(),
                        new Car("Volvo","V70",100,10),
                        new Member("a", "a@mail.dk", "aaa", "af", "al", "avej", "aby", "2000")),
                new Reservation(2, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(),
                        new Car("WV","Polo",100,10),
                        new Member("b", "b@mail.dk", "bbb", "bf", "bl", "bvej", "bby", "2000"))
        ));
        List<ReservationResponse> reservations = reservationService.getReservations();
        assertEquals(2, reservations.size());
    }

    @Test
    void getReservation() {
        Reservation res = new Reservation(1, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(),
                new Car("Volvo","V70",100,10),
                new Member("a", "a@mail.dk", "aaa", "af", "al", "avej", "aby", "2000"));
        Mockito.when(reservationRepository.findById(1)).thenReturn(Optional.of(res));
        ReservationResponse resRes = reservationService.getReservation(1);
        assertEquals("Volvo", resRes.getCar().getBrand());

    }

    @Test
    void addReservation() {
        Reservation resWithId = new Reservation(1, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(),
                new Car("Volvo","V70",100,10),
                new Member("a", "a@mail.dk", "aaa", "af", "al", "avej", "aby", "2000"));
        resWithId.setId(1000);
        Mockito.when(reservationRepository.save(any(Reservation.class))).thenReturn(resWithId);
        ReservationResponse resResp = reservationService.addReservation(new ReservationRequest(resWithId.getId(), resWithId.getReservationDate(), resWithId.getRentalDate(), resWithId.getCar(), resWithId.getMember()));
        assertEquals(1000, resResp.getId());
    }

    @Test
    void editReservation() {
    }

    @Test
    void deleteReservation() {
    }
}