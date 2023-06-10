package view.console.frame;

import model.dungeon.Dungeon;
import model.entity.battle.Battle;
import model.entity.hero.Hero;
import model.util.Utility;
import view.console.panel.*;
import view.console.util.BattleUtility;

import static model.util.Utility.SCANNER;

/**
 * A type of console frame that contains the main dungeon game display.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class DungeonGameFrame extends ConsoleFrame {

    /** Minimum wait time delay. */
    public static final long MIN_RESPONSE_WAIT_MS = 1000L;

    /** Maximum wait time delay. */
    public static final long MAX_RESPONSE_WAIT_MS = 1500L;

    /** Array of answers to your questions from Tom. */
    private static final String[] ASK_TOM = {
            "\nUSE AGILE METHODS!",
            "\nUTILIZE OOP PRINCIPLES!",
            "\nYOU'RE GOING TO MAKE ME CRY",
            "WE'VE TALKED ABOUT\nTHIS ALL QUARTER LONG!"
    };

    /** Dungeon contained in the controller. */
    private final Dungeon myDungeon;

    /** Hero contained in the dungeon. */
    private final Hero myHero;

    /** Inventory information panel. */
    private final InventoryInfoPanel myInventoryInfoPanel;

    /** Hero information panel. */
    private final HeroInfoPanel myHeroInfoPanel;

    /** Monster information panel. */
    private final MonsterInfoPanel myMonsterInfoPanel;

    /** Dungeon information panel. */
    private final DungeonInfoPanel myDungeonInfoPanel;

    /** Tooltip panel. */
    private final ToolTipPanel myToolTipPanel;


    /**
     * Creates a new dungeon game frame.
     *
     * @param theDungeon Dungeon to display.
     */
    public DungeonGameFrame(final Dungeon theDungeon) {

        myDungeon = theDungeon;
        myHero = theDungeon.getHero();

        // Left panels.
        myInventoryInfoPanel = new InventoryInfoPanel(myHero);
        myHeroInfoPanel = new HeroInfoPanel(myHero);
        myMonsterInfoPanel = new MonsterInfoPanel(myHero);

        // Right panels.
        myDungeonInfoPanel = new DungeonInfoPanel(myDungeon);
        myToolTipPanel = new ToolTipPanel(myHero);

    }


    /**
     * Displays the frame.
     */
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
                        Utility.saveDungeonState(myDungeon, queryForSaveName());
                        break;

                }

                if (movementResult == Hero.MovementResult.TRAP) {

                    sendUI(BattleUtility.TRAP);

                    delayGame();

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

                final Battle.Result heroResult;

                switch (input) {

                    case 'A':
                        heroResult = myHero.getBattle().heroAttackMonster(Battle.Option.ATTACK);
                        break;

                    case 'S':
                        heroResult = myHero.getBattle().heroAttackMonster(Battle.Option.ABILITY);
                        if (heroResult == Battle.Result.UNAVAILABLE) {
                            sendUI(BattleUtility.ABILITY_UNAVAILABLE);
                            delayGame();
                            sendUI(BattleUtility.OPTION_MESSAGE);
                            continue;
                        }
                        break;

                    case 'H':
                        heroResult = myHero.getBattle().heroAttackMonster(Battle.Option.HEAL);
                        break;

                    case 'T':
                        sendUI(ASK_TOM[Utility.RANDOM.nextInt(ASK_TOM.length)]);
                        delayGame();
                        heroResult = Battle.Result.NORMAL_HERO;
                        break;

                    default:
                        sendUI("\nINVALID OPTION");
                        delayGame();
                        sendUI(BattleUtility.OPTION_MESSAGE);
                        continue;

                }

                if (heroResult != Battle.Result.MONSTER_DEAD) {
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
                sendUI(BattleUtility.getResultMessage(heroResult));

                // "Wait" for the monster to attack.
                delayGame();

                if (heroResult == Battle.Result.MONSTER_DEAD) {
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

                if (myHero.getHP() == 0) {
                    sendUI("\nYou died. Game over!");
                    return;
                }

                // Delay.
                delayGame();

                // Set to option menu.
                myToolTipPanel.setMessage(BattleUtility.OPTION_MESSAGE);

            }

            // Print the dungeon.
            sendUI(null);

        }

        // Winner winner chicken dinner.
        sendUI("\nWinner winner chicken dinner!");


    }

    /**
     * Queries the user to name their save file.
     * @return Returns the name of the save file.
     */
    private String queryForSaveName() {
        // Ask the user to name the file.
        System.out.print("Name your save file (Ex. \"save1\"): ");

        // Get the save name.
        String saveName = SCANNER.nextLine();

        // Keep asking the user until valid name entered.
        while (saveName.isBlank()) {

            System.out.print("Please enter a non-empty name: ");
            saveName = SCANNER.nextLine();

        }
        return saveName;
    }

    /**
     * Delays the game with values mention at the top of the class.
     */
    private void delayGame() {
        try {

            // Wait between 1.0 and 1.5 seconds.
            Thread.sleep(Utility.RANDOM.nextLong(
                    MIN_RESPONSE_WAIT_MS,
                    MAX_RESPONSE_WAIT_MS)
            );

        } catch (final InterruptedException ie) {

            // Nothing.

        }
    }

    /**
     * Sends the current UI after updating the message.
     *
     * @param theMessage Message to update UI with (null for same).
     */
    private void sendUI(final String theMessage) {

        // Sets the message.
        if (theMessage != null) myToolTipPanel.setMessage(theMessage);

        // Print it out!
        System.out.println(generate());

    }

    /**
     * Generates the panel based on the current state of the game.
     *
     * @return Generated string of the game state.
     */
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
        final StringBuilder sb = new StringBuilder("\n\n\n\n\n\n\n\n\n\n");

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
