package test;

import model.entity.DungeonCharacterFactory;
import model.entity.enemy.monster.Monster;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DungeonCharacterFactoryTest {

    @Test
    void generateMonster() {
        Monster m = DungeonCharacterFactory.generateMonster();
        assertEquals('M', m.getDisplayChar());
    }
}