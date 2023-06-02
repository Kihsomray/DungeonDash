package model.entity.enemy.monster.mock;

import model.entity.enemy.monster.Monster;

/**
 * A type of Monster that is randomly spawned.
 *
 * Overall, good middle ground monster.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class MockSkeleton extends Monster {

    /**
     * Create an instance of Skeleton.
     */
    public MockSkeleton() {

        super(
                "Skeleton",
                100,
                30,
                50,
                3,
                0.8,
                30,
                50,
                0.3
        );

    }

}