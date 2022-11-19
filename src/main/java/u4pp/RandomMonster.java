package u4pp;

import java.util.Random;

public class RandomMonster extends Monster{
    public static Random rand;
    public RandomMonster(String name, int health, int attack, int exp) {
        super(name, health, attack, exp);
        if(rand == null) {
            rand = new Random();
        }
    }
    
    @Override
    public void takeTurn(Combatant target) {
        if(rand.nextInt(2) != 0) {
            target.takeDamage(getAttackPower());
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", 50% attack chance";
    }
}
