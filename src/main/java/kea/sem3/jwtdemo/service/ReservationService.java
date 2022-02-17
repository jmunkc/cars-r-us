package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.ReservationRequest;
import kea.sem3.jwtdemo.dto.ReservationResponse;
import kea.sem3.jwtdemo.entity.Reservation;
import kea.sem3.jwtdemo.error.Client4xxException;
import kea.sem3.jwtdemo.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationResponse> getReservations(){
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream().map(reservation -> new ReservationResponse(reservation, false)).collect(Collectors.toList());
    }

    public ReservationResponse getReservation(int id){
        Reservation res = reservationRepository.findById(id).orElseThrow(()->new Client4xxException("No reservation with this id found"));
        return new ReservationResponse(res, false);
    }


    public ReservationResponse addReservation(ReservationRequest body){
        if(reservationRepository.carExist(body.getCar()) && reservationRepository.rentalDateExist(body.getRentalDate())){
            throw new Client4xxException("This car is already rented out on the specified date");
        }
        Reservation resNew = reservationRepository.save((new Reservation(body)));
        return  new ReservationResponse(resNew, false);
    }

    public ReservationResponse editReservation(ReservationRequest body, int id){
        if(!(reservationRepository.existsById(id))){
            throw new Client4xxException("No reservation with that id exists");
        }
        Reservation resToEdit = new Reservation(body);
        resToEdit.setId(id);
        reservationRepository.save(resToEdit);
        return  new ReservationResponse(resToEdit, true);
    }

    public void deleteReservation(int id){
        if(!(reservationRepository.existsById(id))){
            throw new Client4xxException("No reservation with that id exists");
        }
        reservationRepository.deleteById(id); }

}
