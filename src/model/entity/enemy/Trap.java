package model.entity.enemy;

import model.Utility;

public class Trap implements Enemy {


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
        return Utility.getColor('2') + getDisplayChar();
    }

}
