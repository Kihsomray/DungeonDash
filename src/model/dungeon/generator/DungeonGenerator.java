package model.dungeon.generator;

import model.dungeon.cell.Cell;
import model.dungeon.cell.passable.Door;
import model.entity.hero.Hero;

/**
 * A dungeon generator is used to create a dungeon with cells containing walls
 * and rooms the hero can walk through.
 *
 * The dungeon generator will generate an entrance and place the hero on top
 * of it. An exit will be place elsewhere. Four pillars will be randomly
 * generated in some rooms.
 *
 * Every room will be accessible and there is a guaranteed path from the
 * entrance to every pillar and the exit. The starting coordinates of the
 * hero will be binded to it during the generation process.
 *
 * @version 1.0.0
 * @author Kihsomray
 * @author Patrick Hern
 */
public interface DungeonGenerator {

    /**
     * Generates a dungeon based on implementation.
     *
     * @return Generated 2D array of cells.
     */
    Cell[][] generate();


    /**
     * Gets the number of rooms in the generated dungeon.
     *
     * @return Number of rooms in dungeon.
     */
    int getRoomCount();

    /**
     * Gets the entrance of the dungeon.
     *
     * @return Entrance of the dungeon.
     */
    Door getEntrance();

    /**
     * Gets the exit of the dungeon.
     *
     * @return Exit of the dungeon.
     */
    Door getExit();

    /**
     * Gets the height of the dungeon.
     *
     * @return Height of dungeon.
     */
    int getHeight();

    /**
     * Gets the width of the dungeon.
     *
     * @return Width of dungeon.
     */
    int getWidth();

    /**
     * Gets the hero spawned in the dungeon.
     *
     * @return Hero spawned in the dungeon.
     */
    Hero getHero();

}
