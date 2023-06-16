package model.entity.enemy;

import model.entity.hero.Hero;
import model.util.Utility;
import view.console.pattern.Color;

import java.io.File;
import java.io.Serializable;

/**
 * A type of enemy that inflicts a percentage of damage on the hero.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class Trap implements Enemy, Serializable {

    /** Minimum HP loss percentage. */
    private static final double MIN_HP_LOSS_PERCENTAGE = 0.08;

    /** Maximum HP loss percentage. */
    private static final double MAX_HP_LOSS_PERCENTAGE = 0.16;

    private static final String trapArtPath = "res" + File.separator + "Hole.png";


    /**
     * Does the trap's effect on the hero.
     *
     * @param theHero Hero to damage.
     */
    public void damageHero(final Hero theHero) {

        // Inflict damage on the hero.
        theHero.receiveDamage((int) (Utility.RANDOM.nextDouble(
                MIN_HP_LOSS_PERCENTAGE, MAX_HP_LOSS_PERCENTAGE) *
                theHero.getHP()));

    }


    @Override
    public String getArtPath() {
        return trapArtPath;
    }

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

}
