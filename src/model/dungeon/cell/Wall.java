package model.dungeon.cell;

import java.io.Serializable;

import static view.console.frame.ConsoleFrame.PATTERN;

public class Wall implements Cell, Serializable {

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
        return PATTERN.generateSegment(5, true) + "\n" +
                PATTERN.generateSegment(5, true) ;
    }

}
