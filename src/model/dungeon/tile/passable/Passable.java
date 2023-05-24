package model.dungeon.tile.passable;

import model.dungeon.tile.Cell;

public interface Passable extends Cell {

    /**
     * Neighboring passable cells.
     *
     * @return Neighbors.
     */
    Neighbors getNeighbors();

}
