package model.entity.enemy.monster;

/**
 * A type of Monster that is randomly spawned.
 *
 * Specializes in attack, weaker in health.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class Gremlin extends Monster {

    /**
     * Create an instance of Gremlin.
     */
    public Gremlin() {

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
