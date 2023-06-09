package model.dungeon.cell.passable;

import model.dungeon.cell.passable.info.Neighbors;
import model.entity.hero.Hero;
import view.console.pattern.Color;

/**
 * A type of passable that poses as an exit or entrance.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class Door implements Passable {

    /** X coordinate of this cell. */
    private final int myX;

    /** Y coordinate of this cell. */
    private final int myY;

    /** Is this door an entrance or exit. */
    private final boolean myIsEntrance;

    /** Surrounding neighbors of the passable. */
    private final Neighbors myNeighbors;

    /** Has the character entered through the door. */
    private boolean hasEntered;


    public Door(final int theX, final int theY, final boolean theIsEntrance) {

        myX = theX;
        myY = theY;
        myIsEntrance = theIsEntrance;

        // Bind a neighbor object.
        myNeighbors = new Neighbors(this);

        // Default to the door not being entered yet.
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

    /**
     * Shows a string representation of the door with color.
     *
     * @return String representation of the door.
     */
    @Override
    public String toString() {
        return Color.YELLOW + (myIsEntrance ? "<_^_>\n" :
                ">_^_<\n") + Color.YELLOW + "[]|[]";
    }

    @Override
    public Neighbors getNeighbors() {
        return myNeighbors;
    }

    /**
     * Gets if the character has passed through the door.
     *
     * @return Has the character passed through.
     */
    public boolean hasEntered() {
        return hasEntered;
    }


    /**
     * Used to capture when the hero has walked through.
     * If all pillars are obtained, the hero has passed through.
     *
     * @param theHero Hero in question.
     */
    @Override
    public void interactWith(final Hero theHero) {

        // Checks if the 4th pillar is non-null.
        if (theHero.getInventory().getSlots()[3] == null) return;

        // Sets entered to true.
        hasEntered = true;

    }

}
