package model.dungeon.cell.passable;

import model.Utility;
import model.entity.hero.Hero;

public class Door implements Passable {

    private final int myX;
    private final int myY;
    private final boolean myIsEntrance;

    private final Neighbors myNeighbors;

    public Door(final int theX, final int theY, final boolean theIsEntrance) {
        myX = theX;
        myY = theY;
        myIsEntrance = theIsEntrance;
        myNeighbors = new Neighbors(this);
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

    }

}
