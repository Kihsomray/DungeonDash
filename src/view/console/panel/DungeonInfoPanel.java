package view.console.panel;

import model.dungeon.Dungeon;
import model.dungeon.cell.Cell;
import model.dungeon.cell.passable.Passable;
import model.util.Utility;
import view.console.pattern.Color;

import java.util.LinkedList;
import java.util.Queue;

import static view.console.frame.ConsoleFrame.PATTERN;

public class DungeonInfoPanel extends ConsolePanel {

    private final Dungeon myDungeon;

    public DungeonInfoPanel(final Dungeon theDungeon) {
        myDungeon = theDungeon;
    }

    @Override
    public String generate() {

        // String builder.
        final StringBuilder sb = new StringBuilder();

        // Top wall of dungeon.
        appendHorizontal(sb);

        // Loop through the height.
        for (int i = myDungeon.getMyHeight() - 1; i >= 0; i--) {

            // Needs to be split in two halves
            Queue<String> lowerHalf = new LinkedList<>();

            // Add a typical segment.
            sb.append(PATTERN.generateSegment(5, true)).append(' ');

            // Loop through height of cells.
            for (final Cell[] cells : myDungeon.getMaze()) {

                // Get the cell.
                final Cell cell = cells[i];

                // See if the hero is at the current location.
                if (myDungeon.getHero().getCurrentPassable() == cell) {

                    // Append the character.
                    sb
                            .append(Color.WHITE)
                            .append(" _*_  ");
                    lowerHalf.add(Color.WHITE + " /^\\ ");

                // If extra visibility, make it a visual thing.
                } else if ((myDungeon.getHero().isExtraVisibility() &&
                        (Math.abs(cell.getX() - myDungeon.getHero()
                                .getCurrentPassable().getX()) < 3 &&
                                Math.abs(cell.getY() - myDungeon.getHero()
                                        .getCurrentPassable().getY()) < 3))
                ) {

                    // Split the cell display in half.
                    final String[] split = cell.toString().split("\n");

                    // Append the two halves.
                    sb.append(split[0]).append(' ');
                    lowerHalf.add(split[1]);

                // If it is a passable and hasn't discovered it yet.
                } else if (cell instanceof Passable &&
                        !myDungeon.getHero().hasDiscovered((Passable) cell)
                ) {

                    // Add a 'cloud'.
                    sb.append(Color.RED).append("***** ");
                    lowerHalf.add(Color.RED + "*****");

                } else {

                    // Split the cell.
                    String[] split = cell.toString().split("\n");

                    // Append the two halves.
                    sb.append(split[0]).append(' ');
                    lowerHalf.add(split[1]);

                }

            }

            // Append edge wall.
            sb
                    .append(PATTERN.generateSegment(5, true))
                    .append("\n")
                    .append(PATTERN.generateSegment(5, true))
                    .append(' ');

            // Append the lower half now.
            while (!lowerHalf.isEmpty()) sb.append(lowerHalf.poll()).append(' ');

            // Append lower half edge.
            sb.append(PATTERN.generateSegment(5, true)).append('\n');

        }

        // Bottom wall of dungeon.
        appendHorizontal(sb);

        // Return string.
        return sb.toString();
    }

    private void appendHorizontal(final StringBuilder theStringBuilder) {

        // Loop through 2 height.
        for (int i = 0; i < 2; i++) {

            // Color of wall.
            theStringBuilder.append(Color.GREY);

            // Loop through width of dungeon.
            for (int j = 0; j < myDungeon.getMyWidth() + 1; j++) {

                // Append a segment.
                theStringBuilder
                        .append(PATTERN.generateSegment(5, false))
                        .append(' ');
            }

            // New line char.
            theStringBuilder.append(PATTERN.generateSegment(5, false)).append("\n");

        }

    }

}
