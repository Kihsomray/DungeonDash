package model.sprite;

import model.Utility;
import model.sprite.enemy.monster.Gremlin;
import model.sprite.enemy.monster.Monster;
import model.sprite.enemy.monster.Ogre;
import model.sprite.enemy.monster.Skeleton;

public class DungeonCharacterFactory {


    /**
     * Generates a random monster: Ogre, Gremlin, Skeleton.
     *
     * Roughly has a 1 in 3 chance of generating.
     *
     * @return Randomly generated monster.
     */
    public static Monster generateMonster() {

        final double random = Utility.RANDOM.nextDouble();
        if (random < 0.333333) return new Ogre();
        else if (random < 0.666667) return new Gremlin();
        else return new Skeleton();

    }



}
