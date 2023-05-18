package model.heroes;

import model.DungeonCharacter;
import model.monsters.Monster;

public class Hero extends DungeonCharacter {
    private final Inventory myInventory;

    // Block chance.
    private final double myBlockChance;
    public Hero(
            String theName,
            int theHP,
            int theMinDamage,
            int theMaxDamage,
            int theAttackSpeed,
            double theHitChance,
            double theBlockChance
    ) {
        super(
                theName,
                theHP,
                theMinDamage,
                theMaxDamage,
                theAttackSpeed,
                theHitChance
        );
        myInventory = new Inventory();
        myBlockChance = theBlockChance;
    }

    //        ACCESSORS        //

    public final double GetBlockChance() {
        return myBlockChance;
    }

    public final Inventory GetInventory() {
        return myInventory;
    }


    //        MUTATORS        //
    @Override
    public void receiveDamage(int theDamage) {

        // If out of the range, no damage.
        if (RANDOM.nextDouble() > myBlockChance) return;

        // Otherwise call base.
        super.receiveDamage(theDamage);
    }


    public void attackMonster(Monster theMonster, Boolean theAbility) {

    }
}
