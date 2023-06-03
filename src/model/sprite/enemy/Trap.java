package model.sprite.enemy;

import model.Utility;

import java.io.File;

public class Trap implements Enemy {

    private static final String trapArtPath = "res" + File.separator + "Hole.png";

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

    @Override
    public String getArtPath() {
        return trapArtPath;
    }

}
