package u4pp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("welcome to u4pp! Good luck :)\n"+
        "(Please delete this line of code before submitting :) )");
   
        MonsterFighter myMonsterFighter = new MonsterFighter();
        Scanner scanner = new Scanner(System.in);
        myMonsterFighter.play(scanner);
        // TODO: finish play
    }
}