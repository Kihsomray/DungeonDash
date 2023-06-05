package view.console.frame;

import model.dungeon.Dungeon;
import model.entity.battle.Battle;
import model.entity.hero.Hero;
import model.util.Utility;
import view.console.util.ConsoleDisplayUtility;

import java.util.Locale;
import java.util.Scanner;

public class DungeonGameFrame {

    private final String[] ASK_TOM = {"USE AGILE METHODS!"};

    private final Dungeon myDungeon;
    private final Hero myHero;

    public DungeonGameFrame(final Dungeon theDungeon) {

        myDungeon = theDungeon;
        myHero = theDungeon.getHero();

    }

    public void display() {
        // While hero remains non-null.
        while (myDungeon.isGamePlaying()) {

            // Print the dungeon.
            System.out.println(generate());

            // Get the user input.
            char input = new Scanner(System.in)
                    .next()
                    .toUpperCase(Locale.ROOT)
                    .charAt(0);

            // If there is no hero battle.
            if (myHero.getBattle() == null) {

                // Check input.
                switch (input) {

                    case 'W':
                        myHero.moveNorth();
                        break;

                    case 'A':
                        myHero.moveWest();
                        break;

                    case 'S':
                        myHero.moveSouth();
                        break;

                    case 'D':
                        myHero.moveEast();
                        break;

                    case '1', '2', '3', '4', '5', '6', '7', '8':
                        myHero.useInventoryItem(input - 48);
                        break;

                    case 'N':
                        // Save dungeon's current state.
                        Utility.saveDungeonState(myDungeon);
                        break;

                }

                // If there is a battle.
            } else {

                Battle.Result result;
                switch (input) {

                    case 'A':
                        result = myHero.getBattle().heroAttackMonster(Battle.Option.ATTACK);
                        break;

                    case 'S':
                        result = myHero.getBattle().heroAttackMonster(Battle.Option.ABILITY);
                        break;

                    case 'H':
                        result = myHero.getBattle().heroAttackMonster(Battle.Option.HEAL);
                        break;

                    case 'T':
                        System.out.println();
                        break;



                }

                // "Wait" for the monster to attack.
                try {

                    // Wait between 0.5 and 3.0 seconds.
                    Thread.sleep(Utility.RANDOM.nextLong(
                            ConsoleDisplayUtility.MIN_ENEMY_RESPONSE_WAIT_MS,
                            ConsoleDisplayUtility.MAX_ENEMY_RESPONSE_WAIT_MS)
                    );

                } catch (final InterruptedException ie) {

                    // Some wack error.
                    // TODO put some string here.
                }

                myHero.getBattle().monsterAttackHero();


            }

        }
    }

    private String generate() {
        return "";
    }

}
