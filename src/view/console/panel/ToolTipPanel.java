package view.console.panel;

import model.entity.hero.Hero;

import static view.console.frame.ConsoleFrame.PATTERN;
import static view.console.frame.ConsoleFrame.RIGHT_MENU_WIDTH;

public class ToolTipPanel extends ConsolePanel {

    private final Hero myHero;

    private String myMessage;

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

    public void setMessage(final String theMessage) {
        myMessage = theMessage;
    }

}
