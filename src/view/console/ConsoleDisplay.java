package view.console;

import controller.DungeonAdventure;
import model.util.Utility;
import view.DungeonGUI;
import view.console.frame.DungeonGameFrame;
import view.console.frame.HeroSelectionFrame;

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
     * Instance of main.
     */
    private final DungeonAdventure myMain;

    /**
     * Instance of hero selection frame.
     */
    private HeroSelectionFrame myHeroSelectionFrame;

    /**
     * Instance of dungeon game frame.
     */
    private DungeonGameFrame myDungeonGameFrame;

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
            myHeroSelectionFrame = new HeroSelectionFrame();

            // Display the hero selection.
            heroChar = myHeroSelectionFrame.display();

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

        // Create new dungeon game frame.
        myDungeonGameFrame = new DungeonGameFrame(myMain.getDungeon());

        // Display the dungeon game frame.
        myDungeonGameFrame.display();

    }

}
