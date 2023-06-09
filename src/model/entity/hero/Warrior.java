package model.entity.hero;

import model.util.Utility;
import model.entity.enemy.monster.Monster;

/**
 * A type of Hero that can be chosen when playing.
 *
 * Special skill: Crushing Blow - 75 to 175 HP damage (40% success rate).
 *
 * @version 1.0.1
 * @author Kihsomray
 */
public class Warrior extends Hero {

    /** Default HP. */
    public static final int DEFAULT_HP = 575;

    /** Default minimum damage. */
    public static final int DEFAULT_MIN_DAMAGE = 25;

    /** Default maximum damage. */
    public static final int DEFAULT_MAX_DAMAGE = 60;

    /** Default attack speed. */
    public static final int DEFAULT_ATTACK_SPEED = 4;

    /** Default hit chance. */
    private static final double DEFAULT_HIT_CHANCE = 0.8;

    /** Default block chance. */
    private static final double DEFAULT_BLOCK_CHANCE = 0.2;

    /** Ability minimum damage. */
    private static final int ABILITY_MIN_DAMAGE = 75;

    /** Ability maximum damage. */
    private static final int ABILITY_MAX_DAMAGE = 175;

    /** Ability chance. */
    private static final double ABILITY_CHANCE = 0.4;

    /** 41 x 20 representation of the warrior */
    public static final String ASCII_SKIN =
            "                   ╚Ñ╥,,                 \n" +
            "                  ▌█▐▌▓▓L                \n" +
            "                   ╙╙╟▌▓▓L               \n" +
            "            ╔╗,,@▒╩▓▓╢▓,,▓gφgm           \n" +
            "               ▓▌  ║▒▒╢▓▓███             \n" +
            "       ▄      ▄▓▌ ]▒▒╟▓▓▓▓██╢▓,          \n" +
            "      ]▓▓▌╓╓▓▓██▀▒▒╢▓▓▓▓▓▓█▓█▓╣          \n" +
            "       ]╙╙╙╙╙╙╙   ▌^╙▓▓▓╣▓╢▓█▀▓╣▓        \n" +
            "    \"\"\"╟[\"`      ╓@▒j▓▓▓▓▓▓╢▒▓▓▓╣╢       \n" +
            "       ║[       @████████▓╣▓▓▒▓▓▓╣▓r     \n" +
            "       ╟[        ║]██▌▒╣██╣▓▓██╣▓▓╣L     \n" +
            "       ╟[        ║▒▓▓▒▒▓▓▓▓╣╢╫▓╢▓▓╣▓     \n" +
            "       ║[       ,▄██▓▓▒▒▓█▌▓╣╢╢╢╢╢╢╢╣.   \n" +
            "       ║[       ╘▀█▌▓▓╫▀██▌▓╣╢╢╢╢╢╢╢╢▓   \n" +
            "       ║[         ▐█▓▓╣╣▒▓██▓▓╣╢╢╢╣╢╨╙╙  \n" +
            "       ║[        ███▓▓╣╣╫███▓▓▓╙╙╙       \n" +
            "       ║[         ▐▀      ╟▌             \n" +
            "       ║[         ▌       ╟▌             \n" +
            "       ║      ,,@╣╢      J▓╢h            \n" +
            "                                         ";


    /**
     * Create an instance of a warrior.
     */
    public Warrior(final String theName) {

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
     * @param theUseAbility Should the hero use the Crushing Blow ability.
     */
    @Override
    public void attackMonster(
            final Monster theMonster,
            final boolean theUseAbility
    ) {

        // Should use ability and 40% chance?
        if (theUseAbility && Utility.RANDOM.nextDouble() <= ABILITY_CHANCE) {

            // Attack the monster with the range of the ability.
            attack(theMonster, ABILITY_MIN_DAMAGE, ABILITY_MAX_DAMAGE);

        } else {

            // Otherwise, do a regular attack.
            attack(theMonster);

        }

    }


    @Override
    public char getDisplayChar() {
        return 'W';
    }

}
