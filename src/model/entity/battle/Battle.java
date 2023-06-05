package model.entity.battle;

import model.entity.enemy.monster.Monster;
import model.entity.hero.Hero;
import model.inventory.item.potion.HealthPotion;

public class Battle {

    private final Hero myHero;
    private final Monster myMonster;

    private boolean myAbility;

    public Battle(final Hero theHero, final Monster theMonster) {

        myHero = theHero;
        myMonster = theMonster;

        myAbility = true;

    }

    public Result heroAttackMonster(final Option theOption) {

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

                } catch (final IndexOutOfBoundsException iobe) {

                    // The monster died from the attack.
                    return Result.MONSTER_DEAD;

                }
                break;

            case HEAL:
                HealthPotion potion = (HealthPotion) myHero.getInventory()
                        .getInventory().stream()
                        .filter(e -> e instanceof HealthPotion)
                        .findFirst().orElse(null);

                if (potion == null) return Result.UNAVAILABLE;

                potion.applyPotion(myHero);
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

    public Monster getMonster() {
        return myMonster;
    }

    public boolean hasAbility() {
        return myAbility;
    }


    public enum Option {

        ATTACK, ABILITY, HEAL

    }

    public enum Result {

        NORMAL_HERO, NORMAL_MONSTER, UNAVAILABLE, MONSTER_DEAD, HERO_DEAD

    }

}
