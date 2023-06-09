package model.dungeon.cell;

import java.io.Serializable;

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

}
