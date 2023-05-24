package model.dungeon;

import model.Utility;
import model.dungeon.tile.Cell;
import model.dungeon.tile.Wall;
import model.dungeon.tile.room.Room;

import java.util.*;

public class DungeonGenerator {

    private final int height;
    private final int width;

    private Cell[][] cells;

    private final List<Wall> walls;

    public DungeonGenerator(int theHeight, int theWidth) {

        height = theHeight;
        width = theWidth;

        walls = new ArrayList<>();

    }

    public Cell[][] generate() {

        // Generate new cell 2D array.
        cells = new Cell[height][width];
        fillWalls();

        // Empty the walls.
        walls.clear();

        // Start at bottom right.
        cells[0][0] = new Room(true);

        // Add the surrounding.
        addSurrounding(0, 0);

        while (!walls.isEmpty()) {

            Wall wall = walls.get(Utility.RANDOM.nextInt(walls.size()));

            final int x = wall.getX();
            final int y = wall.getY();
            if (checkSurrounding(x, y)) {
                cells[x][y] = new Room(true);
                addSurrounding(x, y);
            }

            walls.remove(wall);

        }

        return cells;

    }

    private boolean checkSurrounding(final int theX, final int theY) {

        int found = 0;
        if (getCellAt(theX, theY + 1) instanceof Room) found++;
        //if (getCellAt(theX + 1, theY + 1) instanceof Room) found++;
        if (getCellAt(theX + 1, theY) instanceof Room) found++;
        //if (getCellAt(theX + 1, theY - 1) instanceof Room) found++;
        if (getCellAt(theX, theY - 1) instanceof Room) found++;
        //if (getCellAt(theX - 1, theY - 1) instanceof Room) found++;
        if (getCellAt(theX - 1, theY) instanceof Room) found++;
        //if (getCellAt(theX - 1, theY + 1) instanceof Room) found++;


        return found == 1;
    }

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

    private void addIfWall(final int theX, final int theY) {

        // Get the surrounding cells, making sure no IOBE.
        Cell cell = getCellAt(theX, theY);

        // If a wall, return the wall, otherwise, null.
        if (cell instanceof Wall) walls.add((Wall) cell);

    }

    private Cell getCellAt(final int theX, final int theY) {
        return cells[Math.max(Math.min(theX, height - 1), 0)]
                [Math.max(Math.min(theY, width - 1), 0)];
    }

    private void fillWalls() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Wall(i, j);
            }
        }
    }


}
