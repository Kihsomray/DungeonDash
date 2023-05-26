package model.sprite.hero;

import model.Utility;
import model.sprite.enemy.monster.Monster;

/**
 * A type of Hero that can be chosen when playing.
 *
 * Special skill: Heal - 25 to 45 HP (100% success rate).
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class Priestess extends Hero {

    //        FIELDS        //

    // Ability minimum heal.
    private static final int ABILITY_MIN_HEAL = 25;

    // Ability maximum heal.
    private static final int ABILITY_MAX_HEAL = 45;

    public static final String ASCII_SKIN =
            "                                         \n" +
            "           ,                             \n" +
            "     φ╓▄µ▄╨▀⌐       ,.⌐⌐,                \n" +
            "      ▄▓▒▓` '▓▒▄  ╔ⁿ╟▒ W`*▓╔             \n" +
            "     ≡ƒ▒ ╟▒╣▄▄██P ▓▓▀▓▌▀▀╩▌░▌            \n" +
            "     '█▒m▄▐█▓▒█▌   ▓▌▓▓╣UL▐              \n" +
            "       ▐▓▄█▓▌`▀▄  ,▓▒▄╕╣▒▓░▓,            \n" +
            "        '       ▀█▌▌▓Ñ▌▒▀▓▒░▓▒           \n" +
            "                 ▌▓▓█▄▄║Ñ▌╙▌▒▓╙          \n" +
            "                 W▀█▄▄██@▓ ▌ ▒▌▌         \n" +
            "                 █╟▀██▓W▄█░╙▓░H          \n" +
            "                  ▓██▌▒▒█▀█╬k▌╜          \n" +
            "                   ███╢╫█,▐▀█▌           \n" +
            "                  ,▓██╣Ñ▄█`V ▀█▄         \n" +
            "                 ╒]▌▀█]Ü▀▀▒bJ  ▀╣╕       \n" +
            "                 ▌█▌┌█╢╢█ ╙▒H└   \"       \n" +
            "                 m█ ]▒▒╢█r ▌█,           \n" +
            "                   ▌└▌╙╙`▌▐ ▀`           \n" +
            "                    ═╝  ╡,▐              \n" +
            "                                         ";


    //        CONSTRUCTORS        //

    /**
     * Creates an instance of Priestess
     */
    public Priestess(final String theName) {

        super(
                theName,
                75,
                25,
                45,
                5,
                0.7,
                0.3
        );

    }


    //        MUTATORS        //

    /**
     * Attack a monster.
     *
     * @param theMonster Monster to attack.
     * @param theUseAbility Should the hero use Heal ability.
     */
    @Override
    public void attackMonster(
            final Monster theMonster,
            final boolean theUseAbility
    ) {

        // If use ability.
        if (theUseAbility) {

            // Add the necessary health.
            receiveHealth(
                    Utility.RANDOM.nextInt(
                            ABILITY_MIN_HEAL,
                            ABILITY_MAX_HEAL + 1
                    )
            );

        } else {

            // Otherwise, do a regular attack.
            attack(theMonster);
        }

    }

}
