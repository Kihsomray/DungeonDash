package view.console.frame;

import model.entity.hero.Hero;
import model.entity.hero.Priestess;
import model.entity.hero.Thief;
import model.entity.hero.Warrior;
import model.util.Utility;
import view.console.pattern.Color;
import view.console.pattern.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Console GUI utilities to display game information.
 *
 * @version 1.0.2
 * @author Kihsomray
 */
public final class HeroSelectionFrame extends ConsoleFrame {

    /** Progress bar width */
    private static final int PROGRESS_BAR_WIDTH = 18;

    /** Used to set the position of characters on the display. */
    private static final char[] CHARACTER_POSITIONING = {'P', 'W', 'T'};

    /** Message to display in tooltip. */
    private static final String MESSAGE = "TIP: W for warrior, T for thief, P for priestess\n" +
            "Type letter twice to confirm.";

    /**
     * Display the hero selection panel.
     *
     * @return Selected hero by user as a character.
     */
    public char display() {

        // Generate menu with no one selected.
        System.out.println(generate('W'));

        // Character that was previously chosen.
        char chosen = ' ';

        // Character that is currently selected.
        char input = 'W';

        // While it hasn't been selected twice.
        while (chosen != input) {

            // Set the current to the previous.
            chosen = input;

            // Get the console input.
            input = getInput();

            // Check the console input & update UI based on it.
            if (input == 'W' || input == 'T' || input == 'P')
                System.out.println(generate(input));

        }

        return chosen;

    }

    /**
     * Generate a character menu based on a selected hero.
     *
     * @param theHero Hero to display.
     * @return A string representation of the menu.
     */
    private String generate(final char theHero) {

        final StringBuilder sb = new StringBuilder("\n\n\n\n\n\n\n\n\n\n");
        final Hero hero = Utility.generateHeroFromChar(theHero, "");

        // Top border of header and stats.
        appendHorizontalBorderHeaderStats(sb);

        // Walls of each segment.
        appendVerticalBorderHeaderStats(sb);

        // Hero statistics.
        final String heroStats =
                Color.WHITE +
                        "    HP: [" +
                        ProgressBar.generate(
                                hero.getHP(),
                                Hero.getAllHeroMaxHP(),
                                PROGRESS_BAR_WIDTH
                        ) +
                        Color.WHITE +
                        "]  DAMAGE: [" +
                        ProgressBar.generate(
                                hero.getMinDamage(),
                                hero.getMaxDamage(),
                                Hero.getAllHeroMaxDamage(),
                                PROGRESS_BAR_WIDTH
                        ) +
                        Color.WHITE +
                        "]  SPEED: [" +
                        ProgressBar.generate(
                                hero.getAttackSpeed(),
                                Hero.getAllHeroMaxAttackSpeed(),
                                PROGRESS_BAR_WIDTH
                        ) +
                        Color.WHITE +
                        "]    ";


        // Hero name & statistics append.
        sb
                .append(PATTERN.generateVerticalBorder(
                        1,
                        LEFT_MENU_WIDTH,
                        hero.getClass().getSimpleName(),
                        true,
                        true)
                )
                .append(MENU_SEGMENT_SEPARATOR_5)
                .append(PATTERN.generateVerticalBorder(
                        1,
                        RIGHT_MENU_WIDTH,
                        heroStats,
                        false,
                        false)
                )
                .append('\n');


        // Walls of each segment.
        appendVerticalBorderHeaderStats(sb);

        // Bottom border of header and stats.
        appendHorizontalBorderHeaderStats(sb);

        // ------- HERO DISPLAY -------
        sb
                .append(" ".repeat(TOTAL_FRAME_WIDTH))
                .append('\n');

        // Top border of character selection.
        appendSimpleHorizontal(sb);

        // Wall of character selection.
        appendSimpleVertical(sb);

        // Each hero's skin.
        List<String[]> characters = new ArrayList<>();

        int chosen = 0;
        for (int i = 0; i < CHARACTER_POSITIONING.length; i++) {
            char c = CHARACTER_POSITIONING[i];
            switch (c) {
                case 'W' -> characters.add(Warrior.ASCII_SKIN.split("\n"));
                case 'T' -> characters.add(Thief.ASCII_SKIN.split("\n"));
                case 'P' -> characters.add(Priestess.ASCII_SKIN.split("\n"));
                default -> throw new IllegalArgumentException("Invalid character");
            }
            if (c == hero.getDisplayChar()) chosen = i;

        }

        for (int i = 0; i < 20; i++) {

            // Create te hero line.
            final String heroLine = Color.getColor(0 == chosen  ? '2' : '3') +
                    characters.get(0)[i] +
                    GENERIC_SEPARATOR_3 +
                    Color.getColor(1 == chosen  ? '2' : '3') +
                    characters.get(1)[i] +
                    GENERIC_SEPARATOR_3 +
                    Color.getColor(2 == chosen  ? '2' : '3') +
                    characters.get(2)[i];

            // Append the hero line.
            sb.append(PATTERN.generateVerticalBorder(
                            1,
                            TOTAL_FRAME_WIDTH,
                            heroLine,
                            false,
                            false))
                    .append('\n');

        }

        // Wall of character selection.
        appendSimpleVertical(sb);

        // Bottom border of character selection.
        appendSimpleHorizontal(sb);


        // ------- TIP TOOLBAR -------
        sb
                .append(" ".repeat(TOTAL_FRAME_WIDTH))
                .append('\n');

        // Top border of tip toolbar.
        appendSimpleHorizontal(sb);

        // Wall of tip toolbar.
        appendSimpleVertical(sb);

        for (String line : MESSAGE.split("\n"))
            sb
                    .append(PATTERN.generateVerticalBorder(false, TOTAL_FRAME_WIDTH, line))
                    .append('\n');

        // Wall of tip toolbar.
        appendSimpleVertical(sb);

        // Bottom border of tip toolbar.
        appendSimpleHorizontal(sb);

        // Return completed string
        return sb.toString();

    }

