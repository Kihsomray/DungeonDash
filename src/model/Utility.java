package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Utility {

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
        final StringBuilder sb = new StringBuilder(getColor('7') + BOLD);
        for (int i = 0; i < 5; i++) {
            Collections.shuffle(CHARACTERS);
            sb.append(CHARACTERS.get(0));
        }
        return sb.toString();
    }

}
