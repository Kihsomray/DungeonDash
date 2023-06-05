package view.console.frame;

import model.dungeon.Dungeon;
import model.entity.battle.Battle;
import model.entity.hero.Hero;
import model.util.Utility;
import view.console.panel.DungeonInfoPanel;
import view.console.panel.HeroInfoPanel;
import view.console.panel.InventoryInfoPanel;
import view.console.util.ConsoleDisplayUtility;

import java.util.Locale;
import java.util.Scanner;

public class DungeonGameFrame extends ConsoleFrame {

    private static final String[] ASK_TOM = {
            "USE AGILE METHODS!",
            "UTILIZE OOP PRINCIPLES!",
            "YOU'RE GOING TO MAKE ME CRY",
            "WE'VE TALKED ABOUT THIS ALL QTR LONG!",
            ""
    };

    private final Dungeon myDungeon;
    private final Hero myHero;

    private final InventoryInfoPanel myInventoryInfoPanel;
    private final HeroInfoPanel myHeroInfoPanel;

    private final DungeonInfoPanel myDungeonInfoPanel;

    public DungeonGameFrame(final Dungeon theDungeon) {

        myDungeon = theDungeon;
        myHero = theDungeon.getHero();

        myInventoryInfoPanel = new InventoryInfoPanel(myHero);
        myHeroInfoPanel = new HeroInfoPanel(myHero);

        myDungeonInfoPanel = new DungeonInfoPanel(myDungeon);

    }

    public void display() {

        // While the game is in progress.
        while (myDungeon.isGamePlaying()) {

            // Print the dungeon.
            System.out.println(generate());

            // Get the user input.
            char input = new Scanner(System.in)
                    .nextLine()
                    .toUpperCase(Locale.ROOT)
                    .charAt(0);

            // If there is no hero battle.
            if (myHero.getBattle() == null) {

                Hero.MovementResult movementResult;

                // Check input.
                switch (input) {

                    case 'W':
                        movementResult = myHero.moveNorth();
                        break;

                    case 'A':
                        movementResult = myHero.moveWest();
                        break;

                    case 'S':
                        movementResult = myHero.moveSouth();
                        break;

                    case 'D':
                        movementResult = myHero.moveEast();
                        break;

                    case '1', '2', '3', '4', '5', '6', '7', '8':
                        myHero.useInventoryItem(input - 48);
                        continue;

                    case 'N':
                        // Save dungeon's current state.
                        Utility.saveDungeonState(myDungeon);
                        continue;

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

        final String[] leftPanel = (myHeroInfoPanel.generate() + myInventoryInfoPanel.generate()).split("\n");
        final String[] rightPanel = (myDungeonInfoPanel.generate()).split("\n");


        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < leftPanel.length; i++) {
            sb.append(leftPanel[i])
                    .append(MENU_SEGMENT_SEPARATOR_5)
                    .append(rightPanel[i])
                    .append('\n');
        }

        return sb.toString();

    }


}
