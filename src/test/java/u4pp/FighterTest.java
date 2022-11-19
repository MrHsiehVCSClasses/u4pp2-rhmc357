package u4pp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

public class FighterTest {
    private final String FIGHTER_NAME = "TestFighter"; 
    private final String MONSTER_NAME = "TestMonster";
    private final int MAX_HEALTH = 10; 
    private final int ATTACK = 3; 
    private final int EXPERIENCE = 5; 
    Combatant combatant;
    Fighter fighter;

    @BeforeEach
    public void beforeEach() {
        combatant = new Combatant(MONSTER_NAME, MAX_HEALTH, ATTACK);
        combatant = Mockito.spy(combatant);
        
        fighter = new Fighter(FIGHTER_NAME, MAX_HEALTH, ATTACK);
        fighter = Mockito.spy(fighter);
    }

    @Test
    public void class_isChildOfCombatant() {
        assert(fighter instanceof Combatant);
    }


    @Test
    public void constructor_callsSuperConstructorCorrectly() {
        assertEquals(MAX_HEALTH, fighter.getMaxHealth());
        assertEquals(MAX_HEALTH, fighter.getHealth());
        assertEquals(FIGHTER_NAME, fighter.getName());
        assertEquals(ATTACK, fighter.getAttackPower());
    }

    @Test
    public void constructor_setsDefaultLevelCorrectly() {
        assertEquals(1, fighter.getLevel());
    }

    @Test
    public void constructor_setsDefaultEXPCorrectly() {
        assertEquals(0, fighter.getEXP());
    }

    @Test
    public void constructor_setsDefaultFocusCorrectly() {
        assertEquals(0, fighter.getFocusStacks());
    }

    @Test
    public void constructor_setsDefaultIsBlockingCorrectly() {
        assertEquals(false, fighter.getIsBlocking());
    }


    @Test
    // must also work if the character levels multiple times
    public void gainEXP_whenLeveling_resetsEXP() {
        int originalEXP = fighter.getEXP();
        fighter.gainEXP(fighter.getLevel());
        int newEXP = fighter.getEXP();
        assertEquals(originalEXP, newEXP);

        fighter.gainEXP(1);
        originalEXP = fighter.getEXP();
        fighter.gainEXP(fighter.getLevel());
        newEXP = fighter.getEXP();
        assertEquals(originalEXP, newEXP);

        originalEXP = fighter.getEXP();
        fighter.gainEXP(fighter.getLevel() + fighter.getLevel() + 1);
        newEXP = fighter.getEXP();
        assertEquals(originalEXP, newEXP);
    }

    @Test
    // must also work if the character levels multiple times
    public void gainEXP_whenLeveling_incrementsLevel() {
        int originalLevel = fighter.getLevel();
        fighter.gainEXP(fighter.getLevel());
        int newLevel = fighter.getLevel();
        assertEquals(originalLevel + 1, newLevel);

        originalLevel = fighter.getLevel();
        fighter.gainEXP(fighter.getLevel() + fighter.getLevel() + 1);
        newLevel = fighter.getLevel();
        assertEquals(originalLevel+2, newLevel);
    }

    @Test
    // must also work if the character levels multiple times
    public void gainEXP_whenLeveling_refillsHealth() {
        fighter.takeDamage(2);
        int originalHealth = fighter.getHealth();
        fighter.gainEXP(fighter.getLevel());
        int newHealth = fighter.getHealth();
        assertTrue(originalHealth <  newHealth);
        assertEquals(fighter.getMaxHealth(), newHealth);

        fighter.takeDamage(2);
        originalHealth = fighter.getHealth();
        fighter.gainEXP(fighter.getLevel() + fighter.getLevel() + 1);
        newHealth = fighter.getHealth();
        assertTrue(originalHealth <  newHealth);
        assertEquals(fighter.getMaxHealth(), newHealth);
    }

