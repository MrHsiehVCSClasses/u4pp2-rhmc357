package u4pp;

public class Fighter extends Combatant {
private int level;
private int experience;
private int focusStacks;
private boolean blocking;
    //sets the default level to `1`, with `0` experience, `0` focus stacks, and not blocking
    public Fighter(String name, int maxHealth, int attack) {
        super(name, maxHealth, attack);
        level = 1;
        experience = 0;
        focusStacks = 0;
        blocking = false;
    }
    public int getEXP(){
        return experience;
    }
    public int getFocusStacks(){
        return focusStacks;
    }
    public int getLevel(){
        return level;
    }
    public boolean getIsBlocking(){
        return blocking;
    }

    //call `takeDamage` on the target with damage based on the 
    //`Fighter`'s attack multiplied by 2 per stack of focus
    public void attack(Combatant target){
        int num = 1;
        for (int i = 0; i < focusStacks; i++){
            num *= 2;
        }
        if (focusStacks == 0){
            target.takeDamage(getAttackPower());
            
        } 
        else {
            target.takeDamage(num * getAttackPower());
            focusStacks = 0;
        }
        
    }
    //halves the damage (rounded down) taken the next time 
    //`takeDamage` is called on the `Fighter`
    public void block(){
        blocking = true;
    }
    //increases future damage, by gaining 1 stack of focus
    public void focus(){
        focusStacks += 1;
    }
    //When the `Fighter` has EXP greater than or equal to their level, 
    //they should spend EXP equal to their level and gain 1 level.
    public void gainEXP(int exp){
        experience = getEXP() + exp;
        while (experience >= level){
            experience -= level;
            level += 1;
            setMaxHealth(getMaxHealth() + 5);
            setAttackPower(getAttackPower() + 1);
            setHealth(getMaxHealth());
        }
    }
    //lose health based on the damage taken. 
    //Loses half as much (rounded down) health if the `Fighter` is blocking.
    public void takeDamage(int damage){
        if (blocking == true){
            setHealth(getHealth() - (damage/2));
            blocking = false;
        }
        else {
            setHealth(getHealth() - damage);
            blocking = false;
        }
    }

    public String toString(){
        return "Name: " + getName() + " health: " + getHealth() + " max health: " + getMaxHealth() + " attack power: " + getAttackPower() + " level: " + level + " Experience: " + experience + " Focus Stacks: " + focusStacks + " Is blocking: " + blocking;
    }
}
