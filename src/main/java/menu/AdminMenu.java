package menu;

import util.inputUtil;
import util.txtAdmin;

public class AdminMenu {

    public static void adminMenu() {
        byte input;
        boolean flag = true;

        while (flag) {
            txtAdmin.adminDescription();
            System.out.println("Please select a number for Admin menu option: ");
            input = inputUtil.getChoiceNumber();
            switch (input) {
                case 1:
                    txtAdmin.displayAllCustomers();
                    break;
                case 2:
                    txtAdmin.displayAllRooms();
                    break;
                case 3:
                    txtAdmin.displayAllReservations();
                    break;
                case 4:
                    txtAdmin.addRoom();
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    System.out.println("Error: Invalid input. Please select from 1 to 5!");
                    break;
            }
        }
    }
}
