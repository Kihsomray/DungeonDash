package model.entity.enemy.monster.mock;

import model.entity.enemy.monster.Monster;

/**
 * A type of Monster that is randomly spawned.
 *
 * Specializes in attack, weaker in health.
 *
 * @version 1.0.0
 * @author Patrick Hern
 * @author Kihsomray
 */
public class MockGremlin extends Monster {

    /**
     * Create an instance of Gremlin.
     */
    public MockGremlin() {

        super(
                "Gremlin",
                70,
                15,
                30,
                5,
                0.8,
                20,
                40,
                0.4
        );

    }

}
