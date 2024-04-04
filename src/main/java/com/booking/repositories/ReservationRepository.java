package com.booking.repositories;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.booking.models.Customer;
import com.booking.models.Membership;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class ReservationRepository {
    private static List<Reservation> reservations = new ArrayList<>();

    public static List<Reservation> getAllReservation() {
        List<Reservation> listReservation = new ArrayList<>();

        List<Customer> customers = PersonRepository.getAllCustomers();
        List<Service> services = ServiceRepository.getAllService();

        Reservation reservation1 = Reservation.builder()
                .reservationId("Rsv-01")
                .customer(customers.get(2))
                .services(Arrays.asList(services.get(3)))
                .reservationPrice(125_000)
                .workstage("In Process")
                .build();

        Reservation reservation2 = Reservation.builder()
                .reservationId("Rsv-02")
                .customer(customers.get(1))
                .services(Arrays.asList(services.get(3), services.get(0)))
                .reservationPrice(125_000)
                .workstage("In Process")
                .build();

        listReservation.addAll(Arrays.asList(reservation1, reservation2));

        return listReservation;
    }

    public static List<Reservation> getAllReservations() {
        return getAllReservation().stream()
                .filter(reservation -> reservation instanceof Reservation)
                .map(reservation -> (Reservation) reservation)
                .collect(Collectors.toList());
    }
}
