package model.dungeon.cell.passable.info;

import model.dungeon.cell.passable.Passable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Neighboring rooms of the current room.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class Neighbors implements Serializable {

    /** Current room. */
    private final Passable myPassable;

    /** North room. */
    private Passable myNorth;

    /** East room. */
    private Passable myEast;

    /** South room. */
    private Passable mySouth;

    /** West room. */
    private Passable myWest;


    /**
     * Creates neighbor class.
     */
    public Neighbors(final Passable thePassable) {

        myPassable = thePassable;

        // Set all to null
        myNorth = myEast = mySouth = myWest = null;

    }


    /**
     * Gets a random non-null neighbor.
     *
     * @param thePassableIgnore Which passables should be ignored.
     * @return Random neighbor, otherwise null.
     */
    public Passable getRandomNeighbor(final Set<Passable> thePassableIgnore) {

        // Create a new list.
        final List<Passable> rooms = new ArrayList<>();

        // Add every direction if it is not null.
        if (myNorth != null) rooms.add(myNorth);
        if (myEast != null) rooms.add(myEast);
        if (mySouth != null) rooms.add(mySouth);
        if (myWest != null) rooms.add(myWest);

        // Shuffle the list of rooms.
        Collections.shuffle(rooms);

        // Remove each room that should be ignored.
        thePassableIgnore.forEach(rooms::remove);

        // If there is a room left, return it. Otherwise, null.
        return rooms.size() > 0 ? rooms.get(0) : null;

    }

    /**
     * Get north passable.
     *
     * @return North passable.
     */
    public Passable getNorth() {
        return myNorth;
    }

    /**
     * Get east passable.
     *
     * @return East passable.
     */
    public Passable getEast() {
        return myEast;
    }

    /**
     * Get south passable.
     *
     * @return South passable.
     */
    public Passable getSouth() {
        return mySouth;
    }

    /**
     * Get west passable.
     *
     * @return West passable.
     */
    public Passable getWest() {
        return myWest;
    }


    /**
     * Set north passable.
     *
     * @param theNorth Passable to set.
     * @param theSetOther Should we set the adjacent room to point here?
     */
    public void setNorth(final Passable theNorth, final boolean theSetOther) {

        this.myNorth = theNorth;
        if (theSetOther && theNorth != null)
            theNorth.getNeighbors().setSouth(myPassable, false);

    }

    /**
     * Set east passable.
     *
     * @param theEast Passable to set.
     * @param theSetOther Should we set the adjacent room to point here?
     */
    public void setEast(final Passable theEast, final boolean theSetOther) {

        this.myEast = theEast;
        if (theSetOther && theEast != null)
            theEast.getNeighbors().setWest(myPassable, false);

    }

    /**
     * Set south passable.
     *
     * @param theSouth Passable to set.
     * @param theSetOther Should we set the adjacent room to point here?
     */
    public void setSouth(final Passable theSouth, final boolean theSetOther) {

        this.mySouth = theSouth;
        if (theSetOther && theSouth != null)
            theSouth.getNeighbors().setNorth(myPassable, false);

    }

    /**
     * Set west passable.
     *
     * @param theWest Passable to set.
     * @param theSetOther Should we set the adjacent room to point here?
     */
    public void setWest(final Passable theWest, final boolean theSetOther) {

        this.myWest = theWest;
        if (theSetOther && theWest != null)
            theWest.getNeighbors().setEast(myPassable, false);

    }

}
