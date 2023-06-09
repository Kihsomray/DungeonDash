package view.console.panel;

import model.entity.hero.Hero;
import view.console.pattern.Color;

/**
 * A type of console panel that generates the inventory's info.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class InventoryInfoPanel extends ConsolePanel {

    /** Hero to generate info for. */
    private final Hero myHero;


    /**
     * Create an instance of inventory info panel.
     *
     * @param theHero Hero to create the panel for.
     */
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
