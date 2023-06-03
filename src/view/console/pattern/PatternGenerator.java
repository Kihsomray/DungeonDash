package view.console.pattern;

import model.util.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Pattern generator that aids in the creation of walls and console GUI
 * elements.
 *
 * @author Kihsomray
 * @version 1.0.1
 */
public class PatternGenerator {

    /** Collection of characters that can be present in a wall string. */
    private static final List<Character> CHARACTERS
            = new ArrayList<>(List.of('#', '#', '#', '$', '$', '%', '%', '^',
            '@', '~', '#', '#', '#', '$', '$', '%', '%', '^', '@', '~', '#')
    );

    /** Should the walls be colorized. */
    private final boolean myColorize;

    /** Color of the wall */
    private String myWallColor;

    /** Keeps track of what position we are at in the list */
    private int myCounter;

    /**
     * Constructs a new pattern generator.
     *
     * @param theColorize Should the generations be colorized.
     */
    public PatternGenerator(final boolean theColorize) {

        myColorize = theColorize;

        // Default wall color.
        myWallColor = Color.GREY;

        // Initializes counter to 0.
        myCounter = 0;

    }

    /**
     * Generates a single non-colorized random wall character.
     *
     * @return Single non-colorized random wall character.
     */
    public String generateSingle() {

        // Calls the segment generator with 1.
        return generateSegment(1, false);

    }

    /**
     * Generate a wall segment based on the passed width.
     *
     * @param theWidth Width of segment.
     * @param theColorize Should this segment colorize?
     * @return Generated segment.
     */
    public String generateSegment(
            final int theWidth,
            final boolean theColorize
    ) {

        // Create the string builder
        final StringBuilder sb = new StringBuilder(
                theColorize ? Color.BOLD + myWallColor : ""
        );

        // Loop through the width of the wall segment.
        for (int i = 0; i < theWidth; i++) {

            // Add on the needed amount of walls.
            sb.append(CHARACTERS.get(myCounter));

            // Shuffle it for random order.
            if (++myCounter == CHARACTERS.size()) {
                Collections.shuffle(CHARACTERS);
                myCounter = 0;
            }

        }

        // Return the completed string.
        return sb.toString();

    }

    /**
     * Generates a randomly segmented generic border.
     *
     * @param theWidth Total width of the border.
     * @return A randomly segmented generic border.
     */
    public String generateHorizontalBorder(final int theWidth) {

        // Width of the middle string.
        final int middleWidth = theWidth - 4;

        // Append and return the created border.
        return generateVerticalBorder(
                1,
                theWidth,
                generateSegment(middleWidth, false),
                false
        );

    }

    /**
     * Generate border with 1 border width with whitespace.
     *
     * @param theTripleWidth Should the border be tripled in width.
     * @param theWidth Width of the vertical border.
     * @return Generated vertical border.
     */
    public String generateVerticalBorder(
            final boolean theTripleWidth,
            final int theWidth
    ) {

        // Call the other method with default param.
        return generateVerticalBorder(theTripleWidth, theWidth, " ");

    }

    /**
     * Generate border with 1 or 3 border width where the text is automatically
     * centered.
     *
     * @param theTripleWidth Should the border be tripled in width.
     * @param theWidth Width of the vertical border.
     * @param theText Text to place between the two borders.
     * @return Generated vertical border.
     */
    public String generateVerticalBorder(
            final boolean theTripleWidth,
            final int theWidth,
            final String theText
    ) {

        // Generate border using other method.
        return generateVerticalBorder(
                theTripleWidth ? 3 : 1,
                theWidth,
                theText,
                true
        );

    }

    /**
     * Generates a vertical border based on passed parameters.
     *
     * @param theBorderWidth Width of the border.
     * @param theTotalWidth Width of the completed string.
     * @param theText Text to place between the borders.
     * @param theCenterAndSpace Should text be centered and spaced?
     * @return Generated vertical border.
     */
    public String generateVerticalBorder(
            final int theBorderWidth,
            final int theTotalWidth,
            final String theText,
            final boolean theCenterAndSpace
    ) {

        // Available space for text.
        final int availableSpace = theTotalWidth - theBorderWidth * 2 - 2;

        // Check if text is too long.
        /*if (theText.length() * (theCenterAndSpace ? 2 - 1 : 1) > availableSpace)
            throw new IndexOutOfBoundsException(
                    "The provided text is too long: " +
                            theText.length() + ", " + availableSpace
            );*/

        // Generate the string.
        return (myColorize ? Color.BOLD + myWallColor : "") +
                generateSegment(theBorderWidth, myColorize) +
                ' ' +
                (theCenterAndSpace ?
                        Utility.centerAndSpace(theText, availableSpace, true) :
                        theText) +
                ' ' +
                generateSegment(theBorderWidth, myColorize);

    }




    /**
     * Set the wall color of the generator.
     *
     * @param theWallColor Wall color.
     */
    public void setMyWallColor(final String theWallColor) {
        this.myWallColor = theWallColor;
    }

}
