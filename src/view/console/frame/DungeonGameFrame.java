package view.console.frame;

import model.dungeon.Dungeon;
import model.entity.battle.Battle;
import model.entity.hero.Hero;
import model.util.Utility;
import view.console.panel.*;
import view.console.util.BattleUtility;
import view.console.util.ConsoleDisplayUtility;

import java.util.Locale;
import java.util.Scanner;

public class DungeonGameFrame extends ConsoleFrame {

    private static final String[] ASK_TOM = {
            "\nUSE AGILE METHODS!",
            "\nUTILIZE OOP PRINCIPLES!",
            "\nYOU'RE GOING TO MAKE ME CRY",
            "WE'VE TALKED ABOUT\nTHIS ALL QUARTER LONG!"
    };

    private final Dungeon myDungeon;
    private final Hero myHero;

    private final InventoryInfoPanel myInventoryInfoPanel;
    private final HeroInfoPanel myHeroInfoPanel;
    private final MonsterInfoPanel myMonsterInfoPanel;

    private final DungeonInfoPanel myDungeonInfoPanel;
    private final ToolTipPanel myToolTipPanel;

    public DungeonGameFrame(final Dungeon theDungeon) {

        myDungeon = theDungeon;
        myHero = theDungeon.getHero();

        myInventoryInfoPanel = new InventoryInfoPanel(myHero);
        myHeroInfoPanel = new HeroInfoPanel(myHero);
        myMonsterInfoPanel = new MonsterInfoPanel(myHero);

        myDungeonInfoPanel = new DungeonInfoPanel(myDungeon);
        myToolTipPanel = new ToolTipPanel();

    }

    public void display() {

        // Print the dungeon.
        System.out.println(generate());

        // While the game is in progress.
        while (myDungeon.isGamePlaying()) {

            // Get the user input.
            final char input = getInput();

            // If there is no hero battle.
            if (myHero.getBattle() == null) {

                Hero.MovementResult movementResult = null;

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
                        break;

                    case 'N':
                        // Save dungeon's current state.
                        Utility.saveDungeonState(myDungeon);
                        break;

                }

                if (movementResult == Hero.MovementResult.TRAP) {

                    sendUI(BattleUtility.TRAP);

                    sendUI(" ");

                    continue;

                } else if (movementResult == Hero.MovementResult.TRAP_AND_MONSTER) {

                    sendUI(BattleUtility.TRAP);

                    delayGame();

                    sendUI(BattleUtility.OPTION_MESSAGE);

                    continue;

                } else if (movementResult == Hero.MovementResult.MONSTER) {

                    sendUI(BattleUtility.OPTION_MESSAGE);

                    continue;

                }

            // If there is a battle.
            } else {

                final int previousMonsterDamage = myHero.getBattle()
                        .getMonster().getLastDamage();

                final Battle.Result result;

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
                        sendUI(ASK_TOM[Utility.RANDOM.nextInt(ASK_TOM.length)]);
                        delayGame();
                        result = Battle.Result.NORMAL_HERO;
                        break;

                    default:
                        sendUI("INVALID OPTION");
                        delayGame();
                        result = Battle.Result.NORMAL_HERO;
                        break;


                }

                if (result != Battle.Result.MONSTER_DEAD) {
                    // Send damage amount.
                    sendUI(BattleUtility.HERO_ATTACKED.replace("{damage}",
                            previousMonsterDamage == myHero.getBattle()
                                    .getMonster().getLastDamage() ? "0" :
                                    Integer.toString(myHero.getBattle()
                                            .getMonster().getLastDamage())));

                    // Delay.
                    delayGame();
                }

                // Print the dungeon.
                sendUI(BattleUtility.getResultMessage(result));

                // "Wait" for the monster to attack.
                delayGame();

                if (result == Battle.Result.MONSTER_DEAD) {
                    sendUI("");
                    continue;
                }

                final int previousHeroDamage = myHero.getLastDamage();

                // Have the monster attack now.
                myHero.getBattle().monsterAttackHero();

                // Send damage amount.
                sendUI(BattleUtility.ENEMY_ATTACKED.replace("{damage}",
                        previousHeroDamage == myHero.getLastDamage() ? "0" :
                                Integer.toString(myHero.getLastDamage())));

                // Delay.
                delayGame();

                // Set to option menu.
                myToolTipPanel.setMessage(BattleUtility.OPTION_MESSAGE);

            }

            // Print the dungeon.
            sendUI(null);

        }
    }

    private void delayGame() {
        try {

            // Wait between 0.5 and 3.0 seconds.
            Thread.sleep(Utility.RANDOM.nextLong(
                    ConsoleDisplayUtility.MIN_ENEMY_RESPONSE_WAIT_MS,
                    ConsoleDisplayUtility.MAX_ENEMY_RESPONSE_WAIT_MS)
            );

        } catch (final InterruptedException ie) {

            // Nothing.

        }
    }

    private void sendUI(final String theMessage) {
        if (theMessage != null) myToolTipPanel.setMessage(theMessage);
        System.out.println(generate());
    }

    private String generate() {

        // Left panel.
        final String[] leftPanel = (myHeroInfoPanel.generate() +
                " ".repeat(LEFT_MENU_WIDTH) + '\n' +
                myInventoryInfoPanel.generate() +
                " ".repeat(LEFT_MENU_WIDTH) + '\n' +
                myMonsterInfoPanel.generate())
                .split("\n");

        // Right panel.
        final String[] rightPanel = (myDungeonInfoPanel.generate() +
                " ".repeat(RIGHT_MENU_WIDTH) + '\n' +
                myToolTipPanel.generate()).split("\n");

        // String builder.
        final StringBuilder sb = new StringBuilder();

        // Loop through and add each
        for (
                int i = 0;
                i < Math.max(leftPanel.length, rightPanel.length);
                i++
        ) {
            sb.append(i < leftPanel.length ?
                            leftPanel[i] :
                            " ".repeat(LEFT_MENU_WIDTH))
                    .append(MENU_SEGMENT_SEPARATOR_5)
                    .append(i < rightPanel.length ?
                            rightPanel[i] :
                            " ".repeat(RIGHT_MENU_WIDTH))
                    .append('\n');
        }

        return sb.toString();

    }


}
