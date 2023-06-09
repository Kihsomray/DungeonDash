package model.entity.enemy;

import model.entity.hero.Hero;
import model.util.Utility;
import view.console.pattern.Color;

import java.io.Serializable;

public class Trap implements Enemy, Serializable {

    private static final double MIN_HP_LOSS_PERCENTAGE = 0.08;

    private static final double MAX_HP_LOSS_PERCENTAGE = 0.16;


    @Override
    public char getDisplayChar() {
        return 'T';
    }

    /**
     * Gets the colored display of the hero.
     *
     * @return Colored display of the hero.
     */
    @Override
    public String getColoredDisplay() {
        return Color.GREEN + getDisplayChar();
    }

    /**
     * Does the trap's effect on the hero.
     *
     * @param theHero Hero to damage.
     */
    public void damageHero(final Hero theHero) {
        theHero.receiveDamage((int) (Utility.RANDOM.nextDouble(
                MIN_HP_LOSS_PERCENTAGE, MAX_HP_LOSS_PERCENTAGE) *
                theHero.getHP()));
    }

}
