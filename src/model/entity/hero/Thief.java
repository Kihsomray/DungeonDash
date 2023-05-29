package model.entity.hero;

import model.Utility;
import model.entity.enemy.monster.Monster;

/**
 * A type of Hero that can be chosen when playing.
 *
 * Special skill: Surprise Attack - Two attacks (40% success rate)
 *                                  One attack  (40% success rate)
 *                                  No attacks  (20% success rate)
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class Thief extends Hero {

    //        FIELDS        //

    public static final int DEFAULT_HP = 75;

    public static final int DEFAULT_MIN_DAMAGE = 20;

    public static final int DEFAULT_MAX_DAMAGE = 40;

    public static final int DEFAULT_ATTACK_SPEED = 6;

    private static final double DEFAULT_HIT_CHANCE = 0.8;

    private static final double DEFAULT_BLOCK_CHANCE = 0.4;

    // Ability success chance.
    private static final double ABILITY_SUCCESS_CHANCE = 0.4;

    // Ability failure chance.
    private static final double ABILITY_FAILURE_CHANCE = 0.2;

    /** 41 x 20 representation of the thief */
    public static final String ASCII_SKIN =
            "                                         \n" +
            "                                         \n" +
            "                   ▄▄▄                   \n" +
            "               ═█╢▓▓▓███,                \n" +
            "               █▓▓█╫▒▒▓██                \n" +
            "               ▓▓█▒[▀╨  ╨▌               \n" +
            "                █▌▓▓╣ULƒ\"                \n" +
            "             █▓▓▓▓▄▄▄███╖           ,▌   \n" +
            "   ▐▀r      █╜░╢▓█▀▀▀█▓▓╣╢╗        ≈ ▌   \n" +
            "    ▄ Σ   ╓▀WÑ╓╢▌╢▓▀▓▓▒█▓╖▒░▄⌐   ╓' └    \n" +
            "     -▄█▄█▓░▄██▀▓▓▓▓▓▒█▓,`▀▄▓░░║█▓█      \n" +
            "      █▓▓▓▀█╢▓▓▓█▓▒▒▒█▓▓▌     ▀▀▀▀       \n" +
            "          ,█▓▓▓██▄▄█▀█▓█                 \n" +
            "          ▐▓█▓█╢░░░╦░░█▌                 \n" +
            "          ▌▓▓█╢░╫░█╣▓░░▌                 \n" +
            "         █▓▓█▒@░█▓╢▓▓@H░▌                \n" +
            "          ▀▐█▒▀▀█▀▀███▀▒▒▒▌              \n" +
            "             ██▀      ╙█▀█               \n" +
            "          ╔█╢           ▀Ü█▄             \n" +
            "                                         ";


    //        CONSTRUCTORS        //

    /**
     * Creates an instance of a Thief.
     */
    public Thief(final String theName) {

        super(
                theName,
                DEFAULT_HP,
                DEFAULT_MIN_DAMAGE,
                DEFAULT_MAX_DAMAGE,
                DEFAULT_ATTACK_SPEED,
                DEFAULT_HIT_CHANCE,
                DEFAULT_BLOCK_CHANCE
        );

    }


    //        MUTATORS        //

    /**
     * Attack a monster.
     *
     * @param theMonster Monster to attack.
     * @param theUseAbility Should the hero use Surprise Attack ability.
     */
    @Override
    public void attackMonster(
            final Monster theMonster,
            final boolean theUseAbility
    ) {

        // If use ability.
        if (theUseAbility) {

            // If succeeds in extra attack.
            if (Utility.RANDOM.nextDouble() <= ABILITY_SUCCESS_CHANCE) {

                // Attack the monster.
                attack(theMonster);

            // Elif fails an attack.
            } else if (Utility.RANDOM.nextDouble() <= ABILITY_FAILURE_CHANCE /
                            (1.0 - ABILITY_SUCCESS_CHANCE)
            ) {

                // Return, don't proceed.
                return;

            }
        }

        // Perform attack.
        attack(theMonster);

    }

}
