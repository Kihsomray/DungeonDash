package view.console.frame;

import model.util.Utility;
import view.console.pattern.PatternGenerator;

import java.io.Serializable;
import java.util.Locale;

/**
 * A frame to display content to the console.
 *
 * @author Kihsomray
 * @version 1.0.0
 */
public abstract class ConsoleFrame implements Serializable {

    /** Create an instance of pattern generator. */
    public static final PatternGenerator PATTERN = new PatternGenerator(true);

    /** Width of frame display. */
    public static final int TOTAL_FRAME_WIDTH = 133;

    /** Width of display that shows left panel. */
    public static final int LEFT_MENU_WIDTH = 33;

    /** Width of display that shows right panel. */
    public static final int RIGHT_MENU_WIDTH = 95;

    /** Width of separator between right and left panels. */
    public static final String MENU_SEGMENT_SEPARATOR_5 = "     ";

    /** Generic width separator. */
    public static final String GENERIC_SEPARATOR_3 = "   ";

    protected static char getInput() {

        // Get the user input.
        String input = Utility.SCANNER
                .nextLine()
                .toUpperCase(Locale.ROOT);

        return input.length() > 0 ? input.charAt(0) : ' ';

    }


}
