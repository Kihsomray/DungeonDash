package view.console.frame;

import model.inventory.item.Item;
import view.console.pattern.Color;
import view.console.pattern.PatternGenerator;

/**
 * A frame to display content to the console.
 *
 * @author Kihsomray
 * @version 1.0.0
 */
public abstract class ConsoleFrame {

    /** Create an instance of pattern generator. */
    public static final PatternGenerator PATTERN = new PatternGenerator(true);

    /** Width of character display. */
    public static final int TOTAL_FRAME_WIDTH = 133;

    /** Width of display that shows the type of hero. */
    public static final int LEFT_MENU_WIDTH = 33;

    /** Width of separator between stats and other panel. */
    public static final String MENU_SEGMENT_SEPARATOR_5 = "     ";

    /** Width of separator between hero characters. */
    public static final String GENERIC_SEPARATOR_3 = "   ";


}
