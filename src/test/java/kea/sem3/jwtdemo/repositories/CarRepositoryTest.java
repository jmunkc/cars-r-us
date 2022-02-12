package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    //Store som id's for the test methods
    static int carRav4, carPolo;

    @BeforeAll
    static void setUp(@Autowired CarRepository carRepository) {
        carRepository.deleteAll();
        carRav4 = carRepository.save(new Car("Toyota","RAV4",900,10)).getId();
        carPolo = carRepository.save(new Car("WW","Polo",500,10)).getId();
    }

    @Test
    void findCarByID() {
        Car rav4 = carRepository.findById(carRav4).orElse(null);
        assertEquals("RAV4",rav4.getModel());
        //assertEquals("RAV",rav4.getModel());

    }
}