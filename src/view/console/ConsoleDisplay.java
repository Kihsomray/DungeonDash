package view.console;

import controller.DungeonAdventure;
import model.util.Utility;
import view.DungeonGUI;
import view.console.frame.HeroSelectionFrame;

import java.io.Serializable;
import java.util.Locale;
import java.util.Scanner;

/**
 * A type of GUI for DungeonAdventure that is displayed in console.
 *
 * @author Kihsomray
 * @version 1.0.0
 */
public class ConsoleDisplay implements DungeonGUI {

    /**
     * Instance of main instance.
     */
    private final DungeonAdventure myMain;

    /**
     * Creates an instance of console display.
     *
     * @param theMain Main instance (controller).
     */
    public ConsoleDisplay(final DungeonAdventure theMain) {

        myMain = theMain;

    }

    @Override
    public void display() {

        // Start with hero selection frame.
        final HeroSelectionFrame heroSelection = new HeroSelectionFrame();

        // Display the hero selection.
        char heroChar = heroSelection.display();


        // Ask the player for a username.
        System.out.print("Please input your username (up to 13 characters): ");

        // Get the player's input for username.
        myMain.initializeDungeon(heroChar, new Scanner(System.in).nextLine());

        // New line in console.
        System.out.println();


        // While hero remains non-null.
        while (myMain.getDungeon().getHero() != null) {

            // Print the dungeon.
            System.out.println(myMain.getDungeon());

            // Get the user input.
            char input = new Scanner(System.in)
                    .next()
                    .toUpperCase(Locale.ROOT)
                    .charAt(0);

            // If there is no hero battle.
            if (myMain.getDungeon().getHero().getBattle() == null) {

                // Check input.
                switch (input) {

                    case 'W':
                        myMain.getDungeon().getHero().moveNorth();
                        break;

                    case 'A':
                        myMain.getDungeon().getHero().moveWest();
                        break;

                    case 'S':
                        myMain.getDungeon().getHero().moveSouth();
                        break;

                    case 'D':
                        myMain.getDungeon().getHero().moveEast();
                        break;

                    case '1', '2', '3', '4', '5', '6', '7', '8':
                        myMain.getDungeon().getHero().useInventoryItem(input - 48);
                        break;

                    case 'N':
                        // Save dungeon's current state.
                        Utility.saveDungeonState(myMain.getDungeon());
                        break;

                    case 'L':
                        // Load dungeon save state here.
                        myMain.setDungeon(
                                Utility.loadDungeonState(myMain.getDungeon())
                        );
                        break;

                }

            // If there is a battle.
            } else {

                // Check input.
                switch (input) {

                    case 'A':
                        // TODO attack
                        break;

                    case 'S':
                        // TODO special
                        break;

                    case 'H':
                        // TODO heal potion
                        break;

                    case 'T':
                        // TODO ask tom
                        break;



                }

                // "Wait" for the monster to attack.
                try {

                    // Wait between 0.5 and 3.0 seconds.
                    Thread.sleep(Utility.RANDOM.nextLong(500L, 3000L));

                } catch (final InterruptedException ie) {

                    // Some wack error.
                    // TODO put some string here.
                }

                myMain.getDungeon().getHero().getBattle().monsterAttackHero();


            }

        }




    }



}
