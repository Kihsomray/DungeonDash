package model.sprite.enemy.monster;

/**
 * A type of Monster that is randomly spawned.
 *
 * Specializes in health, weaker in attack.
 *
 * @version 1.0.0
 * @author Kihsomray
 * @author Patrick Hern
 */
public class MockOgre extends Monster {

    /**
     * Create an instance of Ogre.
     */
    public MockOgre() {

        super(
                "Ogre",
                200,
                30,
                60,
                2,
                0.6,
                30,
                60,
                0.1
        );

    }

}
