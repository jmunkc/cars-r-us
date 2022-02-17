package kea.sem3.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {
    private int id;

    private Car car;
    private Member renter;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime reservationDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime rentalDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime editedReservationDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime editedRentalDate;

    public ReservationResponse(Reservation reservation, boolean includeAll){
        this.id = reservation.getId();
        this.car = reservation.getCar();
        this.renter = reservation.getMember();
        this.reservationDate = reservation.getReservationDate();
        this.rentalDate = reservation.getRentalDate();
        if(includeAll){
            this.editedReservationDate = reservation.getEditedReservationDate();
            this.editedRentalDate = reservation.getEditedRentalDate();
        }
    }

    public static List<ReservationResponse> getReservationsFromEntities(List<Reservation> reservations){
        return reservations.stream().map(reservation -> new ReservationResponse(reservation, false)).collect(Collectors.toList());
    }
}
