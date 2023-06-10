package view.console;

import controller.DungeonAdventure;
import model.util.Utility;
import view.DungeonGUI;
import view.console.frame.DungeonGameFrame;
import view.console.frame.HeroSelectionFrame;

import java.io.File;
import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import static model.util.Utility.SCANNER;

/**
 * A type of GUI for DungeonAdventure that is displayed in console.
 *
 * @version 1.0.0
 * @author Kihsomray
 * @author Patrick Hern
 */
public class ConsoleDisplay implements DungeonGUI, Serializable {

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
        System.out.print(
                "\nWould you like a new game (N) or load a game (L)? "
        );

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
            System.out.print(
                    "Please input your username (up to 13 characters): "
            );
            username = scanner.nextLine();

        }

        // Get the player's input for username.
        myMain.initializeDungeon(heroChar, username);

        // Load if needed.
        if (load) {

            // Set the dungeon to previous state.
            myMain.setDungeon(Utility.loadDungeonState(
                    myMain.getDungeon(), queryForGameName()
            ));

        }

        // New line in console.
        System.out.println();

        // Create new dungeon game frame.
        myDungeonGameFrame = new DungeonGameFrame(myMain.getDungeon());

        // Display the dungeon game frame.
        myDungeonGameFrame.display();

    }

    /**
     * Queries the user for the name of the game they wish to load.
     * @return Returns the String of the save to load.
     */
    private String queryForGameName() {
        // Ask the user for the name of the file.
        System.out.print("Enter the file name to load (Ex. \"save1\"): ");

        // Get the load name.
        String saveName = SCANNER.nextLine();

        // Keep asking the user until valid file name entered.
        while (saveName.isBlank() || !new File(saveName + ".ser").exists()) {

            System.out.print("Please enter a non-empty name "
                    + "(\"E\" to start a new game): ");
            saveName = SCANNER.nextLine();

        }

        return saveName;
    }

}
