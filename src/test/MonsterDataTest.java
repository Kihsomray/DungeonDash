package test;

import model.entity.enemy.monster.MonsterData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Test class for MonsterData. */
class MonsterDataTest {

    /** MonsterData to test the class. */
    private MonsterData myMonsterData;

    /** Set up the MonsterData with sample information. */
    @BeforeEach
    void setUp() {
        myMonsterData = new MonsterData("name", 100, 20, 30,
                3, 0.5, 40, 50, 0.75);
    }

    /** Simple getter test if the name is correct. */
    @Test
    void getName() {
        assertEquals("name", myMonsterData.getName());
    }

    /** Simple getter test if the HP is correct. */
    @Test
    void getHP() {
        assertEquals(100, myMonsterData.getHP());
    }

    /** Simple getter test if the minDamage is correct. */
    @Test
    void getMinDamage() {
        assertEquals(20, myMonsterData.getMinDamage());
    }

    /** Simple getter test if the maxDamage is correct. */
    @Test
    void getMaxDamage() {
        assertEquals(30, myMonsterData.getMaxDamage());
    }

    /** Simple getter test if the attackSpeed is correct. */
    @Test
    void getAttackSpeed() {
        assertEquals(3, myMonsterData.getAttackSpeed());
    }

    /** Simple getter test if the hitChance is correct. */
    @Test
    void getHitChance() {
        assertEquals(0.5, myMonsterData.getHitChance());
    }

    /** Simple getter test if the minHeal is correct. */
    @Test
    void getMinHeal() {
        assertEquals(40, myMonsterData.getMinHeal());
    }

    /** Simple getter test if the maxHeal is correct. */
    @Test
    void getMaxHeal() {
        assertEquals(50, myMonsterData.getMaxHeal());
    }

    /** Simple getter test if the healChance is correct. */
    @Test
    void getHealChance() {
        assertEquals(0.75, myMonsterData.getHealChance());
    }
}