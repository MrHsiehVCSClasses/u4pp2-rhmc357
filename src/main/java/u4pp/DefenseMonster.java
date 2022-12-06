package u4pp;

public class DefenseMonster extends Monster{
    private int dMonDefense;
    public DefenseMonster(String name, int health, int attack, int exp, int defense) {
        super(name, health, attack, exp);
        dMonDefense = defense;
    }
    public int getDefense(){
        return dMonDefense;
    }
    //negates a certain amount of damage each turn, based on its defense stat. 
    //If the damage dealt is less than or equal to its defense, the monster will take no damage. 
    //If the damage is greater than the monsters defense, the monster loses health equal to the damage minus its defense
    public void takeDamage(int damage){
        if (damage - getDefense() > 0){
            super.takeDamage(damage - dMonDefense);
        }
        else {
            super.takeDamage(0);
        }
    }
    public String toString(){
        return "Name: " + getName() + " health: " + getHealth() + " max health: " + getMaxHealth() + " attack power: " + getAttackPower() + " experience given: " + getExpGiven() + " defense: " + getDefense();
    }
}
