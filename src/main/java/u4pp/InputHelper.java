package u4pp;

import java.util.Scanner;

public class InputHelper {
    String input;
    Scanner myScanner;
    
    //a constructor which takes in a scanner that is used to read the input
    InputHelper(Scanner scanner){
        myScanner = scanner;
    }

    //takes in a prompt, asks the player the prompt. 
    //Everytime the player does not answer something that starts with a `y`, `Y`, `n`, or `N`, 
    //it tell the player that their input is invalid, and asks them the prompt again. 
    //Loops until proper input is given, at which point, it returns a `true` for yes and `false` for no.
    public boolean getYesNoInput(String prompt){
        System.out.println(prompt);
        input = myScanner.nextLine();
        while ((input.charAt(0) != 'y') && (input.charAt(0) != 'Y') && (input.charAt(0) != 'n') && (input.charAt(0) != 'N')){
            System.out.println("input: " + input );
            System.out.println(prompt);
            input = myScanner.nextLine();
        }
        if (input.charAt(0) == 'y'|| input.charAt(0) == 'Y'){
            return true;
        }
        else if (input.charAt(0) == 'n' || input.charAt(0) == 'N'){
            return false;
        }
        return false;
    }

    //does the same thing as `getYesNoInput`, except for a few differences: 
    //Repeatedly prompts until a proper integer input is given. 
    //The integer must be larger than or equal to `min`, and less than or equal to `max`.
    public int getIntegerInput(String prompt, int min, int max){
        System.out.println(prompt);
        input = myScanner.nextLine();
        int num = Integer.parseInt(input);
        while (num < min || num > max){
            System.out.println(prompt);
            input = myScanner.nextLine();
            num = Integer.parseInt(input);
        }
        return num;
    }
}
