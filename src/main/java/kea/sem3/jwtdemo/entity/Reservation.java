package kea.sem3.jwtdemo.entity;

import kea.sem3.jwtdemo.dto.ReservationRequest;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Reservation")
@Table
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private LocalDateTime reservationDate;

    @CreationTimestamp
    private LocalDateTime rentalDate;

    @UpdateTimestamp
    private LocalDateTime editedReservationDate;

    @UpdateTimestamp
    private LocalDateTime editedRentalDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "username")
    private Member member;

    public Reservation(ReservationRequest body) {
        this.id = body.getId();
        this.reservationDate = body.getReservationDate();
        this.rentalDate = body.getRentalDate();
        this.car = body.getCar();
        this.member = body.getRenter();
    }
}
