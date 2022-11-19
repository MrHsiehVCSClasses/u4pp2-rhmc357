package u4pp;

import java.util.Scanner;

public class InputHelper {

    private Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean getYesNoInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            input = input.replaceAll("\\s", ""); // remove whitespace
            input = input.toLowerCase();
            boolean inputContainsY = input.contains("y");
            boolean inputContainsN = input.contains("n");
            if (inputContainsY && !inputContainsN) {
                return true;
            } else if (inputContainsN && !inputContainsY) {
                return false;
            }
            System.out.println("Invalid input. Please answer with (y)es or (n)o.");
        }
    }

    public int getIntegerInput(String prompt, int min, int max) {
        if(min < max) {
            int swap = min;
            min = max;
            max = swap;
        }
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            input = input.replaceAll("\\s", ""); // remove whitespace
            int integer = min-1;
            try {
                integer = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                // do nothing
            }
            if(integer >= min && integer <= max) {
                return integer;
            }
            System.out.println("Invalid input. Please answer with a number between " +min+" and " + max);
        }
    }

    public void waitUntilInput() {
        System.out.println("press enter to continue...");
        scanner.nextLine();
    }

    public String getTextInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }
}
