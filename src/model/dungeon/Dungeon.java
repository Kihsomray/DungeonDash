package model.dungeon;

import java.util.Random;

public class Dungeon {
    /** The dungeon/maze made of Rooms. */
    private final Room[][] myMaze;

    /** Random object for generating indexes. */
    private static final Random RAND = new Random();

    /**
     * Constructor for the dungeon that creates a new dungeon
     * and fills it using private methods.
     * @param theSize The dimension of the dungeon.
     */
    public Dungeon(int theSize) {
        // Initialize the Dungeon
        myMaze = new Room[theSize][theSize];

        // Generate spaces in each room to signify an empty room.
        generateEmptyDungeon();

        // Generate an entrance and an exit for the Dungeon.
        generateEntranceAndExit();

        // Generate the 4 Pillars of OO
        generatePillars();
    }

    /**
     * Fills the initial dungeon with empty rooms.
     */
    private void generateEmptyDungeon() {
        for (int row = 0; row < myMaze.length; row++) {
            for (int col = 0; col < myMaze.length; col++) {
                myMaze[row][col] = new Room(' ');
            }
        }
    }

    /**
     * Calls a helper method to place an entrance (i) and exit (O).
     */
    private void generateEntranceAndExit() {
        addEdge('i');
        addEdge('O');
    }

    /**
     * Generates edge rooms for a given symbol.
     * @param theSymbol The symbol used to denote a specific room.
     */
    private void addEdge(final char theSymbol) {
        int borderNESW = RAND.nextInt(4);
        int roomTile = RAND.nextInt(myMaze.length);

        // Re-rolls room tile and border to make sure i's and O's can't
        // spawn on top of each other.
        while (myMaze[0][roomTile].getContents() != ' '
                || myMaze[roomTile][0].getContents() != ' '
                || myMaze[roomTile][myMaze.length - 1].getContents() != ' '
                || myMaze[myMaze.length - 1][roomTile].getContents() != ' ') {
            roomTile = RAND.nextInt(myMaze.length);
            borderNESW = RAND.nextInt(4);
        }
        // Door spawns on the top border.
        if (borderNESW == 0) {
            myMaze[0][roomTile] = new Room(theSymbol);
        }
        // Door spawns on the bottom border.
        else if (borderNESW == 1) {
            myMaze[myMaze.length - 1][roomTile] = new Room(theSymbol);
        }
        // Door spawns on the left border.
        else if (borderNESW == 2) {
            myMaze[roomTile][0] = new Room(theSymbol);
        }
        // Door spawns on the right border
        else {
            myMaze[roomTile][myMaze.length - 1] = new Room(theSymbol);
        }
    }

    /**
     * Generate pillars for Abstraction, Encapsulation,
     * Inheritance, and Polymorphism in random, non-occupied
     * spaces.
     */
    private void generatePillars() {
        final char[] pillars = {'A', 'E', 'I', 'P'};
        int count = 0;
        int row, col;

        while (count < 4) {
            row = RAND.nextInt(myMaze.length);
            col = RAND.nextInt(myMaze.length);

            if (myMaze[row][col].getContents() == ' ') {
                myMaze[row][col] = new Room(pillars[count]);
                count++;
            }
        }
    }

    /**
     * Prints out all the enemy spawns.
     * Used for debugging.
     * @return Returns of all enemy spawns.
     */
    public String enemySpawns() {
        StringBuilder sr = new StringBuilder();
        for (int row = 0; row < myMaze.length; row++) {
            for (int col = 0; col < myMaze.length; col++) {
                sr.append(" | ").append(myMaze[row][col].getEnemySpawns());
            }
            sr.append(" |\n");
        }
        return sr.toString();
    }

    /**
     * Prints out the entire dungeon with all trap spawns.
     * Used for debugging.
     * @return Returns of a String with all trap spawns.
     */
    public String trapSpawns() {
        StringBuilder sr = new StringBuilder();
        for (int row = 0; row < myMaze.length; row++) {
            for (int col = 0; col < myMaze.length; col++) {
                sr.append(" | ").append(myMaze[row][col].getTrapSpawns());
            }
            sr.append(" |\n");
        }
        return sr.toString();
    }

    /**
     * Prints out the entire dungeon with potion spawns.
     * Used for debugging.
     * @return Returns of a String with potion spawns.
     */
    public String potionSpawns() {
        StringBuilder sr = new StringBuilder();
        for (int row = 0; row < myMaze.length; row++) {
            for (int col = 0; col < myMaze.length; col++) {
                sr.append(" | ").append(myMaze[row][col].getPotionSpawns());
            }
            sr.append(" |\n");
        }
        return sr.toString();
    }

    /**
     * Prints out the entire dungeon with all hints.
     * @return Returns of a String with all hints.
     */
    @Override
    public String toString() {
        StringBuilder sr = new StringBuilder();
        for (int row = 0; row < myMaze.length; row++) {
            for (int col = 0; col < myMaze.length; col++) {
                sr.append(" | ").append(myMaze[row][col].getContents());
            }
            sr.append(" |\n");
        }
        return sr.toString();
    }
}
