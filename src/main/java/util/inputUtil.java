package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class inputUtil {
    private static final Scanner sc = new Scanner(System.in);

    public static String getString() {
        return sc.nextLine();
    }

    public static int getInt() {
        int a;
        while (true) {
            String value = getString();
            try {
                a = Integer.parseInt(value);
                break;
            } catch (Exception e) {
                System.out.println("Invalid input! Try again.");
            }
        }
        return a;
    }

    public static double getDouble() {
        double a;
        while (true) {
            String value = getString();
            try {
                a = Double.valueOf(value);
                break;
            } catch (Exception e) {
                System.out.println("Invalid input! Try again.");
            }
        }
        return a;
    }

    public static double getDoublePositive() {
        double a;
        while (true) {
            String value = getString();
            try {
                a = Double.valueOf(value);
                if (a >= 0) {
                    break;
                } else {
                    System.out.println("Invalid input(Value<0)! Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Try again.");
            }
        }
        return a;
    }

    public static float getFloat() {
        float a;
        while (true) {
            String value = getString();
            try {
                a = Float.valueOf(value);
                break;
            } catch (Exception e) {
                System.out.println("Invalid input! Try again.");
            }
        }
        return a;
    }

    public static String getEmail() {
        String a;
        boolean result;
        while (true) {
            String value = getString();
            result = value.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
            if (result) {
                a = value;
                break;
            } else {
                System.out.println("Invalid email input! Try again.");
            }
        }
        return a;
    }

    public static Date getDate() throws ParseException {
        Date a;
        boolean result;
        while (true) {
            String value = getString();
            result = value.matches("^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$");
            if (result) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                a = format.parse(value);
                break;
            } else {
                System.out.println("Invalid input! Try again according to format (dd/mm/yyyy)");
            }
        }
        return a;
    }

    public static int getAge() {
        int a;
        while (true) {
            String value = getString();
            try {
                a = Integer.valueOf(value);
                if (a > 0) {
                    break;
                } else {
                    System.out.println("Invalid input(Value<=0)! Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Try again.");
            }
        }
        return a;
    }

    public static byte getChoiceNumber() {
        byte a;
        while (true) {
            String value = getString();
            try {
                a = Byte.valueOf(value);
                if (a > 0) {
                    break;
                } else {
                    System.out.println("Invalid input! Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Try again.");
            }
        }
        return a;
    }

    public static String getChoiceCharacter() {
        String a;
        while (true) {
            String value = getString();
            try {
                a = String.valueOf(value).toLowerCase();
                if (Objects.equals(a, "y") || Objects.equals(a, "n")) {
                    break;
                } else {
                    System.out.println("Invalid input! Try again. Y/N");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Try again. Y/N");
            }
        }
        return a;
    }
}
