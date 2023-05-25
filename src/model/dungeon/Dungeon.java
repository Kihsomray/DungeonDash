package model.dungeon;

import model.Utility;
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

        sb.append(generateBorder());

        for (int i = myMaze.length - 1; i >= 0; i--) {
            Cell[] cells = myMaze[i];

            Queue<String> lowerHalf = new LinkedList<>();
            sb.append(Utility.generateSegment()).append(' ');

            for (int j = 0; j < myMaze[0].length; j++) {

                String[] split = cells[j].toString().split("\n");

                sb.append(split[0]).append(' ');
                lowerHalf.add(split[1]);

            }

            sb.append(Utility.generateSegment()).append("\n").append(Utility.generateSegment()).append(' ');

            while (!lowerHalf.isEmpty()) {
                sb.append(lowerHalf.poll()).append(' ');
            }

            sb.append(Utility.generateSegment()).append('\n');

        }

        return sb.append(generateBorder()).append('\n').toString();

    }

    private String generateBorder() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < myMaze.length + 1; j++) {
                sb.append(Utility.generateSegment()).append(' ');
            }
            sb.append(Utility.generateSegment()).append("\n");
        }
        return sb.toString();
    }

}
