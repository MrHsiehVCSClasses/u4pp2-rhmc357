package u4pp;

public class DefenseMonster extends Monster {

    private int defense;

    public DefenseMonster(String name, int health, int attack, int exp, int defense) {
        super(name, health, attack, exp);
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public void takeDamage(int damage) {
        damage -= defense;
        super.takeDamage(damage);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", DEF:%s", defense);
    }

}