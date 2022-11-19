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

public class CombatantTest {


    private final String NAME = "TestCombatant"; 
    private final int MAX_HEALTH = 10; 
    private final int ATTACK = 3; 
    Combatant combatant1;
    Combatant combatant2;


    @BeforeEach
    public void beforeEach() {
        combatant1 = new Combatant(NAME, MAX_HEALTH, ATTACK);
        combatant1 = Mockito.spy(combatant1);
        
        combatant2 = new Combatant(NAME, MAX_HEALTH, ATTACK);
        combatant2 = Mockito.spy(combatant2);
    }


    @Test 
    public void constructor_setsParametersCorrectly() {
        assertEquals(NAME, combatant2.getName());
        assertEquals(MAX_HEALTH, combatant2.getHealth());
        assertEquals(MAX_HEALTH, combatant2.getMaxHealth());
        assertEquals(ATTACK, combatant2.getAttackPower()); 
    }


    @Test
    public void setHealth_setsHealthCorrectly() {
        combatant1.setHealth(MAX_HEALTH-1);
        assertEquals(MAX_HEALTH-1, combatant1.getHealth());

        combatant2.setHealth(-1);
        assertEquals(0, combatant2.getHealth(), 
        "Health should not go below 0. Negative values should set health to 0.");

        combatant2.setHealth(MAX_HEALTH * 2);
        assertEquals(MAX_HEALTH, combatant2.getHealth(), 
        "Health should not go above max health. Such values should set health equal to max health.");
    }


    @Test
    public void setMaxHealth_changesMaxHealthCorrectly() {
        
        combatant1.setMaxHealth(MAX_HEALTH*2);
        assertEquals(MAX_HEALTH*2, combatant1.getMaxHealth());

        combatant1.setMaxHealth(MAX_HEALTH/2);
        assertEquals(MAX_HEALTH/2, combatant1.getMaxHealth());
    
        combatant1.setMaxHealth(0);
        assertEquals(1, combatant1.getMaxHealth(), 
        "Setting max health to 0 or lower should set max health to 1");
    }

    @Test
    public void setMaxHealth_changesHealthCorrectly() {

        combatant1.setMaxHealth(MAX_HEALTH*2);
        assertEquals(MAX_HEALTH, combatant1.getHealth());

        combatant2.setMaxHealth(MAX_HEALTH/2);
        assertEquals(MAX_HEALTH/2, combatant2.getHealth());
    }




    
    @Test
    public void setAttack_setsAttackCorrectly() {
        combatant1.setAttackPower(ATTACK-1);
        assertEquals(ATTACK-1, combatant1.getAttackPower());

        combatant2.setAttackPower(-1);
        assertEquals(0, combatant2.getAttackPower(), 
        "Attack should not go below 0. Negative values should set attack to 0.");
    }


    @Test
    public void takeDamage_decreasesHealthCorrectly() {
        combatant1.takeDamage(0);
        assertEquals(MAX_HEALTH, combatant1.getHealth());

        combatant1.takeDamage(-1);
        assertEquals(MAX_HEALTH, combatant1.getHealth(),
        "taking a negative amount of damage should work like the combatant is taking 0 damage");

        combatant1.takeDamage(MAX_HEALTH/2);
        assertEquals(MAX_HEALTH - (MAX_HEALTH/2), combatant1.getHealth());

        combatant1.takeDamage(-1);
        assertEquals(MAX_HEALTH - (MAX_HEALTH/2), combatant1.getHealth(), 
        "taking a negative amount of damage should work like the combatant is taking 0 damage");

        combatant2.takeDamage(MAX_HEALTH * 2);
        assertEquals(0, combatant2.getHealth(), 
        "When taking damage, health should not fall below 0. If health would be negative, set it to zero");
        
        verify(combatant2, atLeastOnce()).setHealth(anyInt());
    }


    @Test
    public void canFight_whenHasHealth_returnsTrue() {
        combatant1.takeDamage(MAX_HEALTH/2);
        assertTrue(combatant1.canFight());
        assertTrue(combatant2.canFight());
    }

    @Test
    public void canFight_whenHas0Health_returnFalse() {
        combatant2.takeDamage(MAX_HEALTH);
        assertFalse(combatant2.canFight());
    }


    @Test
    public void toString_hasAllStats() {
        combatant1.takeDamage(MAX_HEALTH/2);
        String s = combatant1.toString();
        assertTrue(s.contains(NAME));
        assertTrue(s.contains("" + MAX_HEALTH));
        assertTrue(s.contains("" + (MAX_HEALTH - (MAX_HEALTH/2)))); // current health
        assertTrue(s.contains("" + ATTACK));
    }
}
