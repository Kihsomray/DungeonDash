package model.dungeon.generator;

import model.util.Utility;
import model.dungeon.cell.Cell;
import model.dungeon.cell.Wall;
import model.dungeon.cell.passable.Door;
import model.dungeon.cell.passable.Passable;
import model.dungeon.cell.passable.info.Neighbors;
import model.dungeon.cell.passable.Room;
import model.entity.hero.Hero;
import model.inventory.item.collectable.Collectable;
import model.inventory.item.collectable.Pillar;

import java.util.*;

/**
 * A type of DungeonGenerator that utilizes Prim's Algorithm to randomly place
 * rooms in a map full of walls. Generally this is a very basic implementation.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class PrimsGenerator implements DungeonGenerator {

    /** Width of dungeon. */
    private final int myWidth;

    /** Height of dungeon. */
    private final int myHeight;

    /** Hero to place in dungeon. */
    private final Hero myHero;

    /** Cell representation of dungeon. */
    private Cell[][] myCells;

    /** Used to keep track of surrounding walls. */
    private final List<Wall> myWalls;

    /** Number of rooms within the generated dungeon. */
    private int myRoomCounter;

    /** Entrance room. */
    private Door myEntrance;

    /** Exit room. */
    private Door myExit;


    /**
     * Base constructor for Prim's Generator.
     *
     * @param theWidth Width of dungeon.
     * @param theHeight Height of dungeon.
     * @param theHero Hero to place into dungeon.
     */
    public PrimsGenerator(int theWidth, int theHeight, final Hero theHero) {

        myWidth = theWidth;
        myHeight = theHeight;
        myHero = theHero;

        myWalls = new ArrayList<>();
        myRoomCounter = 0;

        myEntrance = myExit = null;

    }


    @Override
    public Cell[][] generate() {

        // Generate new cell 2D array.
        myCells = new Cell[myWidth][myHeight];
        fillWalls();

        // Empty the walls.
        myWalls.clear();

        // Set room counter to 0.
        myRoomCounter = 1;

        // Start at bottom right.
        myCells[0][0] = myEntrance = new Door(0, 0, true);

        // Set the hero's location.
        myHero.setCurrentPassable(myEntrance);

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
                myCells[x][y] = new Room(x, y);

                // Increment room counter.
                myRoomCounter++;

                // Add surrounding walls.
                addSurrounding(x, y);

            }

            // Remove the observed wall.
            myWalls.remove(wall);

        }

        // Generate the entities.
        generateEntities();

        // Return generated 2D array.
        return myCells;

    }

    /**
     * Generates entities for this dungeon.
     */
    private void generateEntities() {

        // Gets a random number of steps.
        int steps = Utility.RANDOM.nextInt(
                myRoomCounter,
                myRoomCounter * 2
        ) - 1;

        // Get origin.
        Stack<PassableInfo> previous = new Stack<>();
        PassableInfo current = new PassableInfo(myEntrance);

        // Loop through the steps
        while (--steps >= 0) {

            // Set temp to current.
            final PassableInfo temp = current;

            // Set current to next.
            current = new PassableInfo(current.myPassable
                    .getNeighbors()
                    .getRandomNeighbor(current.myBlacklist));

            // If can't go anywhere.
            if (current.myPassable == null || previous.contains(temp)) {

                // Set it back to temp.
                PassableInfo last = previous.pop();
                last.addBlacklist(temp.myPassable);
                current = last;
                ++steps;

            } else {

                previous.push(temp);

            }

        }

        // Get the coordinates.
        final int passX = current.myPassable.getX();
        final int passY = current.myPassable.getY();

        // Set the exit.
        myCells[passX][passY] = myExit
                = new Door(passX, passY, false);

        // Add the surroundings.
        addSurrounding(passX, passY);

        // All the pillars.
        Stack<Collectable> collectables = new Stack<>();
        collectables.addAll(List.of(
                new Pillar(Pillar.Principle.ABSTRACTION),
                new Pillar(Pillar.Principle.ENCAPSULATION),
                new Pillar(Pillar.Principle.INHERITANCE),
                new Pillar(Pillar.Principle.POLYMORPHISM)
        ));

        // Add 4 pillars.
        while (!collectables.isEmpty()) {

            // Choose a random location.
            int i = Utility.RANDOM.nextInt(myWidth);
            int j = Utility.RANDOM.nextInt(myHeight);

            // Get the cell at that location.
            final Cell cell = getCellAt(i, j);

            // If it's not a room, skip it.
            if (!(cell instanceof Room)) continue;

            // Otherwise, add the pillar to that spot.
            ((Room) cell).getInventory().addItem(collectables.pop());
        }

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
        if (isPassable(theX, theY + 1)) found++;

        // Get the east wall.
        if (isPassable(theX + 1, theY)) found++;

        // Get the south wall.
        if (isPassable(theX, theY - 1)) found++;

        // Get the west wall.
        if (isPassable(theX - 1, theY)) found++;

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

        final Neighbors neighbors = ((Passable) getCellAt(theX, theY)).getNeighbors();

        // Get the north wall.
        neighbors.setNorth(addIfWall(theX, theY + 1), true);

        // Get the east wall.
        neighbors.setEast(addIfWall(theX + 1, theY), true);

        // Get the south wall.
        neighbors.setSouth(addIfWall(theX, theY - 1), true);

        // Get the west wall.
        neighbors.setWest(addIfWall(theX - 1, theY), true);

    }

    /**
     * Adds to wall list if the given cell is a wall.
     * Adds to theRooms set if given cell is a room.
     *
     * @param theX X coordinate of cell.
     * @param theY Y coordinate of cell.
     */
    private Passable addIfWall(final int theX, final int theY) {

        // Get the surrounding cells, making sure no IOBE.
        Cell cell = getCellAt(theX, theY);

        // If a wall, return the wall, otherwise, null.
        if (cell instanceof Wall) myWalls.add((Wall) cell);

        // If cell is a room.
        else if (cell instanceof Passable) {

            // Return the room.
            return (Passable) cell;

        }

        // Otherwise, return null.
        return null;

    }

    /**
     * Fills the 2D array of Cells to contain strictly walls.
     */
    private void fillWalls() {

        // Loop through width
        for (int i = 0; i < myWidth; i++) {

            // Loop through height
            for (int j = 0; j < myHeight; j++) {

                // Set to wall at that coordinate
                myCells[i][j] = new Wall(i, j);

            }

        }

    }


    /**
     * Checks if the cell is passable.
     *
     * @param theX X coordinate of cell.
     * @param theY Y coordinate of cell.
     * @return If the cell is passable.
     */
    private boolean isPassable(final int theX, final int theY) {
        return getCellAt(theX, theY) instanceof Passable;
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
        if (theX > myWidth - 1 || theX < 0) return null;

        // Y out of bounds.
        if (theY > myHeight - 1 || theY < 0) return null;

        // Return cell at location
        return myCells[theX][theY];

    }

    @Override
    public Door getEntrance() {
        return myEntrance;
    }

    @Override
    public Door getExit() {
        return myExit;
    }

    @Override
    public int getRoomCount() {
        return myRoomCounter;
    }

    @Override
    public int getWidth() {
        return myWidth;
    }

    @Override
    public int getHeight() {
        return myHeight;
    }

    @Override
    public Hero getHero() {
        return myHero;
    }


    /**
     * Aids the generator in creating the dungeon.
     */
    private static class PassableInfo {

        /**
         * Current passable.
         */
        private final Passable myPassable;

        /**
         * Blacklisted passables to not revisit.
         */
        private final Set<Passable> myBlacklist;

        /**
         * Creates an instance of PassableInfo.
         *
         * @param thePassable Passable in question.
         */
        public PassableInfo(final Passable thePassable) {

            myPassable = thePassable;

            // Empty hashset.
            myBlacklist = new HashSet<>();

        }

        /**
         * Adds an item to this passable's blacklist.
         *
         * @param thePassable Passable to add.
         */
        public void addBlacklist(final Passable thePassable) {
            myBlacklist.add(thePassable);
        }

    }

}
