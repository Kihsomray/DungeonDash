package model;

import model.dungeon.Dungeon;
import model.entity.hero.Hero;
import model.entity.hero.Priestess;
import model.entity.hero.Thief;
import model.entity.hero.Warrior;

import java.io.*;
import java.util.*;

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
            System.out.println("Name your safe file:");
            String saveName = SCANNER.nextLine();

            while (saveName.trim().equals("")) {
                System.out.println("Please enter a non-empty name:");
                saveName = SCANNER.nextLine();
            }

            try {
                // Save the Dungeon in a file.
                FileOutputStream fileStream = new FileOutputStream(saveName);

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
            Dungeon dungeonToLoad = null;
            System.out.println("Enter the file name to load (No extension): ");
            String saveName = SCANNER.nextLine();
            File isValid = new File(saveName);

            if (!isValid.exists()) {

                while (saveName.trim().equals("")) {
                    System.out.println("Please enter a non-empty name:");
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
            final int theMiddle,
            final int theMax,
            final int theLength
    ) {
        return createPointBar(
                theAmount,
                getColor('2'),
                theMiddle,
                getColor('3'),
                theMax,
                getColor('1'),
                theLength
        );
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

    public static String generateCharacterMenu(final char theHero, final String theMessage) {

        final StringBuilder sb = new StringBuilder();
        final Hero hero = generateHeroFromChar(theHero, "");

        // ------- INFORMATION BAR -------

        // Top border.
        appendBorder1(sb);

        // Wall.
        appendWall1(sb);

        // Hero name & statistics.
        sb.append(generateSegment(1))
                .append(getColor('8'))
                .append(centerAndSpace("HERO-" +
                                hero.getClass().getSimpleName(), 31, true))
                .append(getColor('7'))
                .append(generateSegment(1))
                .append("     ")
                .append(generateSegment(1))
                .append(getColor('8'))
                .append("     HP: [")
                .append(createPointBar(hero.getMaxHP(),
                        Math.max(Math.max(
                                Warrior.DEFAULT_HP,
                                Thief.DEFAULT_HP),
                                Priestess.DEFAULT_HP), 18))
                .append(getColor('8'))
                .append("]  DAMAGE: [")
                .append(createPointBar(hero.getMinDamage(),
                        hero.getMaxDamage(),
                        Math.max(Math.max(
                                Warrior.DEFAULT_MAX_DAMAGE,
                                Thief.DEFAULT_MAX_DAMAGE),
                                Priestess.DEFAULT_MAX_DAMAGE), 18))
                .append(getColor('8'))
                .append("]  SPEED: [")
                .append(createPointBar(hero.getAttackSpeed(),
                        Math.max(Math.max(
                                Warrior.DEFAULT_ATTACK_SPEED,
                                Thief.DEFAULT_ATTACK_SPEED),
                        Priestess.DEFAULT_ATTACK_SPEED), 18))
                .append(getColor('8'))
                .append("]     ")
                .append(generateSegment(1))
                .append('\n');

        // Wall.
        appendWall1(sb);

        // Bottom border.
        appendBorder1(sb);

        // ------- HERO DISPLAY -------
        sb.append(" ".repeat(133))
                .append('\n');

        // Top border.
        appendBorder2(sb);

        // Wall.
        appendWall2(sb);

        String[] priestess = Priestess.ASCII_SKIN.split("\n");
        String[] warrior = Warrior.ASCII_SKIN.split("\n");
        String[] thief = Thief.ASCII_SKIN.split("\n");

        for (int i = 0; i < 20; i++) {

            sb.append(generateSegment(1))
                    .append(' ')
                    .append(getColor(hero instanceof Priestess ? '2' : '3'))
                    .append(priestess[i])
                    .append("   ")
                    .append(getColor(hero instanceof Warrior ? '2' : '3'))
                    .append(warrior[i])
                    .append("   ")
                    .append(getColor(hero instanceof Thief ? '2' : '3'))
                    .append(thief[i])
                    .append(' ')
                    .append(generateSegment(1))
                    .append('\n');

        }

        // Wall.
        appendWall2(sb);

        // Bottom border.
        appendBorder2(sb);


        // ------- TIP TOOLBAR -------
        sb.append(" ".repeat(133))
                .append('\n');

        // Top border.
        appendBorder2(sb);

        // Wall.
        appendWall2(sb);

        for (String line : theMessage.split("\n"))
            sb.append(generateSegment(1))
                    .append(getColor('8'))
                    .append(centerAndSpace(line, 131, true))
                    .append(generateSegment(1))
                    .append('\n');

        // Wall.
        appendWall2(sb);

        // Bottom border.
        appendBorder2(sb);

        // Return completed string
        return sb.toString();

    }

    private static void appendBorder1(final StringBuilder theStringBuilder) {
        theStringBuilder.append(getColor('7'))
                .append(generateSegment(1))
                .append(' ')
                .append(generateSegment(29))
                .append(' ')
                .append(generateSegment(1))
                .append("     ")
                .append(generateSegment(1))
                .append(' ')
                .append(generateSegment(91))
                .append(' ')
                .append(generateSegment(1))
                .append('\n');
    }

    private static void appendWall1(final StringBuilder theStringBuilder) {
        theStringBuilder.append(generateSegment(3))
                .append(" ".repeat(27))
                .append(generateSegment(3))
                .append("     ")
                .append(generateSegment(3))
                .append(" ".repeat(89))
                .append(generateSegment(3))
                .append('\n');
    }

    private static void appendBorder2(final StringBuilder theStringBuilder) {
        theStringBuilder.append(generateSegment(1))
                .append(' ')
                .append(generateSegment(129))
                .append(' ')
                .append(generateSegment(1))
                .append('\n');
    }

    private static void appendWall2(final StringBuilder theStringBuilder) {
        theStringBuilder.append(generateSegment(3))
                .append(" ".repeat(127))
                .append(generateSegment(3))
                .append('\n');
    }


    public static Hero generateHeroFromChar(final char theChar, final String theName) {

        return switch (theChar) {
            case 'W' -> new Warrior(theName);
            case 'T' -> new Thief(theName);
            case 'P' -> new Priestess(theName);
            default -> throw new IllegalArgumentException();
        };

    }

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            //  Nothing
        }
    }


}
