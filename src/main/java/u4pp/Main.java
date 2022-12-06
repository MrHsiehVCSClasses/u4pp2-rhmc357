package u4pp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MonsterFighter myGame = new MonsterFighter();
        Scanner myScanner = new Scanner(System.in);
        myGame.play(myScanner);
    }
}