package model.dungeon.tile.passable;

import model.dungeon.tile.Cell;
import model.sprite.hero.Hero;

public interface Passable extends Cell {

    /**
     * Neighboring passable cells.
     *
     * @return Neighbors.
     */
    Neighbors getNeighbors();

    void interactWith(final Hero theHero);

}
