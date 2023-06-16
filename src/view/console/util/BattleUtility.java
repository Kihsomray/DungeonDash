package view.console.util;

import model.entity.battle.Battle;
import model.util.Utility;

/**
 * Utility for message during a battle.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class BattleUtility {

    /** Display options during a battle. */
    public static final String OPTION_MESSAGE = """
            PROCEED BY CHOOSING AN OPTION:
            A - ATTACK, S - SPECIAL ABILITY
            H - HEAL, T - ASK TOM""";

    /** Display amount of damage dealt by hero. */
    public static final String HERO_ATTACKED = """
            
            YOU DEALT {damage} DAMAGE.
            """;

    /** Display message for attacking monster. */
    public static final String MONSTER_ATTACKING = """

            MONSTER IS ATTACKING...
            """;

    /** Display amount of damage dealt by enemy. */
    public static final String ENEMY_ATTACKED = """
            
            ENEMY DEALT {damage} DAMAGE.
            """;

    /** Display message when a hero dies. */
    public static final String[] HERO_DIED = {
            "WHAT A LOSER!",
            "L BOZO!",
            "IMAGINE BEING THIS BAD.",
            "NOW GO TOUCH SOME GRASS"
    };

    /** Display message when a monster dies. */
    public static final String[] MONSTER_DIED = {
            "HOMIE REALLY THOUGHT!",
            "NICE TRY BUDDY :)",
            "LETS GOOOOOOOOO!!!",
            "TOM'S FAVORITE STUDENT!"
    };

    /** Display message when the ability has already been used. */
    public static final String ABILITY_UNAVAILABLE = """
            YOU CAN'T USE THAT!
            YOU HAVE ALREADY USED YOUR
            ABILITY FOR THIS MATCH""";


    /** Display message when a hero gets trapped. */
    public static final String TRAP = """
            
            YOU FELL IN A TRAP!
            """;


    /**
     * Gets message based on battle turn result.
     *
     * @param theResult Result of battle turn.
     * @return Message to display.
     */
    public static String getResultMessage(final Battle.Result theResult) {

        // Loop through the results.
        return switch (theResult) {

            case NORMAL_HERO -> MONSTER_ATTACKING;
            case NORMAL_MONSTER -> OPTION_MESSAGE;
            case HERO_DEAD -> "\n" +
                    HERO_DIED[Utility.RANDOM.nextInt(HERO_DIED.length)] +
                    "\n";
            case MONSTER_DEAD -> "\n" +
                    MONSTER_DIED[Utility.RANDOM.nextInt(MONSTER_DIED.length)] +
                    "\n";
            case UNAVAILABLE -> ABILITY_UNAVAILABLE;
            default -> " ";

        };

    }

}
