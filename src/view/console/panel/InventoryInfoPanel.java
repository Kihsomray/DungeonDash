package view.console.panel;

import model.entity.hero.Hero;
import view.console.pattern.Color;

public class InventoryInfoPanel extends ConsolePanel {

    private final Hero myHero;

    public InventoryInfoPanel(final Hero theHero) {
        myHero = theHero;
    }

    @Override
    public String generate() {

        // String builder.
        final StringBuilder sb = new StringBuilder();

        // Top border of inventory info.
        appendSimpleHorizontal(sb);

        // Type of hero displayed.
        appendTextVertical(sb, "INVENTORY", true);

        // Middle border of inventory info.
        appendSimpleHorizontal(sb);

        // Pillar inventory bar.
        appendInventoryRow(sb, myHero, 0, 4, Color.YELLOW + "?");

        // Usable items header 1.
        appendRowHeaderBar(sb, 0, 4);

        // Usable items row 1.
        appendInventoryRow(sb, myHero,4, 8, Color.GREEN + "+");

        // Usable items header 2.
        appendRowHeaderBar(sb,4, 8);

        // Usable items row 1.
        appendInventoryRow(sb, myHero,8, 12, Color.GREEN + "+");

        // Bottom border of inventory info.
        appendSimpleHorizontal(sb);

        return sb.toString();

    }

}
