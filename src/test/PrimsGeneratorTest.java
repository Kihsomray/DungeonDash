package test;

import model.dungeon.cell.passable.Door;
import model.dungeon.generator.PrimsGenerator;
import model.entity.hero.Hero;
import model.entity.hero.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimsGeneratorTest {

    PrimsGenerator myGen;

    @BeforeEach
    void setUp() {
        Hero dummyHero = new Warrior("dummyHero");
        myGen = new PrimsGenerator(10, 10, dummyHero);
    }

    @Test
    void generate() {

    }

    @Test
    void getEntrance() {
    }

    @Test
    void getExit() {
    }

    @Test
    void getRoomCount() {
    }

    @Test
    void getWidth() {
    }

    @Test
    void getHeight() {
    }

    @Test
    void getHero() {
    }
}