    /**
     * Appends a horizontal border for header and stats.
     *
     * @param theStringBuilder StringBuild to append to.
     */
    private static void appendHorizontalBorderHeaderStats(
            final StringBuilder theStringBuilder
    ) {

        // Append the border.
        theStringBuilder
                .append(PATTERN.generateHorizontalBorder(LEFT_MENU_WIDTH))
                .append(MENU_SEGMENT_SEPARATOR_5)
                .append(PATTERN.generateHorizontalBorder(RIGHT_MENU_WIDTH))
                .append('\n');

    }

    /**
     * Appends a vertical border for header and stats.
     *
     * @param theStringBuilder StringBuild to append to.
     */
    private static void appendVerticalBorderHeaderStats(
            final StringBuilder theStringBuilder
    ) {

        // Append the border.
        theStringBuilder
                .append(PATTERN.generateVerticalBorder(
                        true,
                        LEFT_MENU_WIDTH)
                )
                .append(MENU_SEGMENT_SEPARATOR_5)
                .append(PATTERN.generateVerticalBorder(
                        true,
                        RIGHT_MENU_WIDTH)
                )
                .append('\n');

    }

    /**
     * Appends a simple horizontal border spanning the total width.
     *
     * @param theStringBuilder StringBuild to append to.
     */
    private static void appendSimpleHorizontal(
            final StringBuilder theStringBuilder
    ) {

        // Append the horizontal border.
        theStringBuilder
                .append(PATTERN.generateHorizontalBorder(TOTAL_FRAME_WIDTH))
                .append('\n');

    }

    /**
     * Appends a simple vertical border spanning the total width.
     *
     * @param theStringBuilder StringBuild to append to.
     */
    private static void appendSimpleVertical(
            final StringBuilder theStringBuilder
    ) {

        // Append the vertical border.
        theStringBuilder
                .append(PATTERN.generateVerticalBorder(
                        true,
                        TOTAL_FRAME_WIDTH)
                )
                .append('\n');

    }


}
