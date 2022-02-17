package kea.sem3.jwtdemo.dto;

import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
    private int id;
    private LocalDateTime reservationDate;
    private LocalDateTime rentalDate;
    private Car car;
    private Member renter;
}
