package model.heroes;

import model.DungeonCharacter;
import model.monsters.Monster;

public class Hero extends DungeonCharacter {
    private final int myMinHeal;
    private final int myMaxHeal;
    private final Inventory myInventory;
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
        myMinHeal = 5;
        myMaxHeal = 15;
        myInventory = new Inventory();
        myBlockChance = theBlockChance;
    }

    //        ACCESSORS        //

    public final double getBlockChance() {
        return myBlockChance;
    }

    public final Inventory getInventory() {
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

    public String healHero() {
        if (myInventory.canUseHealthPotion()) {
            int hpToHeal = RANDOM.nextInt(myMinHeal, myMaxHeal + 1);
            receiveHealth(hpToHeal);
            return generateHealMessage(hpToHeal);
        }
        else {
            return "You don't have any health potions.";
        }
    }

    private String generateHealMessage(final int theHealAmount) {
        StringBuilder sb = new StringBuilder();
        sb.append(getName());
        sb.append(" healed for ").append(theHealAmount);

        if (theHealAmount + getHP() > getMaxHP()) {
            int healthWasted = (theHealAmount + getHP()) - getMaxHP();

            sb.append(", though they wasted ");
            sb.append(healthWasted).append(" hit points");
        }

        sb.append(".\n Current HP is now: ").append(getHP());
        return sb.toString();
    }
}
