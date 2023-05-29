package model.inventory.item.potion;

import model.entity.hero.Hero;

/**
 * A type of potion that allows a hero to see adjacent rooms.
 *
 * Only the 4 adjacent room will be visible until the hero moves.
 *
 * @version 1.0.0
 * @author Kihsomray
 * @author Patribird
 */
public class VisionPotion extends Potion {

    //        FIELDS        //

    /** Display character of vision potion */
    private static final char DISPLAY_CHAR = 'V';


    //        CONSTRUCTORS        //

    /**
     * Creates a vision potion object.
     */
    public VisionPotion() {

    }


    //        ACCESSORS        //

    @Override
    public char getDisplayChar() {
        return DISPLAY_CHAR;
    }


    @Override
    public String usePotion(final Hero theHero) {

        theHero.enableExtraVisibility();
        return "Visibility increased!";

    }
}
