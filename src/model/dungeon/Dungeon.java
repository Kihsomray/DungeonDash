package model.dungeon;

import model.dungeon.generator.PrimsGenerator;
import model.dungeon.generator.DungeonGenerator;
import model.dungeon.tile.Cell;
import model.sprite.hero.Warrior;

import java.util.LinkedList;
import java.util.Queue;

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

        final String halfTile = "#####";

        final String border = (halfTile + " ").repeat(Math.max(0, myMaze.length + 1)) + halfTile + "\n";
        sb.append(border).append(border);

        for (int i = myMaze.length - 1; i >= 0; i--) {
            Cell[] cells = myMaze[i];

            Queue<String> lowerHalf = new LinkedList<>();
            sb.append(halfTile).append(' ');

            for (int j = 0; j < myMaze[0].length; j++) {

                String[] split = cells[j].toString().split("\n");

                sb.append(split[0]).append(' ');
                lowerHalf.add(split[1]);

            }

            sb.append(halfTile).append("\n").append(halfTile).append(' ');

            while (!lowerHalf.isEmpty()) {
                sb.append(lowerHalf.poll()).append(' ');
            }

            sb.append(halfTile).append('\n');

        }

        sb.append(border).append(border);
        return sb.append('\n').toString();

    }

}
