package view.console;

import model.entity.hero.Hero;
import model.entity.hero.Priestess;
import model.entity.hero.Thief;
import model.entity.hero.Warrior;
import model.util.Utility;
import view.console.pattern.Color;
import view.console.pattern.PatternGenerator;
import view.console.pattern.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public final class ConsoleUtility {

    /** Create an instance of pattern generator. */
    private static final PatternGenerator PATTERN = new PatternGenerator(true);

    /** Progress bar width */
    private static final int PROGRESS_BAR_WIDTH = 18;

    /** Width of display that shows the type of hero. */
    private static final int CHARACTER_MENU_HEADER_WIDTH = 33;

    /** With of character statistics display. */
    private static final int CHARACTER_MENU_STATS_WIDTH = 95;

    /** Width of separator between header and stats display. */
    private static final String CHARACTER_MENU_SEGMENT_SEPARATOR = "     ";

    /** Width of character display. */
    private static final int CHARACTER_MENU_WIDTH = 133;

    /** Width of separator between hero characters. */
    private static final String CHARACTER_MENU_HERO_SEPARATOR = "   ";

    /** Used to set the position of characters on the display. */
    private static final char[] CHARACTER_POSITIONING = {'W', 'T', 'P'};


    public static String generateCharacterMenu(final char theHero, final String theMessage) {

        final StringBuilder sb = new StringBuilder();
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
                        CHARACTER_MENU_HEADER_WIDTH,
                        hero.getClass().getSimpleName(),
                        true)
                )
                .append(CHARACTER_MENU_SEGMENT_SEPARATOR)
                .append(PATTERN.generateVerticalBorder(
                        1,
                        CHARACTER_MENU_STATS_WIDTH,
                        heroStats,
                        false)
                )
                .append('\n');


        // Walls of each segment.
        appendVerticalBorderHeaderStats(sb);

        // Bottom border of header and stats.
        appendHorizontalBorderHeaderStats(sb);

        // ------- HERO DISPLAY -------
        sb
                .append(" ".repeat(CHARACTER_MENU_WIDTH))
                .append('\n');

        // Top border of character selection.
        sb.append(generateSimpleHorizontal());

        // Wall of character selection.
        sb.append(generateSimpleVertical());

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
                    CHARACTER_MENU_HERO_SEPARATOR +
                    Color.getColor(1 == chosen  ? '2' : '3') +
                    characters.get(1)[i] +
                    CHARACTER_MENU_HERO_SEPARATOR +
                    Color.getColor(2 == chosen  ? '2' : '3') +
                    characters.get(2)[i];

            // Append the hero line.
            sb.append(PATTERN.generateVerticalBorder(
                    1,
                    CHARACTER_MENU_WIDTH,
                    heroLine, false))
                    .append('\n');

        }

        // Wall of character selection.
        sb.append(generateSimpleVertical());

        // Bottom border of character selection.
        sb.append(generateSimpleHorizontal());


        // ------- TIP TOOLBAR -------
        sb
                .append(" ".repeat(CHARACTER_MENU_WIDTH))
                .append('\n');

        // Top border of tip toolbar.
        sb.append(generateSimpleHorizontal());

        // Wall of tip toolbar.
        sb.append(generateSimpleVertical());

        for (String line : theMessage.split("\n"))
            sb
                    .append(PATTERN.generateVerticalBorder(false, CHARACTER_MENU_WIDTH, line))
                    .append('\n');

        // Wall of tip toolbar.
        sb.append(generateSimpleVertical());

        // Bottom border of tip toolbar.
        sb.append(generateSimpleHorizontal());

        // Return completed string
        return sb.toString();

    }

    private static void appendHorizontalBorderHeaderStats(
            final StringBuilder theStringBuilder
    ) {

        // Append the border.
        theStringBuilder
                .append(PATTERN.generateHorizontalBorder(CHARACTER_MENU_HEADER_WIDTH))
                .append(CHARACTER_MENU_SEGMENT_SEPARATOR)
                .append(PATTERN.generateHorizontalBorder(CHARACTER_MENU_STATS_WIDTH))
                .append('\n');

    }

    private static void appendVerticalBorderHeaderStats(
            final StringBuilder theStringBuilder
    ) {

        // Append the border.
        theStringBuilder
                .append(PATTERN.generateVerticalBorder(true, CHARACTER_MENU_HEADER_WIDTH))
                .append(CHARACTER_MENU_SEGMENT_SEPARATOR)
                .append(PATTERN.generateVerticalBorder(true, CHARACTER_MENU_STATS_WIDTH))
                .append('\n');

    }

    private static String generateSimpleHorizontal() {
        return PATTERN.generateHorizontalBorder(CHARACTER_MENU_WIDTH) + '\n';
    }

    private static String generateSimpleVertical() {
        return PATTERN.generateVerticalBorder(true, CHARACTER_MENU_WIDTH) +
                '\n';
    }


}
