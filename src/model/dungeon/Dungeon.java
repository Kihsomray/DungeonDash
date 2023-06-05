package model.dungeon;

import controller.DungeonAdventure;
import model.dungeon.generator.PrimsGenerator;
import model.dungeon.generator.DungeonGenerator;
import model.dungeon.cell.Cell;
import model.entity.hero.Hero;

import java.io.Serializable;

/**
 *
 * @author Kihsomray
 * @author Patrick Hern
 */
public class Dungeon implements Serializable {

    private final DungeonAdventure myMain;

    /** The dungeon/maze made of Rooms. */
    private final Cell[][] myMaze;

    private final Hero myHero;

    private final int myRoomCount;
    private final int myWidth;
    private final int myHeight;

    private boolean myGamePlaying;

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
        myWidth = theWidth;
        myHeight = theHeight;

        // Initialize the Dungeon using Prim's generator.
        final DungeonGenerator generator = new PrimsGenerator(theWidth, theHeight, theHero);

        // Generate the maze.
        myMaze = generator.generate();

        // Gets the room count.
        myRoomCount = generator.getRoomCount();

        // Game is playing.
        myGamePlaying = true;

    }

    public void setGamePlaying(final boolean theGamePlaying) {
        this.myGamePlaying = theGamePlaying;
    }

    public Hero getHero() {
        return myHero;
    }

    public Cell[][] getMaze() {
        return myMaze;
    }

    public int getRoomCount() {
        return myRoomCount;
    }

    public int getMyWidth() {
        return myWidth;
    }

    public int getMyHeight() {
        return myHeight;
    }

    public boolean isGamePlaying() {
        return myGamePlaying;
    }

}
