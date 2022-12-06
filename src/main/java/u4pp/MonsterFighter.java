package u4pp;

import java.util.Scanner;

public class MonsterFighter {
    //All of the monsters that the user will fight in order of easy to difficult
    Monster[] monsters = new Monster[16];
    public MonsterFighter(){
        monsters[0] = new Monster("Lizard Larry", 5, 1, 1);
        monsters[1] = new Monster("Lizard Leo", 5, 1, 1);

        monsters[2] = new RandomMonster("Sloth Syd", 15, 1, 1);
        monsters[3] = new RandomMonster("Big Sloth Subs", 30, 4, 2);
        monsters[4] = new Monster("Lizard Luke", 5, 1, 1);

        monsters[5] = new RandomMonster("Sleepy Goblin Guard Garry", 15, 6, 1);
        monsters[6] = new RandomMonster("Surprised Goblin Gerold", 15, 6, 1);
        monsters[7] = new Monster("Goblin Grock", 15, 8, 2);

        monsters[8] = new Monster("Goblin Gang Gamers", 45, 8, 4);
        monsters[9] = new Monster("Elderly Goblin phyllis", 8, 1, 1);

        monsters[10] = new Monster("Crying Goblin Gerrard", 15, 0, 1);
        monsters[11] = new DefenseMonster("Blue Slime Soph", 10, 5, 2, 1);
        monsters[12] = new DefenseMonster("Green Slime Soup", 10, 5, 3, 5); 

        monsters[13] = new DefenseMonster("Red Slime Stef", 10, 15, 4, 10);
        monsters[14] = new DefenseMonster("Grey Slime Sorp", 20, 5, 4, 20);

        monsters[15] = new RandomMonster("Golem Greg", 50, 15, 100);
    }
    public void play(Scanner scanner){
        InputHelper myIH = new InputHelper(scanner);
        System.out.println("Welcome to MONSTER FIGHTER");
        System.out.println( 
        "_______  _______  _        _______ _________ _______  _______    _______ _________ _______          _________ _______  _______ \n" +
        "(       )(  ___  )( (    |(  ____ \\\\__   __/(  ____ \\(  ____ )  (  ____ \\\\__   __/(  ____ \\|\\     /|\\__   __/(  ____ \\(  ____ )\n" +
        "| () () || (   ) ||  \\  ( || (    \\/   ) (   | (    \\/| (    )|  | (    \\/   ) (   | (    \\/| )   ( |   ) (   | (    \\/| (    )|\n" +
        "| || || || |   | ||   \\ | || (_____    | |   | (__    | (____)|  | (__       | |   | |      | (___) |   | |   | (__    | (____)|\n" +
        "| |(_)| || |   | || (\\ \\) |(_____  )   | |   |  __)   |     __)  |  __)      | |   | | ____ |  ___  |   | |   |  __)   |     __)\n" +
        "| |   | || |   | || | \\   |      ) |   | |   | (      | (\\ (     | (         | |   | | \\_  )| (   ) |   | |   | (      | (\\ (   \n" +
        "| )   ( || (___) || )  \\  |/\\____) |   | |   | (____/\\| ) \\ \\__  | )      ___) (___| (___) || )   ( |   | |   | (____/\\| ) \\ \\__\n" +
        "|/     \\|(_______)|/    )_)\\_______)   )_(   (_______/|/   \\__/  |/       \\_______/(_______)|/     \\|   )_(   (_______/|/   \\__/");
        System.out.println("Press enter to continue!");
        scanner.nextLine();
        System.out.print("\033[H\033[2J"); 
        System.out.flush();

        String fighterName;
        System.out.println("What would you like to name your monster?");
        fighterName = scanner.nextLine();
        Fighter userFighter = new Fighter(fighterName, 30, 20);
        

        //fights, blocks, or focus's for each of the monsters
        for (int i = 0; i < monsters.length; i++){
            System.out.println("You are fighting --> " + monsters[i].toString());
            if (myIH.getIntegerInput("Would you like to Attack (1), block (2), or focus (3)?", 1, 3) == 1){
                System.out.println("You have chosen to attack!");
                userFighter.attack(monsters[i]);
            }
            else if (myIH.getIntegerInput("Would you like to Attack (1), block (2), or focus (3)?", 1, 3) == 2){
                System.out.println("You have chosen to block!");
                userFighter.block();
            }
            else {
                System.out.println("You have chosen to focus!");
                userFighter.focus();
            }
            System.out.println(fighterName + " stats: \n");
            System.out.println(userFighter.toString());
            System.out.println("monster stats: \n");
            System.out.println(monsters[i].toString());
            System.out.println("Press enter to continue!");
            scanner.nextLine();

            System.out.println("Monsters Turn!");
            monsters[i].takeTurn(userFighter);

            System.out.println("Press enter to continue!");
            scanner.nextLine();

            if (monsters[i].getHealth() == 0){
                System.out.println(fighterName + " won!");
                userFighter.gainEXP(monsters[i].getExpGiven());
                System.out.println("Current Level: " + userFighter.getLevel());
            }
            else {
                //this runs if the attack did not fully kill the monster
                if (myIH.getIntegerInput("Monster has more life! Would you like to attack again? yes (1) or no (2)", 1, 2) == 1){
                    System.out.println("You have chosen to attack!");
                    userFighter.attack(monsters[i]);
                }
                else {
                    System.out.println("The Monster is attacking again!");
                    monsters[i].takeTurn(userFighter);
                }
                if (monsters[i].getHealth() == 0){
                    System.out.println(fighterName + " won!");
                    userFighter.gainEXP(monsters[i].getExpGiven());
                    System.out.println("Current Level: " + userFighter.getLevel());
                }
            }
            //still alive
            if (userFighter.getHealth() > 0){
                continue;
            }
            //won the game
            else if (i == 15 && userFighter.getHealth() > 0){
                System.out.println("All Monsters have been defeated! Congradulations" + fighterName + " :) ");
            }
            //died in a fight
            else {
                System.out.println(fighterName + "lost :(");
                break;
            }
        }
    }
}
