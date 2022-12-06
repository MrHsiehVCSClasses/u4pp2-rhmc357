package u4pp;

public class Monster extends Combatant{
    private int monExp;

    //a constructor. The `exp` parameter represents how much experience the monster gives when it is defeated
    public Monster(String name, int health, int attack, int exp) {
        super(name, health, attack);
        if (exp < 0){
            monExp = 0;
        }
        else {
            monExp = exp;
        }
    }
    public int getExpGiven(){
        
        return monExp; 
    }
    //represents what the monster does on its turn. 
    //The basic `Monster` should simply attack whatever its target is (usually, the player)
    public void takeTurn(Combatant target){
        target.takeDamage(getAttackPower());
    }
    public String toString(){
        return "Name: " + getName() + " health: " + getHealth() + " max health: " + getMaxHealth() + " attack power: " + getAttackPower() + " experience given: " + monExp;
    }
    
}
