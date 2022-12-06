package u4pp;

public class Combatant {
    private int charMaxHealth;
    private int charHealth;
    private String charName;
    private int charAttack;

    //constructor. When constructed, the character should also have a health equal to its max health.
    public Combatant(String name, int maxHealth, int attack){
        charMaxHealth = maxHealth;
        charName = name;
        charAttack = attack;
        charHealth = maxHealth;
    }
    public String getName(){
        return charName;
    }
    public int getHealth(){
        return charHealth;
    }
    public int getMaxHealth(){
        return charMaxHealth;
    }
    public int getAttackPower(){
        return charAttack;
    }
    //not allow health to be set below 0, or above the character's max health
    public void setHealth(int health){
        if (health > charMaxHealth){
            charHealth = charMaxHealth;
        }
        else if (health < 0){
            charHealth = 0;

        }
        else {
            charHealth = health;
        }
    }
    //not allow max health to be set to 0 or below
    public void setMaxHealth(int health){
        if (health <= 0){
            charMaxHealth = 1;
        }
        else {
            charMaxHealth = health;
        }
        if (charMaxHealth < charHealth){
            charHealth = charMaxHealth;
        }
    }
    //not allow attack power to be set below 0
    public void setAttackPower(int attackPower){
        if (attackPower >= 0){
            charAttack = attackPower;
        }
        else {
            charAttack = 0;
        }
    }

    //cannot take negative damage (does nothing instead). 
    //Loses health based on attack power. Use `setHealth` in this method
    public void takeDamage(int damage){
        int newHealth;
        if (damage >= 0){
            newHealth = charHealth - damage;
            setHealth(newHealth);
        }
    }
    //returns true if current health is above zero
    public boolean canFight(){
        if (charHealth > 0){
            return true;
        }
        return false;
    }
    public String toString(){
        return "Name: " + charName + " health: " + charHealth + " max health: " + charMaxHealth + " attack power: " + charAttack;
    }
}
