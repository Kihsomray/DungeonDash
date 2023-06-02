package model.dungeon.cell.passable;

import model.dungeon.cell.Cell;
import model.entity.hero.Hero;

public interface Passable extends Cell {

    /**
     * Neighboring passable cells.
     *
     * @return Neighbors.
     */
    Neighbors getNeighbors();

    void interactWith(final Hero theHero);

}
