package model.dungeon.cell;

import java.io.File;

import static view.console.frame.ConsoleFrame.PATTERN;

/**
 * A type of cell that poses as a wall. A cell type the hero should not
 * pass through.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class Wall implements Cell {

    private static final String ART_PATH = "res" + File.separator + "DarkTile.png";

    /** X coordinate of this cell. */
    private final int myX;

    /** Y coordinate of this cell. */
    private final int myY;


    /**
     * Creates an instance of a Wall.
     *
     * @param theX X coordinate of this cell.
     * @param theY Y coordinate of this cell.
     */
    public Wall(final int theX, final int theY) {

        myX = theX;
        myY = theY;

    }

    @Override
    public String getArtPath() {
        return ART_PATH;
    }

    @Override
    public int getX() {
        return myX;
    }

    @Override
    public int getY() {
        return myY;
    }

    /**
     * Gets the string representation of the wall with color.
     *
     * @return String representation of the wall.
     */
    @Override
    public String toString() {
        return PATTERN.generateSegment(5, true) + "\n" +
                PATTERN.generateSegment(5, true) ;
    }

}
