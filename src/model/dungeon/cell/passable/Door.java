package model.dungeon.cell.passable;

import model.util.Utility;
import model.dungeon.cell.passable.info.Neighbors;
import model.entity.hero.Hero;

import java.io.Serializable;

public class Door implements Passable, Serializable {

    private final int myX;
    private final int myY;
    private final boolean myIsEntrance;

    private final Neighbors myNeighbors;

    private boolean hasEntered;

    public Door(final int theX, final int theY, final boolean theIsEntrance) {
        myX = theX;
        myY = theY;
        myIsEntrance = theIsEntrance;
        myNeighbors = new Neighbors(this);

        hasEntered = false;
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
        return Utility.getColor('3')
                + (myIsEntrance ? "<_^_>\n" : ">_^_<\n")
                + Utility.getColor('3') + "[]|[]";
    }

    @Override
    public Neighbors getNeighbors() {
        return myNeighbors;
    }

    @Override
    public void interactWith(final Hero theHero) {
        if (theHero.getInventory().getSlots()[3] == null) return;
        hasEntered = true;
    }

    public boolean hasEntered() {
        return hasEntered;
    }

}
