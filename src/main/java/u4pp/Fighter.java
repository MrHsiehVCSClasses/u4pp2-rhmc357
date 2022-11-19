package u4pp;

public class Fighter extends Combatant {

    private int exp;
    private int level;
    private int focusStacks;
    private boolean isBlocking;

    public Fighter(String name, int maxHealth, int attack) {
        super(name, maxHealth, attack);
        level = 1;
        exp = 0;
        focusStacks = 0;
        isBlocking = false;
    }

    public void gainEXP(int exp) {
        this.exp += exp;
        while(this.exp >= this.level) {
            this.exp -= this.level;
            this.level++;
            setAttackPower(this.getAttackPower() + 1);
            this.setMaxHealth(this.getMaxHealth() + 5);
            this.setHealth(this.getMaxHealth());
        }
    }

    public int getEXP() {
        return this.exp;
    }

    public int getFocusStacks() {
        return focusStacks;
    }

    public int getLevel() {
        return this.level;
    }

    public void attack(Combatant target) {
        target.takeDamage(this.getAttackPower() * (int)Math.pow(2, focusStacks));
        focusStacks = 0;
    }

    public void block() {
        isBlocking = true;
    }

    public boolean getIsBlocking() {
        return isBlocking;
    }

    public void focus() {
        focusStacks++;
    }

    @Override
    public void takeDamage(int damage) {
        if(isBlocking) {
            super.takeDamage(damage/2);
        }
        else
        {
            super.takeDamage(damage);
        }
        isBlocking = false;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", LVL:%s, EXP:%s, FOC:%s, BLK:%s", level, exp, focusStacks, isBlocking);
    }
    

}
