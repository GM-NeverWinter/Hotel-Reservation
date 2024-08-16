package util;

import api.AdminResource;
import model.Customer;
import model.room.IRoom;
import model.room.Room;
import model.room.RoomType;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class txtAdmin {
    private static final AdminResource adminResource = AdminResource.getSingleton();

    public static void addRoom() {
        while(true) {
            System.out.println("Enter room number:");
            final String roomNumber = inputUtil.getString();

            System.out.println("Enter price per night:");
            final double roomPrice = inputUtil.getDouble();

            final Room room = new Room(roomNumber, roomPrice, inputRoomType());

            adminResource.addRoom(Collections.singletonList(room));
            System.out.println("Room added successfully!");

            System.out.println("Would like to add another room? Y/N");
            String addAnother = inputUtil.getChoiceCharacter();
            if (Objects.equals(addAnother, "n")) {
                System.out.println("Return to Admin menu.");
                break;
            }
        }
    }

    private static RoomType inputRoomType() {
        RoomType roomType;
        while (true) {
            System.out.println("Enter room type: 1 for single bed, 2 for double bed:");
            byte selectRoomType = inputUtil.getChoiceNumber();
            if (selectRoomType == 1) {
                roomType = RoomType.SINGLE;
                break;
            } else if (selectRoomType == 2) {
                roomType = RoomType.DOUBLE;
                break;
            } else {
                System.out.println("System: Invalid input! Please select (1) or (2)");
            }
        }
        return roomType;
    }


    public static void displayAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();

        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            adminResource.getAllRooms().forEach(System.out::println);
        }
    }

    public static void displayAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            adminResource.getAllCustomers().forEach(System.out::println);
        }
    }

    public static void displayAllReservations() {
        adminResource.displayAllReservations();
    }

    public static void adminDescription() {
        System.out.print("""
                Admin Menu
                --------------------------------------------
                1. See all Customers
                2. See all Rooms
                3. See all Reservations
                4. Add a Room
                5. Back to Main Menu
                --------------------------------------------
                """);
    }

}
