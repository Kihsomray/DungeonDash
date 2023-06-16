package model.dungeon.cell;

import java.io.Serializable;

/**
 * A cell represents a location in a maze with given data.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public interface Cell extends Serializable {

    /**
     * Get the X coordinate of the cell.
     *
     * @return X coordinate of the cell.
     */
    int getX();

    /**
     * Get the Y coordinate of the cell.
     *
     * @return Y coordinate of the cell.
     */
    int getY();

    String getArtPath();
}
