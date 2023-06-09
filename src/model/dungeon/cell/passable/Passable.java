package model.dungeon.cell.passable;

import model.dungeon.cell.Cell;
import model.dungeon.cell.passable.info.Neighbors;
import model.entity.hero.Hero;

/**
 * A type of cell that a hero can pass through.
 *
 * Stores surrounding neighbors.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public interface Passable extends Cell {

    /**
     * Called when a hero passed through.
     *
     * @param theHero Hero in question.
     */
    void interactWith(final Hero theHero);


    /**
     * Neighboring passable cells.
     *
     * @return Neighbors.
     */
    Neighbors getNeighbors();

}
