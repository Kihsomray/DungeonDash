package model.entity.hero;

import model.util.Utility;
import model.entity.enemy.monster.Monster;

/**
 * A type of Hero that can be chosen when playing.
 *
 * Special skill: Heal - 25 to 45 HP (100% success rate).
 *
 * @version 1.0.1
 * @author Kihsomray
 */
public class Priestess extends Hero {

    /** Default HP. */
    public static final int DEFAULT_HP = 315;

    /** Default minimum damage. */
    public static final int DEFAULT_MIN_DAMAGE = 35;

    /** Default maximum damage. */
    public static final int DEFAULT_MAX_DAMAGE = 55;

    /** Default attack speed. */
    public static final int DEFAULT_ATTACK_SPEED = 5;

    /** Default hit chance. */
    private static final double DEFAULT_HIT_CHANCE = 0.7;

    /** Default block chance. */
    private static final double DEFAULT_BLOCK_CHANCE = 0.3;

    /** Ability minimum heal. */
    private static final int ABILITY_MIN_HEAL = 30;

    /** Ability maximum heal. */
    private static final int ABILITY_MAX_HEAL = 65;

    /** 41 x 20 representation of the priestess */
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


    /**
     * Creates an instance of Priestess
     */
    public Priestess(final String theName) {

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

            attack(theMonster, 0, 0);

        } else {

            // Otherwise, do a regular attack.
            attack(theMonster);

        }

    }

    @Override
    public char getDisplayChar() {
        return 'P';
    }

}
