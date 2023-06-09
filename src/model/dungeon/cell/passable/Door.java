package model.dungeon.cell.passable;

import model.dungeon.cell.passable.info.Neighbors;
import model.entity.hero.Hero;
import view.console.pattern.Color;

public class Door implements Passable {

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
        return Color.YELLOW + (myIsEntrance ? "<_^_>\n" :
                ">_^_<\n") + Color.YELLOW + "[]|[]";
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
