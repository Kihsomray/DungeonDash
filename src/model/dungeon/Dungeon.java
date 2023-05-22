package model.dungeon;

import java.util.*;

public class Dungeon {
    /** The dungeon/maze made of Rooms. */
    private final Room[][] myMaze;

    /** Random object for generating indexes. */
    private static final Random RAND = new Random();

    /** Doors between Rooms, Each */
    private Map<String, String> myDoors = new HashMap<String, String>();
    /** String to represent the current room */
    private String myCurRoom = "";
    /** String to store the entrance and exit locations */
    private String myEntrance = "";

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
        final char[] pillars = {'A', 'E', 'I', 'P'};  // TODO Change to enums?
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

    private void generateDoors() {
        // The current room is represented by a string such as "0-0" is the top left room
        for (int row = 0; row < myMaze.length; row++) {
            for (int col = 0; col < myMaze.length; col++) {
                if (myMaze[row][col].equals("i")) {
                    myEntrance = Integer.toString(row) +"-" + Integer.toString(col);
                }
            }
        }

        // Do depth first search with a sprinkle of randomness to create the room layout.
        // After completing the search, go back and add the way back from rooms into the previous rooms.

        // PsuedoCode
        // step 0 create a map pointing strings to an array of strings representing rooms
        // 1 set a pointer to the room entrance to the maze
        // 2 add it to a stack, and to a list
        // 3 randomly decide on a direction to go, check if it exists and is not in the list
        // 4 add that room which was in that direction to the stack and to the list
        // 5 change the pointer to that room
        // 6 repeat 2 - 5 until there isn't a possible room
        // 7 pop off of the top of the stack and save it to a var called poppedOff
        // 8 add poppedOff to the map with the only thing in its array as the previous room as
        // well as add poppedOff to the previous rooms mapping in the map
        // 9 set the pointer to the previous room and go back to step 2
        // 10 Once the stack is empty there should be a map of doors pointing from a room to all possible
        // rooms from that room

        // When controlling the player, start the player in the entrance room and give the options as the rooms which
        // are adjacent to that room, which are found using the map of doors.

        // This ensures dead ends exist while the entire dungeon is still connected
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
