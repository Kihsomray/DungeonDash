package view.console.panel;

import model.entity.enemy.monster.Monster;
import model.entity.hero.Hero;
import view.console.pattern.Color;
import view.console.pattern.ProgressBar;

import static view.console.frame.ConsoleFrame.*;

/**
 * A type of console panel that generates the monster's info, if any.
 * Will remain blank if the hero is not battling a monster.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class MonsterInfoPanel extends ConsolePanel {

    /** Hero to generate info for. */
    private final Hero myHero;

    /**
     * Create an instance of monster info panel.
     *
     * @param theHero Hero to create the panel for.
     */
    public MonsterInfoPanel(final Hero theHero) {
        myHero = theHero;
    }


    @Override
    public String generate() {

        // String Builder.
        final StringBuilder sb = new StringBuilder();

        // Should monster info be generated?
        final boolean isMonster = myHero.getBattle() != null;

        // Top border.
        appendSimpleHorizontal(sb);

        // Text line if needed.
        appendTextVertical(sb, isMonster ?
                myHero.getBattle().getMonster().getName() :
                " ", true);

        // Splitter bar if needed.
        if (isMonster) appendSimpleHorizontal(sb);

        // Empty line.
        appendTextVertical(sb, " ", isMonster);

        if (isMonster) {

            // Get the monster.
            final Monster monster = myHero.getBattle().getMonster();

            // Generate the health bar.
            final String healthBar =
                    ProgressBar.generate(
                            monster.getHP(),
                            monster.getHP() +
                                    monster.getLastDamage(),
                            monster.getMaxHP(),
                            18
                    );

            // Append it.
            sb
                    .append(
                            PATTERN.generateVerticalBorder(1,
                                    LEFT_MENU_WIDTH,
                                    "  " + Color.WHITE + "HP:  [" +
                                            healthBar + Color.WHITE + "]  ",
                                    false,
                                    false)
                    )
                    .append('\n');

        }

        // Empty line.
        appendTextVertical(sb, " ", true);

        // Bottom border.
        appendSimpleHorizontal(sb);

        return sb.toString();
    }

}
