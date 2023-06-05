package view.console.panel;

import model.entity.hero.Hero;
import view.console.pattern.Color;
import view.console.pattern.ProgressBar;

import static view.console.frame.ConsoleFrame.*;

public class HeroInfoPanel extends ConsolePanel {

    private final Hero myHero;

    public HeroInfoPanel(final Hero theHero) {
        myHero = theHero;
    }

    @Override
    public String generate() {

        // String builder.
        final StringBuilder sb = new StringBuilder();

        // Top border of hero info.
        appendSimpleHorizontal(sb);

        // Type of hero displayed.
        appendTextVertical(sb,
                "HERO-" + myHero.getClass().getSimpleName(),
                false
        );

        // Set color to green for next append.
        PATTERN.setTextColor(Color.GREEN);

        // Name of hero
        appendTextVertical(sb, myHero.getName(), true);

        // Middle border of hero info.
        appendSimpleHorizontal(sb);

        // Set color back to white.
        PATTERN.setTextColor(Color.WHITE);

        // Empty with vertical borders.
        appendTextVertical(sb, " ", false);

        // Current hero health bar.
        final String healthBar =
                myHero.getBattle() == null ?
                        ProgressBar.generate(
                                myHero.getHP(),
                                myHero.getMaxHP(),
                                18
                        ) :
                        ProgressBar.generate(
                                myHero.getHP(),
                                myHero.getHP() +
                                        myHero.getLastDamage(),
                                myHero.getMaxHP(),
                                18
                        );

        // Hero HP information.
        sb
                .append(
                        PATTERN.generateVerticalBorder(1,
                                LEFT_MENU_WIDTH,
                                GENERIC_SEPARATOR_3 + Color.WHITE + "HP:   [" +
                                        healthBar + Color.WHITE + "]",
                                false,
                                false)
                )
                .append('\n');

        // Current hero ability bar.
        final String abilityBar = ProgressBar.generate(
                myHero.getBattle() != null ?
                        (myHero.getBattle().hasAbility() ? 1 : 0) :
                        1, 1, 18);

        // Hero ability information.
        sb
                .append(
                        PATTERN.generateVerticalBorder(1,
                                LEFT_MENU_WIDTH,
                                Color.WHITE + "ABILITY: [" +
                                        abilityBar + Color.WHITE + "]",
                                false,
                                false)
                )
                .append('\n');

        // Top of coordinate box.
        sb
                .append(
                        PATTERN.generateVerticalBorder(
                                1,
                                LEFT_MENU_WIDTH,
                                " ".repeat(6) + "_".repeat(17) + " ".repeat(6),
                                false,
                                false)
                )
                .append('\n');

        // Coordinates of hero.
        final int x = myHero.getCurrentPassable().getX();
        final int y = myHero.getCurrentPassable().getY();

        // Current hero coordinate info.
        final String coordinateInfo = "     |  " + Color.WHITE + "X:" + Color.CYAN +
                (x > 8 ? "" : "0") + (x + 1) + Color.GREY + "  |  " +
                Color.WHITE + "Y:" + Color.CYAN + (y > 8 ? "" : "0") +
                (y + 1) + Color.GREY + "  |     ";

        // Hero coordinate info.
        sb
                .append(
                        PATTERN.generateVerticalBorder(
                                1,
                                LEFT_MENU_WIDTH,
                                coordinateInfo,
                                false,
                                false
                        )
                )
                .append('\n');

        // Bottom border of hero info.
        appendSimpleHorizontal(sb);

        return sb.toString();

    }

}
