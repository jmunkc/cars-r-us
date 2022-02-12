package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.repositories.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CarServiceMockitoTest {

    @Mock
    CarRepository carRepository;

    //@Autowired
    CarService carService;

    @BeforeEach
    void setUp() {
        carService = new CarService(carRepository);

    }

    @Test
    void testGetCars() {
        Mockito.when(carRepository.findAll()).thenReturn(List.of(
                new Car("Volvo","V70",100,10),
                new Car("WV","Polo",100,10)
        ));
        List<CarResponse> cars = carService.getCars();
        assertEquals(2,cars.size());
    }

    @Test
    void testGetCar() throws Exception {
        Car car = new Car("Volvo","V70",100,10);
        car.setId(1000);
        Mockito.when(carRepository.findById(1000)).thenReturn(Optional.of(car));
        CarResponse carRes = carService.getCar(1000,false);
        assertEquals("Volvo",carRes.getBrand());
    }

    @Test
    void addCar() {
        Car carWithId = new Car("Volvo","V70",100,100);
        carWithId.setId(1000);
        Mockito.when(carRepository.save(any(Car.class))).thenReturn(carWithId);
        CarResponse res = carService.addCar(new CarRequest(carWithId.getBrand(),carWithId.getModel(),carWithId.getPricePrDay(),10));
        assertEquals(1000,res.getId());
    }

}
