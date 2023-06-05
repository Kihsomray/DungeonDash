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

    private static final List<Character> CHARACTERS
            = new ArrayList<>(List.of(new Character[]{
                    '#', '#', '#', '$', '$', '%', '%', '^', '@', '~'
            }));

    /** Used to generate random numbers. */
    public static final Random RANDOM = new Random();
    public static final Scanner SCANNER = new Scanner(System.in);

    public static final String BOLD = "\u001B[1m";

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

    public static String generateSegment() {
        return generateSegment(5);
    }

    public static String generateSegment(final int theWidth) {
        final StringBuilder sb = new StringBuilder(getColor('7') + BOLD);
        for (int i = 0; i < theWidth; i++) {
            Collections.shuffle(CHARACTERS);
            sb.append(CHARACTERS.get(0));
        }
        return sb.toString();
    }

    public static String centerAndSpace(
            final String theString,
            final int theLength,
            final boolean theCapitalize
    ) {

        StringBuilder space = new StringBuilder();
        for (char c : theString.toCharArray()) space.append(c).append(" ");
        space.deleteCharAt(space.length() - 1);

        String spaceString = space.length() > theLength ?
                space.substring(0, theLength) : space.toString();

        spaceString = " ".repeat(
                (int) Math.floor((theLength - spaceString.length()) / 2.0)
        ) + spaceString + " ".repeat(
                (int) Math.ceil((theLength - spaceString.length()) / 2.0)
        );

        return theCapitalize ? spaceString.toUpperCase(Locale.ROOT) : spaceString;
    }

    public static String createPointBar(
            final int theAmount,
            final int theMax,
            final int theLength
    ) {
        return createPointBar(
                theAmount,
                getColor('2'),
                theAmount,
                "",
                theMax,
                getColor('1'),
                theLength
        );
    }

    private static String createPointBar(
            final int theMin,
            final String theMinColor,
            final int theMiddle,
            final String theMiddleColor,
            final int theMax,
            final String theMaxColor,
            final int theLength
    ) {
        final StringBuilder sb = new StringBuilder();
        final int min = (int) Math.round((0.0 + theMin) / theMax * theLength);
        final int middle = (int) Math.round((0.0 + theMiddle) / theMax * theLength) - min;
        final int negative = theLength - middle - min;
        sb.append(theMinColor)
                .append("█".repeat(min))
                .append(theMiddleColor)
                .append("█".repeat(middle))
                .append(theMaxColor)
                .append("█".repeat(negative));
        return sb.toString();
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
