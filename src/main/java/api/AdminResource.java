package api;

import model.Customer;
import model.room.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static final AdminResource SINGLETON = new AdminResource();
    private static final CustomerService customerService = CustomerService.getSingleton();
    private static final ReservationService reservationService = ReservationService.getSingleton();

    private AdminResource() {

    }

    public static AdminResource getSingleton() {
        return SINGLETON;
    }

    public void addRoom(List<IRoom> rooms) {
        rooms.forEach(reservationService::addRoom);
    }

    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationService.showAllReservation();
    }

}
