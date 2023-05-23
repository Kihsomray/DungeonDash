package model.inventory.potion;

import model.character.hero.Hero;

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

    /**
     * Creates a vision potion object binded to a hero.
     *
     * @param theHero Hero the potion is used on.
     */
    public VisionPotion(final Hero theHero) {
        super(theHero);
    }

}
