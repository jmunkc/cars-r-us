package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("select (count(r) > 0) from Reservation r where r.car = :car")
    boolean carExist(Car car);

    @Query("select (count(r) > 0) from Reservation r where r.rentalDate = :rental_date")
    boolean rentalDateExist(LocalDateTime rentalDate);
}
