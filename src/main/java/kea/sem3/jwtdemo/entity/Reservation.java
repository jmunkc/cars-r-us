package kea.sem3.jwtdemo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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
    private LocalDateTime updateReservationDate;

    @UpdateTimestamp
    private LocalDateTime updateRentalDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private Member member;
}
