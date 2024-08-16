package service;

import model.Customer;
import model.Reservation;
import model.room.IRoom;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ReservationService {
    private static final ReservationService SINGLETON = new ReservationService();
    private final Map<String, IRoom> rooms = new ConcurrentHashMap<>();
    private final Map<String, Collection<Reservation>> reservations = new ConcurrentHashMap<>();

    private ReservationService() {
    }

    public static ReservationService getSingleton() {
        return SINGLETON;
    }

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getRoom(String roomNumber) {
        return rooms.get(roomNumber);
    }

    public Collection<IRoom> getAllRooms() {
        return rooms.values();
    }

    public Reservation reserveRoom(Customer customer, IRoom room,
                                   Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        Collection<Reservation> reservations = getReservation(customer);

        // Create List for reservation
        if (reservations == null) {
            reservations = new ArrayList<>();
        }

        reservations.add(reservation);
        this.reservations.put(customer.getEmail(), reservations);
        return reservation;
    }

    public Collection<IRoom> findNextRoom(Date checkInDate, Date checkOutDate, byte nextInput) {
        return findRooms(addNextDays(checkInDate, nextInput), addNextDays(checkOutDate, nextInput));
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<Reservation> allReservation = getAllReservations();
        Set<IRoom> roomNA = new HashSet<>();

        for (Reservation reservation : allReservation) {
            if (reservationDuplicate(reservation, checkInDate, checkOutDate)) {
                roomNA.add(reservation.getRoom());
            }
        }
        return rooms.values().stream()
                .filter(room -> !roomNA.contains(room))
                .collect(Collectors.toList());
    }

    // User input next day recommend
    public Date addNextDays(Date date, byte nextInput) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, nextInput);
        return calendar.getTime();
    }

    // Return = true if overlap
    private boolean reservationDuplicate(Reservation reservation, Date checkInDate,
                                         Date checkOutDate) {
        return checkInDate.getTime() <= reservation.getCheckOutDate().getTime()
                && reservation.getCheckInDate().getTime() <= checkOutDate.getTime();
    }

    public Collection<Reservation> getReservation(Customer customer) {
        return reservations.get(customer.getEmail());
    }

    public void showAllReservation() {
        Collection<Reservation> reservations = getAllReservations();

        if (reservations.isEmpty()) {
            System.out.println("No reservations found. Please check again.");
        } else {
            reservations.forEach(System.out::println);
        }
    }

    private Collection<Reservation> getAllReservations() {
        return reservations.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
