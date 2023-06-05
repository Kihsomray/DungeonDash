package view.console.pattern;

import model.util.Utility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Pattern generator that aids in the creation of walls and console GUI
 * elements.
 *
 * @author Kihsomray
 * @version 1.0.1
 */
public class PatternGenerator implements Serializable {

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

    /** Color of text if selected. */
    private String myTextColor;

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

        // Set default color to white.
        myTextColor = Color.WHITE;

    }

    /**
     * Generates a single non-colorized random wall character.
     *
     * @return Single non-colorized random wall character.
     */
    public String generateSingle() {

        // Calls the segment generator with 1.
        return generateSegment(1, true);

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
                false,
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
                true,
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
     * @param theTextColor Should the text be colored as set above?
     * @return Generated vertical border.
     */
    public String generateVerticalBorder(
            final int theBorderWidth,
            final int theTotalWidth,
            final String theText,
            final boolean theCenterAndSpace,
            final boolean theTextColor
    ) {

        // Available space for text.
        final int availableSpace = theTotalWidth - theBorderWidth * 2 - 2;

        // Generate the string.
        return (myColorize ? Color.BOLD + myWallColor : "") +
                generateSegment(theBorderWidth, myColorize) +
                (theTextColor ? ' ' + myTextColor : ' ') +
                (theCenterAndSpace ?
                        centerAndSpace(theText, availableSpace, true) :
                        theText) +
                ' ' +
                generateSegment(theBorderWidth, myColorize);

    }

    /**
     * Centers and spaces out a string.
     *
     * @param theString String to parse.
     * @param theLength Length of spacing.
     * @param theCapitalize Should it be capitalized.
     * @return Compiled String.
     */
    private static String centerAndSpace(
            final String theString,
            final int theLength,
            final boolean theCapitalize
    ) {

        // New String builder.
        StringBuilder space = new StringBuilder();

        // For all chars, append a string.
        for (char c : (theString.isEmpty() ? " " : theString)
                .toCharArray()) space.append(c).append(" ");

        // Fencepost
        space.deleteCharAt(space.length() - 1);

        // Space of the string.
        String spaceString = space.length() > theLength ?
                space.substring(0, theLength) : space.toString();

        // Repeat the two edges.
        spaceString = " ".repeat(
                (int) Math.floor((theLength - spaceString.length()) / 2.0)
        ) + spaceString + " ".repeat(
                (int) Math.ceil((theLength - spaceString.length()) / 2.0)
        );

        // Return a completed and capitalized string.
        return theCapitalize ? spaceString.toUpperCase(Locale.ROOT) :
                spaceString;
    }

    /**
     * Set the wall color of the generator.
     *
     * @param theWallColor Wall color.
     */
    public void setWallColor(final String theWallColor) {
        this.myWallColor = theWallColor;
    }

    /**
     * Set the color of the text to generate.
     *
     * @param theTextColor Color of text.
     */
    public void setTextColor(final String theTextColor) {
        this.myTextColor = theTextColor;
    }

}
