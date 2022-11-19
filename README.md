# u4pp2

In this programming project, you will be creating a basic text based dungeon crawler role-playing game (RPG). You will first create an inheritance hierarchy of all the characters (fighter, various monsters) in the game. Then, you will create the actual game itself.  

## Part A: Combatant, the Fighter, and Monsters

You will create an inheritance hierarchy that looks like this:

          Combatant
         /         \
     Fighter      Monster
                 /       \
       RandomMonster    DefenseMonster
## Part A1: Combatant

The `Combatant` class must have the following `public` methods:

### Constructor

- `public Combatant(String name, int maxHealth, int attack)` - constructor. When constructed, the character should also have a health equal to its max health. 

### Getters & Setters

- `public String getName()`
- `public int getHealth()`
- `public int getMaxHealth()`
- `public int getAttackPower()`
- `public void setHealth(int health)` - should not allow health to be set below 0, or above the character's max health
- `public void setMaxHealth(int health)` - should not allow max health to be set to 0 or below
- `public void setAttackPower(int attackPower)` - should not allow attack power to be set below 0

### Other Methods

- `public void takeDamage(int damage)` - cannot take negative damage (does nothing instead). Loses health based on attack power. Use `setHealth` in this method.
- `public boolean canFight()` - returns true if current health is above zero
- `public String toString()` - returns a string that has all the stats of the character, including its name, health, max health, and attack power.

## Part A2: Fighter

The `Fighter` class can block, focus, or attack. Blocking reduces damage taken by half (rounded down) for 1 turn. Focusing makes the next attack twice as powerful. Focusing multiple times doubles the damage each time (yes, it gets really powerful). Attacking does damage to the target and resets focus.

Must have the following `public` methods:

### Constructor 

- `public Fighter(String name, int maxHealth, int attack)` - also sets the default level to `1`, with `0` experience, `0` focus stacks, and not blocking.

### Getters

- `public int getEXP()`
- `public int getFocusStacks()`
- `public int getLevel()`
- `public boolean getIsBlocking()`

### Other Methods

- `public void attack(Combatant target)` - should call `takeDamage` on the target with damage based on the `Fighter`'s attack multiplied by 2 per stack of focus
- `public void block()` - halves the damage (rounded down) taken the next time `takeDamage` is called on the `Fighter`.
- `public void focus()` - increases future damage, by gaining 1 stack of focus
- `public void gainEXP(int exp)` - Gain EXP. When the `Fighter` has EXP greater than or equal to their level, they should spend EXP equal to their level and gain 1 level. Doing so will increase their max health by 5, increase their attack by 1, and heal them to full health. This method should be able to handle gaining so much EXP that the character levels multiple times in a row.

### Overridden Methods

- `public void takeDamage(int damage)` - lose health based on the damage taken. Loses half as much (rounded down) health if the `Fighter` is blocking.
- `public String toString()` - Should return everything that the `Combatant` `toString` does, plus the `Fighter`'s level, experience, number of focus stacks, and a true/false for whether the fighter is blocking.

## Part A3: Monster

The `Monster` class should have the following `public` methods:

### Methods

- `public Monster(String name, int health, int attack, int exp)` - a constructor. The `exp` parameter represents how much experience the monster gives when it is defeated.
- `public int getExpGiven()`
- `public void takeTurn(Combatant target)` - represents what the monster does on its turn. The basic `Monster` should simply attack whatever its target is (usually, the player)

### Overridden Methods

- `public String toString()` - should return everything that the `Combatant` `toString` does, plus the amount of experience the `Monster` gives when it is defeated.

## Part A4: DefenseMonster

The `DefenseMonster` class should have the following `public` methods:

### Methods 

- `public DefenseMonster(String name, int health, int attack, int exp, int defense)` - defense is explained in `takeDamage` below
- `public int getDefense()`

### Overridden Methods

- `public void takeDamage(int damage)` - a `DefenseMonster` negates a certain amount of damage each turn, based on its defense stat. If the damage dealt is less than or equal to its defense, the monster will take no damage. If the damage is greater than the monsters defense, the monster loses health equal to the damage minus its defense.
- `public String toString()` - has everything that `Monster`'s `toString` does, plus the defense of this monster

## Part A5: RandomMonster

The `RandomMonster` class should have the following `public` methods:

## Constructor

- `public RandomMonster(String name, int health, int attack, int exp)`


### Overridden Methods

- `public void takeTurn(Combatant target)` - only has a 50% chance of taking a normal Monster turn. Half the time, does nothing. 
- `public String toString()` - has everything that `Monster`'s `toString` does, and also says something that indicates that the monster only has a 50% chance of attacking.
  
## Part B: MonsterFighter

You will create a file called `MonsterFighter.java`, which will have the following `public` methods: 

- `public MonsterFighter()`
- `public void play(Scanner scanner)`

### Gameplay

- The player should be able to name their `Fighter`, and then fight a bunch of monsters in succession.
- The `Fighter` will gain experience each time they defeat a monster.
- The `Fighter` has 3 possible actions each round: attack, focus, and block. Taking any of these actions will end the `Fighter`'s turn. 
- After the `Fighter`'s turn, the current monster will take a turn, targeting the `Fighter`
- Monsters should have unique names and statistics.
- You must include at least 1 normal `Monster`, 1 `RandomMonster`, and 1 `DefenseMonster`.
- Include as much flavor text as you want.
- *Hint:* Use `InputHelper` from `u4pp1` & use numbers for options (like when picking what action the `Fighter` will take)
- *Hint:* if you ever find yourself repeating code, see if you can create a helper function. If you want to, you can even split a large function into a collection of smaller ones to divide and conquer
  
## Grading Rubric

- Proper formatting/indentation: 2 points
- Correctly JavaDoc'ed all Code: 2 points
- Has no public members other than those specified: 2 points
- Pass all automated test cases (`mvn package`): 5 pts
- `MonsterFighter` gameplay functionality (tested manually): 5 pts
- Clear UI/UX - 2 points

Total: 18 points