package view.console.frame;

import view.console.pattern.PatternGenerator;

/**
 * A frame to display content to the console.
 *
 * @author Kihsomray
 * @version 1.0.0
 */
public abstract class ConsoleFrame {

    /** Create an instance of pattern generator. */
    protected static final PatternGenerator PATTERN = new PatternGenerator(true);

    /** Width of character display. */
    protected static final int TOTAL_FRAME_WIDTH = 133;

    /** Width of display that shows the type of hero. */
    protected static final int LEFT_MENU_WIDTH = 33;

    /** Width of separator between stats and other panel. */
    protected static final String MENU_SEGMENT_SEPARATOR_5 = "     ";

    /** Width of separator between hero characters. */
    protected static final String GENERIC_SEPARATOR_3 = "   ";

}
