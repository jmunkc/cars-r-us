package kea.sem3.jwtdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.MemberRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends BaseUser {

    //Make sure you understand why there is no @Id annotation in this class, and the SINGLE table created in the database

    @Column(length = 40)
    String firstName;

    @Column(length = 60)
    String lastName;

    @Column(length = 60)
    String street;

    @Column(length = 30)
    String city;

    @Column(length = 4)
    String zip;

    @CreationTimestamp
    LocalDateTime created;

    @UpdateTimestamp
    LocalDateTime edited;

    Boolean isApproved;

    //Number between 0 and 10, ranking the customer
    byte ranking;

    @JsonIgnore
    @OneToMany(
            mappedBy = "member",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Reservation> reservations = new HashSet<>();

    public Member(String username, String email, String password, String firstName, String lastName, String street, String city, String zip) {
        super(username, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        ranking = 5; //Initial ranking
        isApproved = false;
    }

    public Member(MemberRequest body) {
        this.firstName = body.getFirstName();
        this.lastName = body.getLastName();
        this.street = body.getStreet();
        this.city = body.getCity();
        this.zip = body.getZip();
    }

}
