package model.util;

import model.dungeon.Dungeon;
import model.entity.hero.Hero;
import model.entity.hero.Priestess;
import model.entity.hero.Thief;
import model.entity.hero.Warrior;

import java.io.*;
import java.util.*;

/**
 * Utility class for the model.
 *
 * @version 1.0.1
 * @author Patrick Hern
 * @author Kihsomray
 */
public final class Utility {

    /** Used to generate random numbers. */
    public static final Random RANDOM = new Random();

    /** Scanner to get user input. */
    public static final Scanner SCANNER = new Scanner(System.in);

    /** Name of file extension to load/save files. */
    private static final String SAVE_FILE_EXTENSION = "ser";


    /** Used to assure singleton utility class. */
    private Utility() {
        // Nothing.
    }


    /**
     * Saves the current Dungeon state to file.
     *
     * @param theDungeon Dungeon to save.
     * @param theSaveName Name of dungeon save.
     */
    public static void saveDungeonState(final Dungeon theDungeon,
                                        final String theSaveName) {
        // The save file's name.
        String saveName = theSaveName;

        // Concat the file extension.
        saveName += "." + SAVE_FILE_EXTENSION;

        try {

            // Start the save with file output stream.
            final FileOutputStream fileStream = new FileOutputStream(saveName);

            // Pass in the filestream to an object output stream.
            final ObjectOutputStream outStream =
                    new ObjectOutputStream(fileStream);

            // Method to serialize object.
            outStream.writeObject(theDungeon);

            // Close both.
            outStream.close();
            fileStream.close();

            // Let them know that the game has been saved.
            System.out.println("Game has been saved.");

        } catch (final IOException theIOException) {

            // Catch and print exception as needed.
            System.out.println("IOException, could not save dungeon:\n"
                    + theIOException);
        }

        // New line.
        System.out.println();

    }

    /**
     * Loads a dungeon state and returns it.
     *
     * @param theDungeon Dungeon to use in case of failure.
     * @return Resulting Dungeon.
     */
    public static Dungeon loadDungeonState(final Dungeon theDungeon,
                                           final String theLoadName) {
        String loadGameName = theLoadName;

        // Concat the file extension.
        loadGameName += "." + SAVE_FILE_EXTENSION;

        try {

            // Start the save with file input stream.
            final FileInputStream fileStream = new FileInputStream(loadGameName);

            // Pass in the filestream to an object input stream.
            final ObjectInputStream inStream =
                    new ObjectInputStream(fileStream);

            // Deserialize the Dungeon.
            final Dungeon dungeonToLoad = (Dungeon) inStream.readObject();

            // Close both.
            inStream.close();
            fileStream.close();

            // Return the deserialized dungeon.
            return dungeonToLoad;

        } catch (IOException theIOException) {

            // Input/output exception.
            System.out.println("Could not find file '"
                    + loadGameName + "'");

        } catch (ClassNotFoundException theCNFException) {

            // Class not found exception.
            System.out.println("ClassNotFoundException, "
                    + "could not find class.");

        }

        // When all fails, return the given dungeon.
        return theDungeon;
    }

    /**
     * Generates a hero based on the passed char and name.
     *
     * @param theChar Char representing the hero.
     * @param theName Name of the hero.
     * @return Hero based on the char.
     */
    public static Hero generateHeroFromChar(
            final char theChar,
            final String theName
    ) {

        // Check the char.
        return switch (theChar) {

            case 'W' -> new Warrior(theName);       // 'W' - Warrior
            case 'T' -> new Thief(theName);         // 'T' - Thief
            case 'P' -> new Priestess(theName);     // 'P' = Priestess
            default -> throw new IllegalArgumentException();

        };

    }

}
