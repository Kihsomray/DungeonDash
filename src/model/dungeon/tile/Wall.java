package model.dungeon.tile;

import model.Utility;

import java.io.File;

public class Wall implements Cell {

    private final int myX;
    private final int myY;

    private static final String wallArtPath = "res" + File.separator + "DarkTile.png";

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

    @Override
    public String getArtPath() {
        return wallArtPath;
    }

}
