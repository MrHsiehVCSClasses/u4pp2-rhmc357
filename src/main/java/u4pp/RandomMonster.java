package u4pp;
import java.util.Random;
public class RandomMonster extends Monster{

    public RandomMonster(String name, int health, int attack, int exp) {
        super(name, health, attack, exp);
        
    }
    //only has a 50% chance of taking a normal Monster turn. Half the time, does nothing
    public void takeTurn(Combatant target){
        Random rand = new Random();
        if (rand.nextInt(100) < 50){
            super.takeTurn(target);
        }
        else {
            setHealth(0);
        }
    }
    public String toString(){
        return "Name: " + getName() + " health: " + getHealth() + " max health: " + getMaxHealth() + " attack power: " + getAttackPower() + "   50% change of taking turn";
    }
}
