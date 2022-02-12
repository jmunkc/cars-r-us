package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.repositories.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarServiceInMemoryTest
{
    @Autowired
    CarRepository carRepository;

    CarService carService;

    static int car1Id, car2Id;

    @BeforeAll
    static void setup(@Autowired CarRepository carRepository){
        carRepository.deleteAll();
        car1Id = carRepository.save(new Car("Volvo", "C40", 560,10)).getId();
        car2Id = carRepository.save(new Car("WW", "Up", 300,10)).getId();

    }

    @BeforeEach
    void setUpCarService(){
        carService = new CarService(carRepository);
    }

    @Test
    void getCars() {
        List<CarResponse> carResponses = carService.getCars();
        assertEquals(2,carResponses.size());
        assertInstanceOf(CarResponse.class,carResponses.get(0));
        assertThat(carResponses, containsInAnyOrder(hasProperty("model", is("C40")), hasProperty("model", is("Up"))));
    }

    @Test
    void getCar() {
    }

    @Test
    void addCar() {
    }

    @Test
    void deleteCar() {
    }
}
