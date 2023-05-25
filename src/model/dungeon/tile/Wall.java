package model.dungeon.tile;

import model.Utility;

public class Wall implements Cell {

    private final int myX;
    private final int myY;

    public Wall(final int theX, final int theY) {
        myX = theX;
        myY = theY;
    }

    @Override
    public int getX() {
        return myX;
    }

    @Override
    public int getY() {
        return myY;
    }

    @Override
    public String toString() {
        return Utility.generateSegment() + "\n" + Utility.generateSegment();
    }

}
