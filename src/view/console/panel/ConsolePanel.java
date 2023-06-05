package view.console.panel;

import model.entity.hero.Hero;
import model.inventory.item.Item;
import view.console.frame.ConsoleFrame;
import view.console.pattern.Color;

import java.io.Serializable;

import static view.console.frame.ConsoleFrame.*;

public abstract class ConsolePanel implements Serializable {


    protected static void appendSimpleHorizontal(
            final StringBuilder theStringBuilder
    ) {

        // Append the horizontal border.
        theStringBuilder
                .append(PATTERN.generateHorizontalBorder(
                        LEFT_MENU_WIDTH
                ))
                .append('\n');

    }

    protected static void appendTextVertical(
            final StringBuilder theStringBuilder,
            final String theText,
            boolean theTripleBorder
    ) {

        // Append the vertical border with text.
        theStringBuilder
                .append(PATTERN.generateVerticalBorder(
                        theTripleBorder,
                        LEFT_MENU_WIDTH,
                        theText))
                .append('\n');

    }

    protected static void appendInventoryRow(
            final StringBuilder theStringBuilder,
            final Hero theHero,
            final int theStart,
            final int theEnd,
            final String theEmptyChar

    ) {

        for (int i = theStart; i < theEnd; i++) {
            final Item item = theHero.getInventory().getSlots()[i];
            theStringBuilder
                    .append(PATTERN.generateSingle())
                    .append(GENERIC_SEPARATOR_3)
                    .append(item == null ?
                            theEmptyChar :
                            item.getColoredDisplay())
                    .append(GENERIC_SEPARATOR_3);
        }
        theStringBuilder.append(PATTERN.generateSingle())
                .append('\n');

    }

    protected static void appendRowHeaderBar(
            final StringBuilder theStringBuilder,
            final int theStart,
            final int theEnd
    ) {

        for (int i = theStart + 1; i < theEnd + 1; i++) {
            theStringBuilder
                    .append(PATTERN.generateSingle())
                    .append(" -(")
                    .append(Color.WHITE)
                    .append(i)
                    .append(Color.GREY)
                    .append(")- ");
        }
        theStringBuilder.append(PATTERN.generateSingle())
                .append('\n');

    }

    public abstract String generate();


}
