package test;

import model.entity.battle.Battle;
import model.entity.enemy.monster.Monster;
import model.entity.enemy.monster.mock.MockSkeleton;
import model.entity.hero.Hero;
import model.entity.hero.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Class to test Battle. */
class BattleTest {

    /** Hero to test Battle. */
    Hero myHero;
    /** Monster to test Battle. */
    Monster myMonster;
    /** Battle to test the Battle class. */
    Battle myBattle;

    /** Set up the hero, monster and battle to test with. */
    @BeforeEach
    void setUp() {
        myHero = new Warrior("test");
        myMonster = new MockSkeleton();
        myBattle = new Battle(myHero, myMonster);
    }

    /** Test if hero attacking the monster results with what is expected. */
    @Test
    void heroAttackMonster() {
        Battle.Result result = myBattle.heroAttackMonster(Battle.Option.ATTACK);
        assertEquals(Battle.Result.NORMAL_HERO, result);
    }

    /** Test if monster attacking the hero results with what is expected. */
    @Test
    void monsterAttackHero() {
        Battle.Result result = myBattle.monsterAttackHero();
        assertEquals(Battle.Result.NORMAL_MONSTER, result);
    }

    /** Test if you can get the monster after stuff in the battle happens. */
    @Test
    void getMonster() {
        assertEquals(myMonster, myBattle.getMonster());
    }

    /** Test if hero has the ability, which is true since it was never used in testing. */
    @Test
    void hasAbility() {
        assertTrue(myBattle.hasAbility());
    }
}