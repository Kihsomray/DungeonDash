package model;

import java.util.*;

public final class Utility {

    private static final List<Character> CHARACTERS
            = new ArrayList<>(List.of(new Character[]{
                    '#', '#', '#', '$', '$', '%', '%', '^', '@', '~'
            }));

    /** Used to generate random numbers. */
    public static final Random RANDOM = new Random();

    public static final String BOLD = "\u001B[1m";

    public static String getColor(final char theColor) {
        return "\u001B[3"  + theColor + 'm';
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

        String spaceString = space.toString();

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
        final StringBuilder sb = new StringBuilder();
        final int positive = (int) Math.round((0.0 + theAmount) / theMax * theLength);
        final int negative = theLength - positive;
        sb.append(Utility.getColor('2'))
                .append("█".repeat(positive))
                .append(Utility.getColor('1'))
                .append("█".repeat(negative));
        return sb.toString();
    }

}
