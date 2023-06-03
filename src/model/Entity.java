package model;

public interface Entity {

    /**
     * Gets the display char of the entity.
     *
     * @return Display char of the entity.
     */
    char getDisplayChar();

    /**
     * Gets the display char with a color applied.
     *
     * @return Display char with color.
     */
    String getColoredDisplay();

    String getArtPath();

}
