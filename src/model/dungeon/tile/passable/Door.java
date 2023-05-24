package model.dungeon.tile.passable;

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
        return (myIsEntrance ? "<___>" : ">___<") + "\n[]|[]";
    }

    @Override
    public Neighbors getNeighbors() {
        return myNeighbors;
    }

}
