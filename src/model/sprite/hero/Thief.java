package model.sprite.hero;

import model.Utility;
import model.sprite.enemy.monster.Monster;

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

    // Ability success chance.
    private static final double ABILITY_SUCCESS_CHANCE = 0.4;

    // Ability failure chance.
    private static final double ABILITY_FAILURE_CHANCE = 0.2;

    public static final String ASCII_SKIN =
            "                                        \n" +
            "                                        \n" +
            "                  ▄▄▄                   \n" +
            "              ═█╢▓▓▓███,                \n" +
            "              █▓▓█╫▒▒▓██                \n" +
            "              ▓▓█▒[▀╨  ╨▌               \n" +
            "               █▌▓▓╣ULƒ\"                \n" +
            "            █▓▓▓▓▄▄▄███╖           ,▌   \n" +
            "  ▐▀r      █╜░╢▓█▀▀▀█▓▓╣╢╗        ≈ ▌   \n" +
            "   ▄ Σ   ╓▀WÑ╓╢▌╢▓▀▓▓▒█▓╖▒░▄⌐   ╓' └    \n" +
            "    -▄█▄█▓░▄██▀▓▓▓▓▓▒█▓,`▀▄▓░░║█▓█      \n" +
            "     █▓▓▓▀█╢▓▓▓█▓▒▒▒█▓▓▌     ▀▀▀▀       \n" +
            "         ,█▓▓▓██▄▄█▀█▓█                 \n" +
            "         ▐▓█▓█╢░░░╦░░█▌                 \n" +
            "         ▌▓▓█╢░╫░█╣▓░░▌                 \n" +
            "        █▓▓█▒@░█▓╢▓▓@H░▌                \n" +
            "         ▀▐█▒▀▀█▀▀███▀▒▒▒▌              \n" +
            "            ██▀      ╙█▀█               \n" +
            "         ╔█╢           ▀Ü█▄             \n" +
            "                                        ";


    //        CONSTRUCTORS        //

    /**
     * Creates an instance of a Thief.
     */
    public Thief(final String theName) {

        super(
                theName,
                125,
                35,
                60,
                4,
                0.8,
                0.2
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
