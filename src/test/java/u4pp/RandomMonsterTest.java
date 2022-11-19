package u4pp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RandomMonsterTest {

    private final String NAME = "TestRandomMonster"; 
    private final int MAX_HEALTH = 10; 
    private final int ATTACK = 3; 
    private final int EXPERIENCE = 5; 
    RandomMonster randomMonster;
    Combatant combatant;

    @BeforeEach
    public void beforeEach() {
        randomMonster = new RandomMonster(NAME, MAX_HEALTH, ATTACK, EXPERIENCE);
        randomMonster = Mockito.spy(randomMonster);
        
        combatant = new Combatant("TestCombatant", MAX_HEALTH, ATTACK);
        combatant = Mockito.spy(combatant);
    }

    @Test
    public void class_isChildOfMonster() {
        assertTrue( randomMonster instanceof Monster);        
    }

    @Test
    public void constructor_callsSuperConstructorCorrectly() {
        assertTrue(randomMonster.canFight(), "After calling the constructor, testMonster.canFight() should be true");
        assertEquals(MAX_HEALTH, randomMonster.getMaxHealth(), "After calling the constructor, testMonster.getMaxHealth() should be the given maxHealth");
        assertEquals(MAX_HEALTH, randomMonster.getHealth(), "After calling the constructor, testMonster.getHealth() should be the given maxHealth");
        assertEquals(ATTACK, randomMonster.getAttackPower(), "After calling the constructor, testMonster.getAttack() should be the given attack");
        assertEquals(EXPERIENCE, randomMonster.getExpGiven(), "After calling the constructor, testMonster.getExpGiven() should be the given exp");
    }

    
    @Test
    public void takeTurn_doesDamageAboutHalfTheTime() {
        // don't worry about all this stuff.... it's just checking that you're actually using randomness. 
        final int NUM_RUNS = 10000;
        int[] runLenCounter = new int[(int)Math.log(NUM_RUNS)*3];
        boolean currentRunValue = false;
        int currentRunLength = 0;
        int didDamageCount = 0;
        for(int i = 0; i < NUM_RUNS; i++) {
            combatant.setHealth(combatant.getMaxHealth());
            randomMonster.takeTurn(combatant);
            boolean didDamage = combatant.getHealth() != combatant.getMaxHealth();
            if(didDamage) {
                didDamageCount++;
            }
            if(didDamage != currentRunValue) {
                // log the current run
                if(currentRunLength < runLenCounter.length) {
                    runLenCounter[currentRunLength]++;
                } else {
                    runLenCounter[0]++; // for any runs that are too long for our storage
                }
                currentRunValue = didDamage;
                currentRunLength = 1;
            } else {
                // if (didDamage == currentRunValue
                currentRunLength++;
            }
        }

        int longestRun = 0;
        for(int i = 1; i < runLenCounter.length; i++) {
            if(runLenCounter[i]==0) {
                break;
            }
            longestRun++;
        }
        assertTrue( NUM_RUNS * 0.45 < didDamageCount && didDamageCount < NUM_RUNS * 0.55);
        assertTrue(longestRun > Math.log(NUM_RUNS));
    }


    @Test
    public void toString_hasAllStats() {
        randomMonster.takeDamage(MAX_HEALTH/2);
        String s = randomMonster.toString();
        assertTrue(s.contains(NAME));
        assertTrue(s.contains("" + MAX_HEALTH));
        assertTrue(s.contains("" + (MAX_HEALTH - (MAX_HEALTH/2)))); // current health
        assertTrue(s.contains("" + ATTACK));
        assertTrue(s.contains("" + randomMonster.getExpGiven()));
        assertTrue(s.contains("50%"), "must include some mention that the monster has a 50% chance to attack/miss/sleep/etc.");
    }




}