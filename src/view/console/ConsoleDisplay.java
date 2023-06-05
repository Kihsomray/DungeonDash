package view.console;

import controller.DungeonAdventure;
import model.util.Utility;
import view.DungeonGUI;
import view.console.frame.HeroSelectionFrame;

import java.util.Locale;
import java.util.Scanner;

/**
 * A type of GUI for DungeonAdventure that is displayed in console.
 *
 * @version 1.0.0
 * @author Kihsomray
 * @author Patrick Hern
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

        // Scanner object.
        final Scanner scanner = new Scanner(System.in);

        // Selected hero, defaulted to warrior.
        char heroChar = 'W';

        // Username, by default- empty.
        String username = "";

        // Prompt the user.
        System.out.print("\nWould you like a new game (N) or load a game (L)? ");

        // Load if 'L', otherwise new game.
        boolean load = scanner.nextLine()
                .toUpperCase(Locale.ROOT)
                .charAt(0) == 'L';

        // If not load, prompt for username.
        if (!load) {

            // Start with hero selection frame.
            final HeroSelectionFrame heroSelection = new HeroSelectionFrame();

            // Display the hero selection.
            heroChar = heroSelection.display();

            // Prompt and get user input.
            System.out.print("Please input your username (up to 13 characters): ");
            username = scanner.nextLine();

        }

        // Get the player's input for username.
        myMain.initializeDungeon(heroChar, username);

        // Load if needed.
        if (load) {

            // Set the dungeon to previous state.
            myMain.setDungeon(Utility.loadDungeonState(myMain.getDungeon()));

        }

        // New line in console.
        System.out.println();

    }

}
