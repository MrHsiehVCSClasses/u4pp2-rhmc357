package u4pp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MonsterTest {

    private final String NAME = "TestMonster"; 
    private final int MAX_HEALTH = 10; 
    private final int ATTACK = 3; 
    private final int EXPERIENCE = 4; 
    Monster testMonster;
    Combatant target;

    @BeforeEach
    public void beforeEach() {
        testMonster = new Monster(NAME, MAX_HEALTH, ATTACK, EXPERIENCE);
        target = new Combatant("TestCombatant", MAX_HEALTH, ATTACK);

        testMonster = Mockito.spy(testMonster);
        target = Mockito.spy(target);
    }

    @Test
    public void monster_isChildOfCombatant() {
        assertTrue( testMonster instanceof Combatant);        
    }

    @Test
    public void constructor_callsSuperConstructorCorrectly() {
        assertTrue(testMonster.canFight(), "After calling the constructor, testMonster.canFight() should be true");
        assertEquals(MAX_HEALTH, testMonster.getMaxHealth(), "After calling the constructor, testMonster.getMaxHealth() should be the given maxHealth");
        assertEquals(MAX_HEALTH, testMonster.getHealth(), "After calling the constructor, testMonster.getHealth() should be the give maxHealth");
        assertEquals(ATTACK, testMonster.getAttackPower(), "After calling the constructor, testMonster.getAttack() should be the given attack");
    }

    @Test
    public void constructor_setsExpCorrectly() {
        assertEquals(EXPERIENCE, testMonster.getExpGiven(), "After calling the constructor, testMonster.getExpGiven() should be equal to the given experience");
    }

    @Test
    public void constructor_ignoresNegativeExpValue() {
    Monster testMonster = new Monster(NAME, MAX_HEALTH, ATTACK, -1);
        assertEquals(0, testMonster.getExpGiven(), "After calling the constructor with a negative experience value, testMonster.getExpGiven() should return 0");
    }

    @Test 
    public void takeTurn_damagesTarget() {
        testMonster.takeTurn(target);
        assertEquals(MAX_HEALTH-ATTACK, target.getHealth(), "testMonster should do damage to the given target");
        verify(target, Mockito.times(1).description("testMonster should call takeDamage one time on its target")).takeDamage(ATTACK);
    }

    @Test 
    public void takeTurnTwice_damagesTargetTwice() {
        testMonster.takeTurn(target);
        testMonster.takeTurn(target);
        assertEquals(MAX_HEALTH-ATTACK-ATTACK, target.getHealth(), "testMonster should do damage to the given target");
        verify(target, Mockito.times(2).description("testMonster should call takeDamage one time on its target")).takeDamage(ATTACK);
    }

    @Test
    public void toString_hasAllStats() {
        testMonster.takeDamage(MAX_HEALTH/2);
        String s = testMonster.toString();
        assertTrue(s.contains(NAME));
        assertTrue(s.contains("" + MAX_HEALTH));
        assertTrue(s.contains("" + (MAX_HEALTH - (MAX_HEALTH/2)))); // current health
        assertTrue(s.contains("" + ATTACK));
        assertTrue(s.contains("" + testMonster.getExpGiven()));
    }




}