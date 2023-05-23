package model.character.monster;

/**
 * A type of Monster that is randomly spawned.
 *
 * Specializes in health, weaker in attack.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class Ogre extends Monster {

    /**
     * Create an instance of Ogre.
     */
    public Ogre() {

        super(
                "Ogre",
                200,
                30,
                6,
                2,
                0.6,
                30,
                60,
                0.1
        );

    }

}
