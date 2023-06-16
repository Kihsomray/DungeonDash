package model.inventory.item.potion;

import model.entity.hero.Hero;

import java.io.File;

/**
 * A type of potion that allows a hero to see adjacent rooms.
 *
 * Only the surrounding room will be visible until the hero moves.
 *
 * @version 1.0.0
 * @author Kihsomray
 * @author Patribird
 */
public class VisionPotion extends Potion {

    /** Display character of vision potion */
    private static final char DISPLAY_CHAR = 'V';

    private static final String visionPotionArtPath = "res" + File.separator + "VisionPotion.png";
    private static final String visionSlotPath = "res" + File.separator + "InventoryRes" + File.separator + "VisionSlot.png";


    @Override
    public void applyPotion(final Hero theHero) {

        // Give the hero more visibility.
        theHero.enableExtraVisibility();

    }


    @Override
    public char getDisplayChar() {
        return DISPLAY_CHAR;
    }


    public String getArtPath() {
        return visionPotionArtPath;
    }
    public String getSlotArtPath() {return visionSlotPath;}
}
