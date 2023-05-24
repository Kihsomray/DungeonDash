package model.dungeon.tile.room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Neighboring rooms of the current room.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class Neighbors {

    /** Current room */
    private final Room myRoom;

    /** North room */
    private Room myNorth;

    /** East room */
    private Room myEast;

    /** South room */
    private Room mySouth;

    /** West room */
    private Room myWest;


    /**
     * Creates neighbor class
     */
    public Neighbors(final Room theRoom) {

        myRoom = theRoom;

        // Set all to null
        myNorth = myEast = mySouth = myWest = null;

    }

    public Room getRandomNeighbor(final Room theRoomIgnore) {
        List<Room> rooms = new ArrayList<>();
        if (myNorth != null) rooms.add(myNorth);
        if (myEast != null) rooms.add(myEast);
        if (mySouth != null) rooms.add(mySouth);
        if (myWest != null) rooms.add(myWest);
        Collections.shuffle(rooms);
        rooms.remove(theRoomIgnore);
        return rooms.size() > 0 ? rooms.get(0) : null;
    }

    /**
     * Get north room.
     *
     * @return North room.
     */
    public Room getNorth() {
        return myNorth;
    }

    /**
     * Get east room.
     *
     * @return East room.
     */
    public Room getEast() {
        return myEast;
    }

    /**
     * Get south room.
     *
     * @return South room.
     */
    public Room getSouth() {
        return mySouth;
    }

    /**
     * Get west room.
     *
     * @return West room.
     */
    public Room getWest() {
        return myWest;
    }

    /**
     * Set north room.
     *
     * @param theNorth Room.
     * @param theSetOther Should we set the adjacent room to point here?
     */
    public void setNorth(final Room theNorth, final boolean theSetOther) {
        this.myNorth = theNorth;
        if (theSetOther && theNorth != null)
            theNorth.getNeighbors().setSouth(myRoom, false);
    }

    /**
     * Set east room.
     *
     * @param theEast Room.
     * @param theSetOther Should we set the adjacent room to point here?
     */
    public void setEast(final Room theEast, final boolean theSetOther) {
        this.myEast = theEast;
        if (theSetOther && theEast != null)
            theEast.getNeighbors().setWest(myRoom, false);
    }

    /**
     * Set south room.
     *
     * @param theSouth Room.
     * @param theSetOther Should we set the adjacent room to point here?
     */
    public void setSouth(Room theSouth, final boolean theSetOther) {
        this.mySouth = theSouth;
        if (theSetOther && theSouth != null)
            theSouth.getNeighbors().setNorth(myRoom, false);
    }

    /**
     * Set west room.
     *
     * @param theWest Room.
     * @param theSetOther Should we set the adjacent room to point here?
     */
    public void setWest(Room theWest, final boolean theSetOther) {
        this.myWest = theWest;
        if (theSetOther && theWest != null)
            theWest.getNeighbors().setEast(myRoom, false);
    }

}
