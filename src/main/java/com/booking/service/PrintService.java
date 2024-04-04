package com.booking.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.PersonRepository;
import com.booking.repositories.ReservationRepository;
import com.booking.repositories.ServiceRepository;

public class PrintService {
        public static void printMenu(String title, String[] menuArr) {
                int num = 1;
                System.out.println(title);
                for (int i = 0; i < menuArr.length; i++) {
                        if (i == (menuArr.length - 1)) {
                                num = 0;
                        }
                        System.out.println(num + ". " + menuArr[i]);
                        num++;
                }
        }

        public static List<Service> printServices() {
                int counter = 1;
                DecimalFormat currencyFormatter = new DecimalFormat("Rp#,##0");
                List<Service> services = ServiceRepository.getAllService();
                System.out.println(
                                "------------------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-10s | %-20s | %-10s |\n", "No", "ID",
                                "Nama", "Uang");
                System.out.println(
                                "------------------------------------------------------------------------------------");
                for (Service service : services) {
                        System.out.printf("| %-5d | %-10s | %-20s | %-10s |\n", counter,
                                        service.getServiceId(), service.getServiceName(),
                                        currencyFormatter.format(service.getPrice()));
                        counter++;
                }
                System.out.println(
                                "------------------------------------------------------------------------------------");
                return services;
        }

        // Function yang dibuat hanya sebgai contoh bisa disesuaikan kembali
        public static List<Reservation> showRecentReservation() {
                int counter = 1;
                DecimalFormat currencyFormatter = new DecimalFormat("Rp#,##0");
                List<Reservation> reservations = ReservationRepository.getAllReservation();
                System.out.println(
                                "------------------------------------------------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-10s | %-20s | %-30s | %-15s | %-15s |\n", "No", "ID",
                                "Nama Customer", "Service", "Total Biaya", "Workstage");
                System.out.println(
                                "------------------------------------------------------------------------------------------------------------------");
                for (Reservation reservation : reservations) {
                        System.out.printf("| %-5d | %-10s | %-20s | ", counter,
                                        reservation.getReservationId(),
                                        reservation.getCustomer().getName());

                        List<Service> services = reservation.getServices();
                        StringBuilder servicesString = new StringBuilder();
                        for (int i = 0; i < services.size(); i++) {
                                servicesString.append(services.get(i).getServiceName());
                                if (i < services.size() - 1) {
                                        servicesString.append(", ");
                                }
                        }
                        System.out.printf("%-30s | ", servicesString.toString());

                        System.out.printf("%-15s | %-15s |\n",
                                        currencyFormatter.format(reservation.getReservationPrice()),
                                        reservation.getWorkstage());

                        counter++;
                }
                System.out.println(
                                "------------------------------------------------------------------------------------------------------------------");
                return reservations;
        }

        public static List<Customer> showAllCustomer() {
                int counter = 1;
                DecimalFormat currencyFormatter = new DecimalFormat("Rp#,##0");
                List<Customer> customers = PersonRepository.getAllCustomers();
                System.out.println(
                                "------------------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-10s | %-20s | %-10s | %-10s | %-10s |\n", "No", "ID",
                                "Nama", "Alamat", "Membership", "Uang");
                System.out.println(
                                "------------------------------------------------------------------------------------");
                for (Customer customer : customers) {
                        System.out.printf("| %-5d | %-10s | %-20s | %-10s | %-10s | %-10s |\n", counter,
                                        customer.getId(), customer.getName(), customer.getAddress(),
                                        customer.getMember().getMembershipName(),
                                        currencyFormatter.format(customer.getWallet()));
                        counter++;
                }
                System.out.println(
                                "------------------------------------------------------------------------------------");
                return customers;
        }

        public static List<Employee> showAllEmployee() {
                int counter = 1;
                List<Employee> employeeLists = PersonRepository.getAllEmployees();
                System.out.println(
                                "--------------------------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-10s | %-20s | %-10s | %-10s |\n", "No", "ID",
                                "Nama", "Alamat", "Pengalaman");
                System.out.println(
                                "--------------------------------------------------------------------------------------------");
                for (Employee employee : employeeLists) {
                        System.out.printf("| %-5d | %-10s | %-20s | %-10s | %-10s |\n", counter,
                                        employee.getId(), employee.getName(), employee.getAddress(),
                                        employee.getExperience());
                        counter++;
                }
                System.out.println(
                                "--------------------------------------------------------------------------------------------");
                return employeeLists;
        }

        public static List<Reservation> showHistoryReservation() {
                int counter = 1;
                DecimalFormat currencyFormatter = new DecimalFormat("Rp#,##0");
                List<Reservation> histories = new ArrayList<>();
                System.out.println(
                                "------------------------------------------------------------------------------------------------------------------");
                System.out.printf("| %-5s | %-10s | %-20s | %-30s | %-15s | %-15s |\n", "No", "ID",
                                "Nama Customer", "Service", "Total Biaya", "Workstage");
                System.out.println(
                                "------------------------------------------------------------------------------------------------------------------");
                for (Reservation reservation : histories) {
                        System.out.printf("| %-5d | %-10s | %-20s | ", counter,
                                        reservation.getReservationId(),
                                        reservation.getCustomer().getName());

                        List<Service> services = reservation.getServices();
                        StringBuilder servicesString = new StringBuilder();
                        for (int i = 0; i < services.size(); i++) {
                                servicesString.append(services.get(i).getServiceName());
                                if (i < services.size() - 1) {
                                        servicesString.append(", ");
                                }
                        }
                        System.out.printf("%-30s | ", servicesString.toString());

                        System.out.printf("%-15s | %-15s |\n",
                                        currencyFormatter.format(reservation.getReservationPrice()),
                                        reservation.getWorkstage());

                        counter++;
                }
                System.out.println(
                                "------------------------------------------------------------------------------------------------------------------");
                return histories;
        }
}
