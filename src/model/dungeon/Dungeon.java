package model.dungeon;

import model.dungeon.generator.PrimsGenerator;
import model.dungeon.generator.DungeonGenerator;
import model.dungeon.tile.Cell;
import model.dungeon.tile.room.Room;
import model.entity.hero.Warrior;

public class Dungeon {

    /** The dungeon/maze made of Rooms. */
    private final Cell[][] myMaze;
    private final int myRoomCount;

    private int myHeroRow;
    private int myHeroCol;

    /**
     * Constructor for the dungeon that creates a new dungeon
     * and fills it using private methods.
     * @param theSize The dimension of the dungeon.
     */
    public Dungeon(int theSize) {

        // Initialize the Dungeon using Prim's generator.
        // TODO - create character selection, pass it in here.
        final DungeonGenerator generator = new PrimsGenerator(theSize, theSize, new Warrior());

        // Generate the maze.
        myMaze = generator.generate();

        // Gets the room count.
        myRoomCount = generator.getRoomCount();

    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        final String repeat = "##".repeat(Math.max(0, myMaze.length + 2)) + "\n";
        sb.append(repeat);

        for (Cell[] cells : myMaze) {
            sb.append("##");
            for (int j = 0; j < myMaze[0].length; j++) {
                sb.append(cells[j] instanceof Room ? "  " : "##");
            }
            sb.append("##\n");
        }

        sb.append(repeat);
        return sb.append('\n').toString();

    }

}
