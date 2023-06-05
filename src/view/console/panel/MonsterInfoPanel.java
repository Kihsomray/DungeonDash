package view.console.panel;

import model.entity.enemy.monster.Monster;
import model.entity.hero.Hero;
import view.console.pattern.Color;
import view.console.pattern.ProgressBar;

import static view.console.frame.ConsoleFrame.*;

public class MonsterInfoPanel extends ConsolePanel {

    private final Hero myHero;

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

            System.out.println(monster.getHP() + ", " + monster.getLastDamage());

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
