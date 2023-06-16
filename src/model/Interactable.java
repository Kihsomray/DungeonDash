package model;

/**
 * An interactable is something that can be interacted with.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public interface Interactable {

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
