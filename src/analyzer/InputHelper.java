package analyzer;

import java.util.Scanner;

/**
 * Safe console input. Every method keeps asking until the user
 * enters a valid value, so bad input can never crash the program.
 */
public class InputHelper {

    // One shared Scanner for the whole program
    private static final Scanner scanner = new Scanner(System.in);

    /** Reads any whole number. */
    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    /** Reads a whole number between min and max (both inclusive). */
    public static int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }

    /** Reads a non-empty line of text. */
    public static String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Input cannot be empty.");
        }
    }
}