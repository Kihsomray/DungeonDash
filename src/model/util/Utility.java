package model.util;

import model.dungeon.Dungeon;
import model.entity.hero.Hero;
import model.entity.hero.Priestess;
import model.entity.hero.Thief;
import model.entity.hero.Warrior;

import java.io.*;
import java.util.*;

/**
 *
 * @author Kihsomray
 * @author Patrick Hern
 */
public final class Utility {

    private Utility() {

    }

    /** Used to generate random numbers. */
    public static final Random RANDOM = new Random();
    public static final Scanner SCANNER = new Scanner(System.in);

    public static String getColor(final char theColor) {
        return "\u001B[3"  + theColor + 'm';
    }

    public static void saveDungeonState(final Dungeon theDungeon) {
        System.out.print("Name your save file (Ex. \"save1\"): ");
        String saveName = SCANNER.nextLine();
        while (saveName.trim().equals("")) {
            System.out.print("Please enter a non-empty name: ");
            saveName = SCANNER.nextLine();
        }

        try {
            // Save the Dungeon in a file.
            FileOutputStream fileStream = new FileOutputStream(saveName + ".ser");

            ObjectOutputStream outStream = new ObjectOutputStream((fileStream));

            // Method to serialize object.
            outStream.writeObject(theDungeon);

            outStream.close();
            fileStream.close();

            System.out.println("Game has been saved.");

        }
        catch (IOException theIOException) {
            System.out.println("IOException, could not save dungeon:\n"
                    + theIOException);
        }
    }

    public static Dungeon loadDungeonState(final Dungeon theDungeon) {
        Dungeon dungeonToLoad;
        System.out.print("Enter the file name to load (Ex. \"save1\"): ");
        String saveName = SCANNER.nextLine();
        File isValid = new File(saveName);

        if (!isValid.exists()) {

            while (saveName.trim().equals("")) {
                System.out.print("Please enter a non-empty name: ");
                saveName = SCANNER.nextLine();
            }

        }

        saveName += ".ser";

        try {
            // Read in the object from a file.
            FileInputStream fileStream = new FileInputStream(saveName);

            ObjectInputStream inStream = new ObjectInputStream((fileStream));

            // Deserialize the Dungeon.
            dungeonToLoad = (Dungeon) inStream.readObject();

            inStream.close();
            fileStream.close();

            return dungeonToLoad;
        }
        catch (IOException theIOException) {
            System.out.println("Could not find file '"
                    + saveName + "': " + theIOException);
        }
        catch (ClassNotFoundException theCNFException) {
            System.out.println("ClassNotFoundException, "
                    + "could not find class.");
        }

        return theDungeon;
    }


    public static Hero generateHeroFromChar(final char theChar, final String theName) {

        return switch (theChar) {
            case 'W' -> new Warrior(theName);
            case 'T' -> new Thief(theName);
            case 'P' -> new Priestess(theName);
            default -> throw new IllegalArgumentException();
        };

    }


}
