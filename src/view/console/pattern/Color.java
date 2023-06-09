package view.console.pattern;

/**
 * ANSI based colors for displaying color in console.
 *
 * @author Kihsomray
 * @version 1.0.0
 */
public final class Color {

    /** Reset ANSI value. */
    public static final String RESET = "\033[0m";

    /** Bold ANSI value. */
    public static final String BOLD = "\u001B[1m";

    /** Black ANSI value. */
    public static final String BLACK = "\u001B[30m";

    /** Red ANSI value. */
    public static final String RED = "\u001B[31m";

    /** Green ANSI value. */
    public static final String GREEN = "\u001B[32m";

    /** Yellow ANSI value. */
    public static final String YELLOW = "\u001B[33m";

    /** Blue ANSI value. */
    public static final String BLUE = "\u001B[34m";

    /** Purple ANSI value. */
    public static final String PURPLE = "\u001B[35m";

    /** Cyan ANSI value. */
    public static final String CYAN = "\u001B[36m";

    /** Grey ANSI value. */
    public static final String GREY = "\u001B[37m";

    /** White ANSI value. */
    public static final String WHITE = "\u001B[38m";

    /**
     * Private constructor to assert singleton class.
     */
    private Color() {

    }

    /**
     * Retrieves a specific ANSI console color. Will return the color based on
     * the following passed in char:
     *
     * 0 - Black
     * 1 - Red
     * 2 - Green
     * 3 - Yellow
     * 4 - Blue
     * 5 - Purple
     * 6 - Cyan
     * 7 - Grey
     * 8 - White
     *
     * @param theColor Color char as specified above.
     * @return Color ANSI value.
     */
    public static String getColor(final char theColor) {

        // Checks the bounds of available colors.
        if (theColor < '0' || theColor > '8')
            throw new IllegalArgumentException("Invalid color passed");

        // Changes char at location.
        return "\u001B[3" + theColor + 'm';

    }


}
