package util;

import api.UserResource;
import model.Reservation;
import model.room.IRoom;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class txtMain {

    private static final UserResource userResource = UserResource.getSingleton();
    public static void findAndReserveRoom() throws ParseException {
        Date checkInDate;
        Date checkOutDate;

        while (true) {
            System.out.println("Enter Check-In Date (dd/MM/yyyy): ");
            checkInDate = inputUtil.getDate();

            System.out.println("Enter Check-Out Date (dd/MM/yyyy): ");
            checkOutDate = inputUtil.getDate();

            if (Objects.requireNonNull(checkOutDate).after(checkInDate)) {
                break;
            } else {
                System.out.println("Invalid input! Check-out Date must before Check-in Date.");
            }

        }
        if (checkInDate != null) {
            Collection<IRoom> availableRooms = userResource.findRoom(checkInDate, checkOutDate);

            if (availableRooms.isEmpty()) {
                System.out.println("No rooms found with your choice. Enter number of the day ahead: (up to 127 days)");
                byte nextInput = inputUtil.getChoiceNumber();

                Collection<IRoom> nextRooms = userResource.findNextRoom(checkInDate, checkOutDate, nextInput);

                if (nextRooms.isEmpty()) {
                    System.out.println("No rooms found. Please select another date or the day ahead.");
                } else {
                    Date nextCheckIn = userResource.addNextDays(checkInDate, nextInput);
                    Date nextCheckOut = userResource.addNextDays(checkOutDate, nextInput);
                    System.out.println("We've found rooms on " + nextInput + "days ahead: " );
                    showRooms(nextRooms);
                    System.out.println("Check-In Date: " + nextCheckIn);
                    System.out.println("Check-Out Date: " + nextCheckOut);
                    reserveRoom(nextCheckIn, nextCheckOut, nextRooms);
                }
            } else {
                showRooms(availableRooms);
                reserveRoom(checkInDate, checkOutDate, availableRooms);
            }
        }
    }

    private static void reserveRoom(Date checkInDate,
                                    Date checkOutDate, Collection<IRoom> rooms) {
        System.out.println("Would you like to book? Y/N");
        String bookRoom = inputUtil.getString();

        if ("y".equals(bookRoom)) {
            System.out.println("Do you have an account? Y/N");
            String account = inputUtil.getChoiceCharacter();

            if ("y".equals(account)) {
                System.out.println("Enter your Email: ");
                String email = inputUtil.getEmail();

                if (!userResource.duplicateMail(email)) {
                    System.out.println("Customer not found. Please check your account.");
                } else {
                    System.out.println("Select the room you would like to book: ");
                    String roomNumber = inputUtil.getString();

                    if (rooms.stream().anyMatch(room -> room.getRoomNumber().equals(roomNumber))) {
                        IRoom room = userResource.getRoom(roomNumber);

                        Reservation reservation = userResource
                                .bookRoom(email, room, checkInDate, checkOutDate);
                        System.out.println("Reservation created successfully!");
                        System.out.println(reservation);
                    } else {
                        System.out.println("Error: Room number not available.");
                        System.out.println("Return to Main menu.");
                    }
                }
            } else {
                System.out.println("You need an account to book a room");
                System.out.println("Return to main Menu.");
            }
        } else if ("n".equals(bookRoom)) {
            System.out.println("Return to main Menu.");
        } else {
            reserveRoom(checkInDate, checkOutDate, rooms);
        }
    }

    private static void showRooms(Collection<IRoom> rooms) {
        if (rooms.isEmpty()) {
            System.out.println("No rooms found. Please come back later.");
        } else {
            rooms.forEach(System.out::println);
        }
    }

    public static void showReservation() {

        System.out.println("Enter your Email: ");
        String customerEmail = inputUtil.getEmail();
            if (!userResource.duplicateMail(customerEmail)) {
                System.out.println("The email does not exist. Please check again.");
            }
            else {
                showReservations(userResource.getReservations(customerEmail));
            }

    }

    private static void showReservations(Collection<Reservation> reservations) {
        if (reservations == null || reservations.isEmpty()) {
            System.out.println("No reservations found.");
            System.out.println("Return to main Menu.");
        } else {
            reservations.forEach(reservation -> System.out.println("\n" + reservation));
        }
    }

    public static void createAccount() {
        String email;
        while (true) {
            System.out.println("Enter your email: ");
            email = inputUtil.getEmail();
            if (userResource.duplicateMail(email)) {
                System.out.println("Email already in use. Please register another email.");
            } else {
                System.out.println("Registered mail successfully.");
                break;
            }
        }

        System.out.println("Enter First Name: ");
        String firstName = inputUtil.getString();

        System.out.println("Enter Last Name: ");
        String lastName = inputUtil.getString();

        userResource.createCustomer(email, firstName, lastName);
        System.out.println("Account registered successfully!");
        System.out.println("Return to main Menu.");
    }
}
