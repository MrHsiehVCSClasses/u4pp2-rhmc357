package u4pp;

public class Monster extends Combatant{
    private int exp;

    public Monster(String name, int health, int attack, int exp) {
        super(name, health, attack);
        this.exp = Math.max(0,exp);
    }

    public int getExpGiven() {
        return exp;
    }

    public void takeTurn(Combatant target) {
        target.takeDamage(this.getAttackPower());
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", EXP:%s", exp);
    }
}