    @Test
    // must also work if the character levels multiple times
    public void gainEXP_whenLeveling_increasesStats() {
        int originalAttack = fighter.getAttackPower();
        int originalMaxHealth = fighter.getMaxHealth();
        fighter.gainEXP(fighter.getLevel());
        int newAttack = fighter.getAttackPower();
        int newMaxHealth = fighter.getMaxHealth();
        assertEquals(originalAttack+1, newAttack);
        assertEquals(originalMaxHealth+5, newMaxHealth);

        originalAttack = fighter.getAttackPower();
        originalMaxHealth = fighter.getMaxHealth();
        fighter.gainEXP(fighter.getLevel() + fighter.getLevel() + 1);
        newAttack = fighter.getAttackPower();
        newMaxHealth = fighter.getMaxHealth();
        assertEquals(originalAttack+1+1, newAttack);
        assertEquals(originalMaxHealth+5+5, newMaxHealth);
    }

    @Test
    public void attack_shouldCallTakeDamageOnTarget() {
        fighter.attack(combatant);
        verify(combatant, atLeastOnce()).takeDamage(anyInt());
    }


    @Test
    public void attack_whenFocused_doublesDamagePerStack() {
        // for 0, 1, 2, 3, 4, 5...9 stacks, make sure the damage is correct
        combatant.setMaxHealth(10000000);
        for(int i = 0; i < 10; i++) {
            combatant.setHealth(combatant.getMaxHealth());
            for(int j = 0; j < i; j++) {
                // focus for i times
                fighter.focus();
            }
            assertEquals(i, fighter.getFocusStacks());
            fighter.attack(combatant);
            int expectedDamage = fighter.getAttackPower() * (int)Math.pow(2, i);
            int actualDamage = combatant.getMaxHealth() - combatant.getHealth();
            assertEquals(expectedDamage, actualDamage);
        }
    }

    
    @Test
    public void block_takingEvenDamage_halvesDamage() {
        fighter.block();
        assertEquals(true, fighter.getIsBlocking());
        fighter.takeDamage(4);
        int actualDamage = fighter.getMaxHealth() - fighter.getHealth();
        assertEquals(false, fighter.getIsBlocking());
        assertEquals(2, actualDamage);
    }

    @Test
    public void block_takingOddDamage_halvesDamage() {
        fighter.block();
        assertEquals(true, fighter.getIsBlocking());
        fighter.takeDamage(5);
        int actualDamage = fighter.getMaxHealth() - fighter.getHealth();
        assertEquals(false, fighter.getIsBlocking());
        assertEquals(2, actualDamage);   
    }

    @Test
    public void block_takingDamageTwice_reducesDamageForOnlyOneAttack() {
        // take damage again to make sure that block doesn't last more than 1 round
        assertEquals(false, fighter.getIsBlocking());
        fighter.block();
        assertEquals(true, fighter.getIsBlocking());
        fighter.takeDamage(4);
        int actualDamage = fighter.getMaxHealth() - fighter.getHealth();
        assertEquals(false, fighter.getIsBlocking());
        assertEquals(2, actualDamage);   
        fighter.takeDamage(4);
        actualDamage = fighter.getMaxHealth() - fighter.getHealth();
        assertEquals(false, fighter.getIsBlocking());
        assertEquals(2+4, actualDamage);   
    }


    @Test
    public void toString_hasAllStats() {
        fighter.gainEXP(100);
        fighter.takeDamage(1);
        fighter.focus();
        fighter.focus();
        String s = fighter.toString();

        assertTrue(s.contains(FIGHTER_NAME));
        assertTrue(s.contains("" + fighter.getMaxHealth()));
        assertTrue(s.contains("" + (fighter.getMaxHealth()-1))); // current health
        assertTrue(s.contains("" + fighter.getAttackPower()));
        assertTrue(s.contains("" + fighter.getLevel()));
        assertTrue(s.contains("" + fighter.getEXP()));
        assertTrue(s.contains("" + fighter.getIsBlocking()));
        assertTrue(s.contains("" + fighter.getFocusStacks()));

        fighter.focus();
        fighter.block();
        s = fighter.toString();

        assertTrue(s.contains("" + fighter.getIsBlocking()));
        assertTrue(s.contains("" + fighter.getFocusStacks()));
    }
}
