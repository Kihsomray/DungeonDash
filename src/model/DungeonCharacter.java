package model;

import java.util.Random;

public class DungeonCharacter {
    //      FIELDS      //
    protected static final Random RANDOM = new Random();
    private final String myName;
    private int myHP;
    private final int myMaxHP;
    private final int myMinDamage;
    private final int myMaxDamage;
    private final int myAttackSpeed;
    private final double myHitChance;

    //      CONSTRUCTORS        //
    public DungeonCharacter(
            final String theName,
            final int    theHP,
            final int    theMinDamage,
            final int    theMaxDamage,
            final int    theAttackSpeed,
            final double theHitChance
    ) {
        myName = theName;
        myHP = theHP;
        myMaxHP = theHP;
        myMinDamage = theMinDamage;
        myMaxDamage = theMaxDamage;
        myAttackSpeed = theAttackSpeed;
        myHitChance = theHitChance;
    }

    //      ACCESSORS      //
    public final String getName() {
        return myName;
    }


    public final int getHP() {
        return myHP;
    }

    public final int getMaxHP() { return myMaxHP; }

    public final int getMinDamage() {
        return myMinDamage;
    }

    public final int getMaxDamage() {
        return myMaxDamage;
    }

    public final int getAttackSpeed() {
        return myAttackSpeed;
    }

    public final double getHitChance() {
        return myHitChance;
    }

    //        MUTATORS        //

    protected void receiveDamage(int theDamage) {

        // If lost too much health, the character has died. End the game here.
        if (myHP - theDamage <= 0.0) throw new IndexOutOfBoundsException();
    }

    protected void attack(DungeonCharacter theCharacter) {

        // If out of the range, no damage.
        if (RANDOM.nextDouble() > myHitChance) return;

        // Attack the other character.
        theCharacter.receiveDamage(RANDOM.nextInt(myMinDamage, myMaxDamage));
    }

    protected void healCharacter(final int theHealAmount) {
        myHP = theHealAmount;
    }
}
