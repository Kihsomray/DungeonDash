package model.dungeon;

import controller.DungeonAdventure;
import model.dungeon.cell.passable.Door;
import model.dungeon.generator.PrimsGenerator;
import model.dungeon.generator.DungeonGenerator;
import model.dungeon.cell.Cell;
import model.entity.hero.Hero;

import java.io.Serializable;

/**
 * A dungeon contains the back-end data for the DungeonAdventure game. This
 * data ranges from a 2D array of mazes, the hero and all of its data, along
 * with any other necessary data.
 *
 * @version 1.0.0
 * @author Kihsomray
 * @author Patrick Hern
 */
public class Dungeon implements Serializable {

    /** Main instance of the program. */
    private final DungeonAdventure myMain;

    /** The dungeon/maze made of Rooms. */
    private final Cell[][] myMaze;

    /** Exit door of the dungeon. */
    private final Door myExit;

    /** Hero corresponding to this dungeon. */
    private final Hero myHero;

    /** Number of rooms in the dungeon. */
    private final int myRoomCount;


    /**
     * Constructor for the dungeon that creates a new dungeon
     * and fills it using private methods.
     */
    public Dungeon(
            final DungeonAdventure theMain,
            final int theWidth,
            final int theHeight,
            final Hero theHero
    ) {

        myMain = theMain;
        myHero = theHero;

        // Initialize the Dungeon using Prim's generator.
        final DungeonGenerator generator = new PrimsGenerator(theWidth, theHeight, theHero);

        // Generate the maze.
        myMaze = generator.generate();

        // Gets the exit.
        myExit = generator.getExit();

        // Gets the room count.
        myRoomCount = generator.getRoomCount();

    }


    /**
     * Gets the hero of this dungeon.
     *
     * @return Hero binded to this dungeon.
     */
    public Hero getHero() {
        return myHero;
    }

    /**
     * Gets the maze of the dungeon.
     *
     * @return Maze contained in the dungeon.
     */
    public Cell[][] getMaze() {
        return myMaze;
    }

    /**
     * Gets the number of rooms contained in the dungeon.
     *
     * @return Rooms in the dungeon.
     */
    public int getRoomCount() {
        return myRoomCount;
    }

    /**
     * Gets the width of the dungeon.
     *
     * @return Width of the dungeon.
     */
    public int getMyWidth() {
        return myMaze.length;
    }

    /**
     * Gets the height of the dungeon.
     *
     * @return Height of the dungeon.
     */
    public int getMyHeight() {
        return myMaze[0].length;
    }

    /**
     * Checks if player has walked through the exit.
     *
     * @return Is the game still being played.
     */
    public boolean isGamePlaying() {
        return !myExit.hasEntered();
    }

}
