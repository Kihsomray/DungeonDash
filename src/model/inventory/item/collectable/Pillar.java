package model.inventory.item.collectable;

import view.console.pattern.Color;

import java.io.Serializable;

import java.io.File;

/**
 * A type of collectable that the user need to discover.
 *
 * Can be one of four pillars: A, E, I, P.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class Pillar implements Collectable, Serializable {

    /** Principle of this pillar */
    private final Principle myPrinciple;

    private static final String pillarArtPath = "res" + File.separator + "Pillar.png";
    private static final String pillarSlotPath = "res" + File.separator + "InventoryRes" + File.separator + "PillarSlot.png";


    /**
     * Create a Pillar object.
     *
     * @param thePrinciple Principle this pillar observes.
     */
    public Pillar(final Principle thePrinciple) {
        myPrinciple = thePrinciple;
    }


    @Override
    public char getDisplayChar() {
        return myPrinciple.getDisplayChar();
    }

    /**
     * Gets the colored display of the hero.
     *
     * @return Colored display of the hero.
     */
    @Override
    public String getColoredDisplay() {
        return Color.PURPLE + getDisplayChar();
    }


    /**
     * Enum representing the specific pillar.
     */
    public enum Principle {

        ABSTRACTION('A'),
        ENCAPSULATION('E'),
        INHERITANCE('I'),
        POLYMORPHISM('P');

        /**
         * Display character of the pillar.
         */
        private final char myDisplayChar;

        /**
         * Creates a new principle enum.
         *
         * @param theDisplayChar Display char of the principle.
         */
        Principle(final char theDisplayChar) {
            myDisplayChar = theDisplayChar;
        }

        /**
         * Gets display char.
         *
         * @return Display char.
         */
        public char getDisplayChar() {
            return myDisplayChar;
        }

    }

    public String getArtPath() {
        return pillarArtPath;
    }
    public String getSlotArtPath() {return pillarSlotPath;}

}
