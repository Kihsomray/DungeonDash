package model.monster;

import model.DungeonCharacter;

public class Monster extends DungeonCharacter {
    private final int myMinHealAmt;
    private final int myMaxHealAmt;
    private final double myHealChance;
    public Monster(
            String theName,
            int theHP,
            int theMinDamage,
            int theMaxDamage,
            int theAttackSpeed,
            double theHitChance,
            int theMinHealAmt,
            int theMaxHealAmt,
            int theHealChance) {
        super(
                theName,
                theHP,
                theMinDamage,
                theMaxDamage,
                theAttackSpeed,
                theHitChance
             );
        myMinHealAmt = theMinHealAmt;
        myMaxHealAmt = theMaxHealAmt;
        myHealChance = theHealChance;
    }

    public int GetMinHealAmt() {
        return myMinHealAmt;
    }

    public int GetMaxHealAmt() {
        return myMaxHealAmt;
    }

    public double GetHealChance() {
        return myHealChance;
    }
}
