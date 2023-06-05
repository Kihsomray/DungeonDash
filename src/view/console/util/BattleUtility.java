package view.console.util;

import model.entity.battle.Battle;
import model.util.Utility;

public class BattleUtility {

    public static final String OPTION_MESSAGE = """
            PROCEED BY CHOOSING AN OPTION:
            A - ATTACK, S - SPECIAL ABILITY
            H - HEAL, T - ASK TOM""";

    public static final String HERO_ATTACKED = """
            
            YOU DEALT {damage} DAMAGE.
            """;

    public static final String MONSTER_ATTACKING = """

            MONSTER IS ATTACKING...
            """;

    public static final String ENEMY_ATTACKED = """
            
            ENEMY DEALT {damage} DAMAGE.
            """;

    public static final String[] HERO_DIED = {
            "WHAT A LOSER!",
            "L BOZO!",
            "IMAGINE BEING THIS BAD.",
            "NOW GO TOUCH SOME GRASS"
    };

    public static final String[] MONSTER_DIED = {
            "HOMIE REALLY THOUGHT!",
            "NICE TRY BUDDY :)",
            "LETS GOOOOOOOOO!!!",
            "TOM'S FAVORITE STUDENT!"
    };

    public static final String ABILITY_UNAVAILABLE = """
            YOU CAN'T USE THAT!
            YOU HAVE ALREADY USED YOUR
            ABILITY FOR THIS MATCH""";

    public static final String ERROR = """
            
            SOME ERROR HAS OCCURRED
            """;

    public static final String TRAP = """
            
            YOU FELL IN A TRAP!
            """;


    public static String getResultMessage(final Battle.Result theResult) {
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
