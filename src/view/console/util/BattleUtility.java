package view.console.util;

import model.entity.battle.Battle;
import model.util.Utility;

public class BattleUtility {

    private static final String OPTION_MESSAGE = """
            PROCEED BY CHOOSING AN OPTION:
            A - ATTACK, S - SPECIAL ABILITY
            H - HEAL, T - ASK TOM""";

    private static final String MONSTER_ATTACKING = """

            MONSTER IS ATTACKING...
            """;

    private static final String[] HERO_DIED = {
            "WHAT A LOSER!",
            "L BOZO!",
            "IMAGINE BEING THIS BAD.",
            "NOW GO TOUCH SOME GRASS"
    };

    private static final String[] MONSTER_DIED = {
            "HOMIE REALLY THOUGHT!",
            "NICE TRY BUDDY :)",
            "LETS GOOOOOOOOO!!!",
            "TOM'S FAVORITE STUDENT!"
    };

    private static final String ABILITY_UNAVAILABLE = """
            YOU CAN'T USE THAT!
            YOU HAVE ALREADY USED YOUR
            ABILITY FOR THIS MATCH""";

    private static final String ERROR = """
            
            SOME ERROR HAS OCCURRED
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
            default -> ERROR;

        };
    }

}
