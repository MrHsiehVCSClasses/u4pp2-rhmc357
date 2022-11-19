package u4pp;

import java.util.Scanner;

public class MonsterFighter {

    private int currentMonsterIndex = 0;
    private Monster[] monsters;
    private Fighter hero;
    private InputHelper inputHelper;

    public MonsterFighter() {
        monsters = new Monster[16];
        monsters[0] = new Monster("Lizard", 5, 1, 1);
        // level 2 here // 10 health, 3 atk
        monsters[1] = new Monster("Lizard", 5, 1, 1);
        monsters[2] = new RandomMonster("Sloth", 15, 1, 1);
        // level 3 here // 20 health, 4 atk
        monsters[3] = new RandomMonster("Big Sloth", 30, 4, 2);
        monsters[4] = new Monster("Lizard", 5, 1, 1);
        // level 4 here // 30 health, 5 atk
        monsters[5] = new RandomMonster("Sleepy Goblin Guard", 15, 6, 1);
        monsters[6] = new RandomMonster("Surprised Goblin", 15, 6, 1);
        monsters[7] = new Monster("Goblin", 15, 8, 2);
        // level 5 here // 50 health, 6 atk
        monsters[8] = new Monster("Goblin Gang", 45, 8, 4);
        monsters[9] = new Monster("Elderly Goblin", 8, 1, 1);
        // level 6 here // 60 health, 8 atk
        monsters[10] = new Monster("Crying Goblin", 15, 0, 1);
        monsters[11] = new DefenseMonster("Blue Slime", 10, 5, 2, 1);
        monsters[12] = new DefenseMonster("Green Slime", 10, 5, 3, 5); 
        // level 7 here // 70 health, 9 atk
        monsters[13] = new DefenseMonster("Red Slime", 10, 15, 4, 10);
        monsters[14] = new DefenseMonster("Grey Slime", 20, 5, 4, 20);
        // level 8 here // 80 health, 10 atk
        monsters[15] = new RandomMonster("Golem", 50, 15, 100);
    }

    //TODO: Finish this play method!!!!
    public void play(Scanner scanner) {
        inputHelper = new InputHelper(scanner);
        clearScreen();
        printLogo();
        inputHelper.waitUntilInput();

        String heroName = inputHelper.getTextInput("What is the name of your character? ");
        
        hero = new Fighter(heroName, 10, 2);
        System.out.println("Hero: " + hero);
        System.out.println();
        System.out.println("Monsters: ");
        for(Monster monster : monsters) {
            System.out.println("   " + monster);
        }
        inputHelper.waitUntilInput();

        boolean isGameOver = false;
        boolean isWinner = false;
        while(!isGameOver) {
            // initialize next fight
            Monster currentMonster = monsters[currentMonsterIndex];

            boolean isFighting = true;
            while(isFighting) {
                // player turn

                // check if monster dead

                // monster turn

                // check if player dead

            }

            currentMonsterIndex += 1;
            // check if winner

            // ask if they want to keep playing
            // set gameOver if they quit
        }
        // game over

        // good job for winning display
        if(isWinner) {

        }

        // not winner, better luck next time display
        else {

        }
    }

    private void printDivider() {
        System.out.println();
        System.out.println();
        System.out.println("------------------------------------");
        System.out.println();
        System.out.println();
    }
    
    private void clearScreen() {  
        System.out.print("\033[H\033[2J"); // special print character
        System.out.flush();  
    }  

    private void printLogo() {
        printDivider();
        System.out.println( 
        "_______  _______  _        _______ _________ _______  _______    _______ _________ _______          _________ _______  _______ \n" +
        "(       )(  ___  )( (    |(  ____ \\\\__   __/(  ____ \\(  ____ )  (  ____ \\\\__   __/(  ____ \\|\\     /|\\__   __/(  ____ \\(  ____ )\n" +
        "| () () || (   ) ||  \\  ( || (    \\/   ) (   | (    \\/| (    )|  | (    \\/   ) (   | (    \\/| )   ( |   ) (   | (    \\/| (    )|\n" +
        "| || || || |   | ||   \\ | || (_____    | |   | (__    | (____)|  | (__       | |   | |      | (___) |   | |   | (__    | (____)|\n" +
        "| |(_)| || |   | || (\\ \\) |(_____  )   | |   |  __)   |     __)  |  __)      | |   | | ____ |  ___  |   | |   |  __)   |     __)\n" +
        "| |   | || |   | || | \\   |      ) |   | |   | (      | (\\ (     | (         | |   | | \\_  )| (   ) |   | |   | (      | (\\ (   \n" +
        "| )   ( || (___) || )  \\  |/\\____) |   | |   | (____/\\| ) \\ \\__  | )      ___) (___| (___) || )   ( |   | |   | (____/\\| ) \\ \\__\n" +
        "|/     \\|(_______)|/    )_)\\_______)   )_(   (_______/|/   \\__/  |/       \\_______/(_______)|/     \\|   )_(   (_______/|/   \\__/");
        printDivider();
    }
}
