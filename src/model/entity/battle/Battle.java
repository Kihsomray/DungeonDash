package model.entity.battle;

import model.entity.enemy.monster.Monster;
import model.entity.hero.Hero;
import model.util.Utility;

public class Battle {

    private static final long MIN_MONSTER_WAIT_MS = 500L;

    private static final long MAX_MONSTER_WAIT_MS = 3000L;

    private final Hero myHero;
    private final Monster myMonster;

    private boolean myAbility;

    private Integer myHeroDamage;
    private Integer myMonsterDamage;

    public Battle(final Hero theHero, final Monster theMonster) {

        myHero = theHero;
        myMonster = theMonster;

        myAbility = true;

        myHeroDamage = 0;
        myMonsterDamage = 0;

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
                myHero.getInventory().getInventory().stream().findFirst();
                break;




        }

        return null;

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


    public boolean hasAbility() {
        return myAbility;
    }


    public enum Option {

        ATTACK, ABILITY, HEAL, ASK_TOM

    }

    public enum Result {

        NORMAL_HERO, NORMAL_MONSTER, UNAVAILABLE, MONSTER_DEAD, HERO_DEAD, ERROR

    }

}
