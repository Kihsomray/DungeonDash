package model.dungeon.tile;

public class Wall extends Cell {

    private final int myX;
    private final int myY;

    public Wall(final int theX, final int theY) {
        myX = theX;
        myY = theY;
    }

    public int getX() {
        return myX;
    }

    public int getY() {
        return myY;
    }

}
