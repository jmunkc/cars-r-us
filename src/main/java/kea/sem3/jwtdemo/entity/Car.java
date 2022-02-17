package kea.sem3.jwtdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kea.sem3.jwtdemo.dto.CarRequest;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String brand;

    @Column(length = 60)
    String model;

    double pricePrDay;

    //Best discount price (percent fo pricePrDay) an admin can offer
    double bestDiscount;

    @CreationTimestamp
    LocalDateTime created;

    @UpdateTimestamp
    LocalDateTime edited;

    @JsonIgnore
    @OneToMany(
            mappedBy = "car",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Reservation> reservations = new HashSet<>();


    public Car(String brand, String model, double pricePrDay,double discount) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
        this.bestDiscount = discount;
    }
    public Car(CarRequest body) {
        this.brand = body.getBrand();
        this.model = body.getModel();
        this.pricePrDay = body.getPricePrDay();
        this.bestDiscount = body.getBestDiscount();
    }



    }
