package model.inventory.potion;

import model.character.hero.Hero;

public abstract class Potion {

    protected final Hero myHero;

    /**
     * Creates a potion object binded to a hero.
     *
     * @param theHero Hero the potion is used on.
     */
    public Potion(final Hero theHero) {
        myHero = theHero;
    }

    /**
     * Use a potion on the hero.
     *
     * @return Message from using the potion.
     */
    public String usePotion() {
        return "Not implemented";
    }

}
