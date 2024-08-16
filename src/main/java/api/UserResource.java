package api;

import model.room.IRoom;
import model.Customer;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class UserResource {
    private static final UserResource SINGLETON = new UserResource();
    private final CustomerService customerService = CustomerService.getSingleton();
    private final ReservationService reservationService = ReservationService.getSingleton();

    private UserResource() {}

    public static UserResource getSingleton() {
        return SINGLETON;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createCustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    public Reservation bookRoom(String email, IRoom room, Date checkInDate, Date checkOutDate) {
        return reservationService.reserveRoom(getCustomer(email), room, checkInDate, checkOutDate);
    }

    public Collection<IRoom> findRoom(Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }

    public Collection<IRoom> findNextRoom(Date checkIn, Date checkOut, byte nextInput) {
        return reservationService.findNextRoom(checkIn, checkOut, nextInput);
    }

    public Date addNextDays(Date date, byte nextInput) {
        return reservationService.addNextDays(date, nextInput);
    }

    public Collection<Reservation> getReservations(String email) {
        return reservationService.getReservation(getCustomer(email));
    }

    public IRoom getRoom(String roomNumber) {
        return reservationService.getRoom(roomNumber);
    }
    public boolean duplicateMail(String email){
        return customerService.duplicateMail(email);
    }
}
