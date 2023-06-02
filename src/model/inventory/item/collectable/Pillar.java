package model.inventory.item.collectable;

import model.Utility;

import java.io.Serializable;

/**
 * A type of collectable that the user need to discover.
 *
 * Can be one of four pillars: A, E, I, P.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class Pillar implements Collectable, Serializable {

    //        FIELDS        //

    /** Principle of this pillar */
    private final Principle myPrinciple;


    //        CONSTRUCTORS        //

    /**
     * Create a Pillar object.
     *
     * @param thePrinciple Principle this pillar observes.
     */
    public Pillar(final Principle thePrinciple) {
        myPrinciple = thePrinciple;
    }


    //        ACCESSORS        //

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
        return Utility.getColor('5') + getDisplayChar();
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

}
