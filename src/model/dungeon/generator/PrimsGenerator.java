package model.dungeon.generator;

import model.Utility;
import model.dungeon.tile.Cell;
import model.dungeon.tile.Wall;
import model.dungeon.tile.room.Room;
import model.entity.hero.Hero;

import java.util.*;

/**
 * A type of DungeonGenerator that utilizes Prim's Algorithm to randomly place
 * rooms in a map full of walls. Generally this is a very basic implementation.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class PrimsGenerator implements DungeonGenerator {

    /** Height of dungeon. */
    private final int myHeight;

    /** Width of dungeon. */
    private final int myWidth;

    /** Hero to place in dungeon. */
    private final Hero myHero;

    /** Cell representation of dungeon. */
    private Cell[][] myCells;

    /** Used to keep track of surrounding walls. */
    private final List<Wall> myWalls;

    /** Number of rooms within the generated dungeon. */
    private int myRoomCounter;

    /**
     * Base constructor for Prim's Generator.
     *
     * @param theHeight Height of dungeon.
     * @param theWidth Width of dungeon.
     * @param theHero Hero to place into dungeon.
     */
    public PrimsGenerator(int theHeight, int theWidth, final Hero theHero) {

        myHeight = theHeight;
        myWidth = theWidth;
        myHero = theHero;

        myWalls = new ArrayList<>();
        myRoomCounter = 0;

    }

    @Override
    public Cell[][] generate() {

        // Generate new cell 2D array.
        myCells = new Cell[myHeight][myWidth];
        fillWalls();

        // Empty the walls.
        myWalls.clear();

        // Set room counter to 0.
        myRoomCounter = 1;

        // Start at bottom right.
        myCells[0][0] = new Room(true);

        // Add the surrounding.
        addSurrounding(0, 0);

        // While we have walls to look at.
        while (!myWalls.isEmpty()) {

            // Get a random wall.
            Wall wall = myWalls.get(Utility.RANDOM.nextInt(myWalls.size()));

            // Get its coordinates.
            final int x = wall.getX();
            final int y = wall.getY();

            // If it should be room.
            if (checkSurrounding(x, y)) {

                // Change it to a room.
                myCells[x][y] = new Room(true);

                // Increment room counter.
                myRoomCounter++;

                // Add surrounding walls.
                addSurrounding(x, y);

            }

            // Remove the observed wall.
            myWalls.remove(wall);

        }

        // Return generated 2D array.
        return myCells;

    }

    /**
     * Checks the surroundings if the current wall should be a room instead.
     *
     * @param theX X coordinate of cell.
     * @param theY Y coordinate of cell.
     * @return If the wall is suitable to be a room.
     */
    private boolean checkSurrounding(final int theX, final int theY) {

        // Counter for found rooms
        int found = 0;

        // Get the north wall.
        if (getCellAt(theX, theY + 1) instanceof Room) found++;

        // Get the east wall.
        if (getCellAt(theX + 1, theY) instanceof Room) found++;

        // Get the south wall.
        if (getCellAt(theX, theY - 1) instanceof Room) found++;

        // Get the west wall.
        if (getCellAt(theX - 1, theY) instanceof Room) found++;

        // Return the found amount
        return found == 1;

    }

    /**
     * Adds all the surrounding walls of a cell if possible
     *
     * @param theX X coordinate of cell.
     * @param theY Y coordinate of cell.
     */
    private void addSurrounding(final int theX, final int theY) {

        // Get the north wall.
        addIfWall(theX, theY + 1);

        // Get the east wall.
        addIfWall(theX + 1, theY);

        // Get the south wall.
        addIfWall(theX, theY - 1);

        // Get the west wall.
        addIfWall(theX - 1, theY);

    }

    /**
     * Adds to wall list if the given cell is a wall.
     *
     * @param theX X coordinate of cell.
     * @param theY Y coordinate of cell.
     */
    private void addIfWall(final int theX, final int theY) {

        // Get the surrounding cells, making sure no IOBE.
        Cell cell = getCellAt(theX, theY);

        // If a wall, return the wall, otherwise, null.
        if (cell instanceof Wall) myWalls.add((Wall) cell);

    }

    /**
     * Gets a cell at a certain location.
     *
     * @param theX X coordinate of cell.
     * @param theY Y coordinate of cell.
     * @return Cell at that location if not IOB. Otherwise, null.
     */
    private Cell getCellAt(final int theX, final int theY) {

        // X out of bounds.
        if (theX > myHeight - 1 || theX < 0) return null;

        // Y out of bounds.
        if (theY > myWidth - 1 || theY < 0) return null;

        // Return cell at location
        return myCells[theX][theY];

    }

    /**
     * Fills the 2D array of Cells to contain strictly walls.
     */
    private void fillWalls() {

        // Loop through height
        for (int i = 0; i < myHeight; i++) {

            // Loop through width
            for (int j = 0; j < myWidth; j++) {

                // Set to wall at that coordinate
                myCells[i][j] = new Wall(i, j);

            }

        }

    }

    @Override
    public int getRoomCount() {
        return myRoomCounter;
    }

    @Override
    public int getHeight() {
        return myHeight;
    }

    @Override
    public int getWidth() {
        return myWidth;
    }

    @Override
    public Hero getHero() {
        return myHero;
    }

}
