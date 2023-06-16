package view.console.pattern;

/**
 * Utility class used to generate progress bars.
 *
 * @author Kihsomray
 * @version 1.0.0
 */
public final class ProgressBar {

    /** Start color of bar. */
    private static final String START_COLOR = Color.GREEN;

    /** Middle color of bar. */
    private static final String MIDDLE_COLOR = Color.YELLOW;

    /** End color of bar. */
    private static final String FINISH_COLOR = Color.RED;


    /** Used to assert the class is a singleton. */
    private ProgressBar() {

    }


    /**
     * Generates a point bar based on start, middle, and finish values.
     *
     * @param theStart Minimum value.
     * @param theMiddle Middle value.
     * @param theFinish Maximum value.
     * @param theLength Length of string.
     * @return Generated point bar.
     */
    public static String generate(
            final int theStart,
            final int theMiddle,
            final int theFinish,
            final int theLength
    ) {
        return generate(
                theStart,
                theMiddle,
                MIDDLE_COLOR,
                theFinish,
                theLength
        );
    }

    /**
     * Generates a point bar based on start and finish values.
     *
     * @param theStart Minimum value.
     * @param theFinish Maximum value.
     * @param theLength Length of string.
     * @return Generated point bar.
     */
    public static String generate(
            final int theStart,
            final int theFinish,
            final int theLength
    ) {
        return generate(
                theStart,
                theStart,
                "",
                theFinish,
                theLength
        );
    }

    /**
     * Creates point bar based on values passed in.
     *
     * @param theMin Minimum value.
     * @param theMiddle Middle value.
     * @param theMiddleColor Middle color.
     * @param theMax Max value.
     * @param theLength Length of string.
     * @return Generated point bar
     */
    private static String generate(
            final double theMin,
            final double theMiddle,
            final String theMiddleColor,
            final double theMax,
            final int theLength
    ) {

        // Get the min value.
        final int start = (int) Math.round(theMin / theMax * theLength);

        // Get the middle value.
        final int middle = (int) Math.min(
                Math.round(theMiddle / theMax * theLength) - start,
                theLength - start
        );

        // Get the finish value.
        final int finish = theLength - middle - start;

        // Return the combined string.
        return START_COLOR + "█".repeat(start) +
                theMiddleColor + "█".repeat(middle) +
                FINISH_COLOR + "█".repeat(finish) +
                Color.RESET;

    }

}
