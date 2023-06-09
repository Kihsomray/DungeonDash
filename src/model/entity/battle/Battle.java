package model.entity.battle;

import model.entity.enemy.monster.Monster;
import model.entity.hero.Hero;
import model.inventory.item.potion.HealthPotion;

/**
 * Contains the information needed for a battle between a hero and a given
 * monster. This class should be accessed by the view and have hero attack
 * the monster, followed by the monster attacking the hero.
 *
 * Hero should contain this class. If it is null, there is no battle in the
 * process, and the hero is roaming freely.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class Battle {

    /** Hero of the battle. */
    private final Hero myHero;

    /** Monster of the battle. */
    private final Monster myMonster;

    /** Has the hero used their ability yet. */
    private boolean myAbility;


    /**
     * Creates an instance of a Battle.
     *
     * @param theHero Hero of the battle.
     * @param theMonster Monster of the battle.
     */
    public Battle(final Hero theHero, final Monster theMonster) {

        myHero = theHero;
        myMonster = theMonster;

        // By default, the ability isn't used yet.
        myAbility = true;

    }


    /**
     * Hero attacks the monster. Should be called by the view. Based on the
     * result, the view should also call monster attack hero.
     *
     * @param theOption Option chosen by the player.
     * @return Result of the attack.
     */
    public Result heroAttackMonster(final Option theOption) {

        // Take action based on option.
        switch (theOption) {

            case ABILITY:

                // Check if ability has already been used.
                if (!myAbility) return Result.UNAVAILABLE;

                // Else set it to false.
                else myAbility = false;

            case ATTACK:

                // Try to attack the monster.
                try {

                    // Attack the monster.
                    myHero.attackMonster(myMonster, theOption == Option.ABILITY);

                } catch (final IndexOutOfBoundsException theIOBE) {

                    // The monster died from the attack.
                    return Result.MONSTER_DEAD;

                }
                break;

            case HEAL:

                // Try to use the potion
                HealthPotion potion = (HealthPotion) myHero.getInventory()
                        .getInventory().stream()
                        .filter(e -> e instanceof HealthPotion)
                        .findFirst().orElse(null);

                // If no available potion, return unavailable.
                if (potion == null) return Result.UNAVAILABLE;

                // Otherwise, apply the potion.
                potion.applyPotion(myHero);

                // Remove the potion.
                myHero.getInventory().removeItem(potion);

        }

        return Result.NORMAL_HERO;

    }

    /**
     * Have the monster attack the hero.
     *
     * @return Result from attack.
     */
    public Result monsterAttackHero() {

        // Try to attack the hero.
        try {

            // Attack the hero.
            myMonster.attackHero(myHero);

        } catch (final IndexOutOfBoundsException ie) {

            // The hero died from the attack.
            return Result.HERO_DEAD;

        }

        // Otherwise, the hero is alive and this was a normal attack.
        return Result.NORMAL_MONSTER;

    }


    /**
     * Gets the monster of the battle.
     *
     * @return Monster of the battle.
     */
    public Monster getMonster() {
        return myMonster;
    }

    /**
     * Gets if the hero still has their ability.
     *
     * @return Hero still has ability.
     */
    public boolean hasAbility() {
        return myAbility;
    }


    /**
     * Set of options the player can choose from.
     */
    public enum Option {

        ATTACK, ABILITY, HEAL

    }

    /**
     * Set of results based on the option chosen by the player.
     */
    public enum Result {

        NORMAL_HERO, NORMAL_MONSTER, UNAVAILABLE, MONSTER_DEAD, HERO_DEAD

    }

}
