package model.dungeon;

import controller.DungeonAdventure;
import model.util.Utility;
import model.dungeon.generator.PrimsGenerator;
import model.dungeon.generator.DungeonGenerator;
import model.dungeon.cell.Cell;
import model.dungeon.cell.passable.Passable;
import model.entity.hero.Hero;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(generateBorder());

        for (int i = myMaze[0].length - 1; i >= 0; i--) {

            Queue<String> lowerHalf = new LinkedList<>();
            sb.append(Utility.generateSegment()).append(' ');

            for (Cell[] cells : myMaze) {

                Cell cell = cells[i];

                if (myHero.getCurrentPassable() == cell) {

                    sb.append(Utility.getColor('8')).append(" _*_  ");
                    lowerHalf.add(Utility.getColor('8') + " /^\\ ");

                } else if ((myHero.isExtraVisibility() &&
                        (Math.abs(cell.getX() - myHero.getCurrentPassable().getX()) < 3 &&
                                Math.abs(cell.getY() - myHero.getCurrentPassable().getY()) < 3))
                ) {

                    String[] split = cell.toString().split("\n");

                    sb.append(split[0]).append(' ');
                    lowerHalf.add(split[1]);

                } else if (cell instanceof Passable &&
                        !myHero.hasDiscovered((Passable) cell)
                ) {

                    sb.append(Utility.getColor('1')).append("***** ");
                    lowerHalf.add(Utility.getColor('1') + "*****");

                } else {

                    String[] split = cell.toString().split("\n");

                    sb.append(split[0]).append(' ');
                    lowerHalf.add(split[1]);

                }

            }

            sb.append(Utility.generateSegment()).append("\n").append(Utility.generateSegment()).append(' ');

            while (!lowerHalf.isEmpty()) {
                sb.append(lowerHalf.poll()).append(' ');
            }

            sb.append(Utility.generateSegment()).append('\n');

        }

        final String[] dungeonString = sb.append(generateBorder()).append('\n').toString().split("\n");
        final String[] heroString = myHero.toString().split("\n");

        sb = new StringBuilder();

        for (int i = 0; i < dungeonString.length; i++) {
            sb.append(heroString[i])
                    .append("     ")
                    .append(dungeonString[i])
                    .append('\n');
        }

        return sb.toString();

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
