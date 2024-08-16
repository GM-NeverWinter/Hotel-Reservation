package menu;

import util.inputUtil;
import util.txtMain;

import java.text.ParseException;

public class MainMenu {

    public static void mainMenu() throws ParseException {
        byte input;
        boolean flag = true;
        while (flag) {
            mainDescription();
            System.out.println("Please select a number for the Main menu option: ");
            input = inputUtil.getChoiceNumber();
            switch (input) {
                case 1:
                    txtMain.findAndReserveRoom();
                    break;
                case 2:
                    txtMain.showReservation();
                    break;
                case 3:
                    txtMain.createAccount();
                    break;
                case 4:
                    AdminMenu.adminMenu();
                    break;
                case 5:
                    System.out.println("Exit Hotel Reservation program.");
                    flag = false;
                    break;
                default:
                    System.out.println("Error: Invalid input. Please select from 1 to 5!");
                    break;
            }
        }
    }

    public static void mainDescription() {
        System.out.print("""
                Welcome to the Hotel Reservation Application
                --------------------------------------------
                1. Find and reserve a room
                2. See my reservations
                3. Create an Account
                4. Admin
                5. Exit
                --------------------------------------------
                """);
    }

}
