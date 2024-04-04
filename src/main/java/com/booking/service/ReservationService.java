package com.booking.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.ReservationRepository;
import com.booking.repositories.ServiceRepository;

public class ReservationService {
    static Scanner input = new Scanner(System.in);

    public static void createReservation() {
        DecimalFormat currencyFormatter = new DecimalFormat("Rp#,##0");

        // Customer
        PrintService.showAllCustomer();
        System.out.println("Silahkan Masukkan Customer ID: ");
        String customerId = input.nextLine();
        Customer customer = getCustomerByCustomerId(customerId);
        if (customer == null) {
            System.out.println("Customer ID " + customerId + " tidak ditemukan.");
            return;
        }

        // Employee
        PrintService.showAllEmployee();
        System.out.println("Silahkan Masukkan Employee ID: ");
        String employeeId = input.nextLine();
        Employee employee = getEmployeeByEmployeeId(employeeId);
        if (employee == null) {
            System.out.println("Employee ID " + employeeId + " tidak ditemukan.");
            return;
        }

        // Service
        List<Service> selectedServices = new ArrayList<>();
        boolean addMoreServices = true;
        while (addMoreServices) {
            PrintService.printServices();
            System.out.println("Silahkan Masukkan Service ID: ");
            String serviceId = input.nextLine();
            Service service = getServiceByServiceId(serviceId);
            if (service == null) {
                System.out.println("Service ID " + serviceId + " tidak ditemukan.");
            } else {
                selectedServices.add(service);
                System.out.println("Service '" + service.getServiceName() + "' ditambahkan.");
                System.out.println("Ingin pilih service yang lain? (Y/T)?");
                String choice = input.nextLine();
                if (!choice.equalsIgnoreCase("Y")) {
                    addMoreServices = false;
                }
            }
        }

        // Print Booking Berhasil
        System.out.println("Booking Berhasil!");
        double totalPrice = 0;
        for (Service service : selectedServices) {
            totalPrice += service.getPrice();
        }
        System.out.println("Total Biaya Booking: " + currencyFormatter.format(totalPrice));

        List<Reservation> listReservation = new ArrayList<>();
        Reservation reservation = Reservation.builder()
                .reservationId("Rsv - " + listReservation.size() + 1)
                .customer(customer)
                .employee(employee)
                .services(selectedServices)
                .workstage("In Process")
                .reservationPrice(totalPrice)
                .build();
        listReservation.add(reservation);
    }

    public static Customer getCustomerByCustomerId(String customerId) {
        List<Customer> customers = PrintService.showAllCustomer();
        for (Customer customer : customers) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    public static Employee getEmployeeByEmployeeId(String employeeId) {
        List<Employee> employees = PrintService.showAllEmployee();
        for (Employee employee : employees) {
            if (employee.getId().equals(employeeId)) {
                return employee;
            }
        }
        return null;
    }

    public static Reservation getReservationByReservationId(String reservationId) {
        List<Reservation> reservations = ReservationRepository.getAllReservations();
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId().equals(reservationId)) {
                return reservation;
            }
        }
        return null;
    }

    public static Service getServiceByServiceId(String serviceId) {
        List<Service> services = ServiceRepository.getAllService();
        for (Service service : services) {
            if (service.getServiceId().equals(serviceId)) {
                return service;
            }
        }
        return null;
    }

    public static void editReservationWorkstage() {
        System.out.println("Silahkan Masukkan Reservation ID: ");
        String reservationId = input.nextLine();
        Reservation reservation = getReservationByReservationId(reservationId);
        if (reservation == null) {
            System.out.println("Reservation ID " + reservationId + " tidak ditemukan.");
            return;
        }

        System.out.println("Ubah Workstage (Finish/Canceled):");
        String workstageStatus = input.nextLine();
        Customer customer = reservation.getCustomer();
        if (workstageStatus.equalsIgnoreCase("finish")) {
            if (customer.getWallet() < reservation.getReservationPrice()) {
                System.out.println("Maaf, saldo anda kurang");
            } else {
                double saldo = customer.getWallet() - reservation.getReservationPrice();
                customer.setWallet(saldo);
            }

            reservation.setWorkstage(workstageStatus);
            System.out.println("Workstage berhasil diubah");
        } else if (workstageStatus.equalsIgnoreCase("canceled")) {
            System.out.println("Anda membatalkan reservasi");
        } else {
            System.out.println("Maaf workstage yang anda masukkan salah. (Finish/Canceled)");
        }
    }

    // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan
}
