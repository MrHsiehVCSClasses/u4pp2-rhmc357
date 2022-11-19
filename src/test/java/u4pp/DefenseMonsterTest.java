package u4pp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DefenseMonsterTest {

    private final String NAME = "TestDefenseMonster"; 
    private final int MAX_HEALTH = 10; 
    private final int ATTACK = 3; 
    private final int EXPERIENCE = 4; 
    private final int DEFENSE = 2; 
    DefenseMonster defenseMonster;
    Combatant combatant;

    @BeforeEach
    public void beforeEach() {
        defenseMonster = new DefenseMonster(NAME, MAX_HEALTH, ATTACK, EXPERIENCE, DEFENSE);
        defenseMonster = Mockito.spy(defenseMonster);
        
        combatant = new Combatant("TestCombatant", MAX_HEALTH, ATTACK);
        combatant = Mockito.spy(combatant);
    }

    @Test
    public void class_isChildOfMonster() {
        assertTrue( defenseMonster instanceof Monster);        
    }



    @Test
    public void constructor_callsSuperConstructorCorrectly() {
        assertTrue(defenseMonster.canFight(), "After calling the constructor, testMonster.canFight() should be true");
        assertEquals(MAX_HEALTH, defenseMonster.getMaxHealth(), "After calling the constructor, testMonster.getMaxHealth() should be the given maxHealth");
        assertEquals(MAX_HEALTH, defenseMonster.getHealth(), "After calling the constructor, testMonster.getHealth() should be the given maxHealth");
        assertEquals(ATTACK, defenseMonster.getAttackPower(), "After calling the constructor, testMonster.getAttack() should be the given attack");
        assertEquals(EXPERIENCE, defenseMonster.getExpGiven(), "After calling the constructor, testMonster.getExpGiven() should be the given exp");
    }

    @Test
    public void constructor_setsDefenseCorrectly() {
        assertEquals(DEFENSE, defenseMonster.getDefense(), "After calling the constructor, testMonster.getDefense() should be the given defense value");
    }


    @Test
    public void takeDamage_decreasesHealthCorrectly(){
        defenseMonster.takeDamage(DEFENSE);
        assertEquals(defenseMonster.getMaxHealth(), defenseMonster.getHealth(), 
        "When taking damage less than or equal to its defense, " +
        "the defense monster should not lose any health");

        defenseMonster.takeDamage(DEFENSE + 1);
        assertEquals(defenseMonster.getMaxHealth()-1, defenseMonster.getHealth(),
        "when taking damage greater than its defense, " + 
        "the defense monster should lose health equal to the damage minus its defense");
    }

// @Override
// public String toString() {
//     return super.toString() + String.format(", DEF:%s", defense);
// }
    @Test
    public void toString_hasAllStats() {
        //name
        //attack
        //health
        //maxhealth
        //exp
        //def
        defenseMonster.takeDamage(MAX_HEALTH/2);
        String s = defenseMonster.toString();
        assertTrue(s.contains(NAME));
        assertTrue(s.contains("" + MAX_HEALTH));
        assertTrue(s.contains("" + (MAX_HEALTH - (MAX_HEALTH/2 - DEFENSE)))); // current health
        assertTrue(s.contains("" + ATTACK));
        assertTrue(s.contains("" + defenseMonster.getExpGiven()));
        assertTrue(s.contains("" + defenseMonster.getDefense()));
    }



}



