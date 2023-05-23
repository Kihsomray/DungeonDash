package model.character.monster;

import model.character.DungeonCharacter;

public abstract class Monster extends DungeonCharacter {

    /** Minimum heal. */
    private final int myMinHeal;

    /** Maximum heal. */
    private final int myMaxHeal;

    /** Heal chance. */
    private final double myHealChance;

    /**
     * Create an instance of Hero.
     *
     * @param theName Name.
     * @param theHP Hit points.
     * @param theMinDamage Minimum damage.
     * @param theMaxDamage Maximum damage.
     * @param theAttackSpeed Attack speed.
     * @param theHitChance Hit chance.
     * @param theMinHeal Minimum heal.
     * @param theMaxHeal Maximum heal.
     */
    public Monster(
            String theName,
            int theHP,
            int theMinDamage,
            int theMaxDamage,
            int theAttackSpeed,
            double theHitChance,
            int theMinHeal,
            int theMaxHeal,
            int theHealChance
    ) {

        super(
                theName,
                theHP,
                theMinDamage,
                theMaxDamage,
                theAttackSpeed,
                theHitChance
        );

        myMinHeal = theMinHeal;
        myMaxHeal = theMaxHeal;
        myHealChance = theHealChance;

    }

    public int getMinHeal() {
        return myMinHeal;
    }

    public int getMaxHealAmt() {
        return myMaxHeal;
    }

    public double getHealChance() {
        return myHealChance;
    }
}
