package model.inventory.item.potion;

import model.sprite.hero.Hero;

import java.io.File;

/**
 * A type of potion that allows a hero to see adjacent rooms.
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

    private static final String visionPotionArtPath = "res" + File.separator + "VisionPotion.png";
    private static final String visionSlotPath = "res" + File.separator + "InventoryRes" + File.separator + "VisionSlot.png";


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

    public String getArtPath() {
        return visionPotionArtPath;
    }
    public String getSlotArtPath() {return visionSlotPath;}
}
