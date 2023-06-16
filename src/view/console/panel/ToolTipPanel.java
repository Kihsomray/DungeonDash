package view.console.panel;

import model.entity.hero.Hero;

import static view.console.frame.ConsoleFrame.PATTERN;
import static view.console.frame.ConsoleFrame.RIGHT_MENU_WIDTH;

/**
 * A type of console panel that displays a message related to the game.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class ToolTipPanel extends ConsolePanel {

    /** Hero to generate info for. */
    private final Hero myHero;

    /** Current message to display. */
    private String myMessage;


    /**
     * Create an instance of tooltip panel.
     *
     * @param theHero Hero to create the panel for.
     */
    public ToolTipPanel(final Hero theHero) {

        myHero = theHero;

        myMessage = "\nUSE WASD TO MOVE AROUND";

    }


    @Override
    public String generate() {

        // New string builder.
        final StringBuilder sb = new StringBuilder();

        String[] s = myMessage.split("\n");

        sb
                .append(PATTERN.generateHorizontalBorder(RIGHT_MENU_WIDTH))
                .append('\n')
                .append(myHero.getBattle() != null ?
                                PATTERN.generateVerticalBorder(
                                        true, RIGHT_MENU_WIDTH) + "\n" :
                        ""
                        )
                .append(PATTERN.generateVerticalBorder(
                        myHero.getBattle() == null,
                        RIGHT_MENU_WIDTH, s.length > 0 ? s[0] : " "))
                .append('\n')
                .append(PATTERN.generateVerticalBorder(
                        false,
                        RIGHT_MENU_WIDTH, s.length > 1 ? s[1] : " "))
                .append('\n')
                .append(PATTERN.generateVerticalBorder(
                        myHero.getBattle() == null,
                        RIGHT_MENU_WIDTH, s.length > 2 ? s[2] : " "))
                .append('\n')
                .append(myHero.getBattle() != null ?
                        PATTERN.generateVerticalBorder(
                                true, RIGHT_MENU_WIDTH) + "\n" :
                        ""
                )
                .append(PATTERN.generateHorizontalBorder(RIGHT_MENU_WIDTH))
                .append('\n');

        return sb.toString();

    }

    /**
     * Set the message being display on the panel.
     *
     * @param theMessage Message to display next.
     */
    public void setMessage(final String theMessage) {
        myMessage = theMessage;
    }

}
