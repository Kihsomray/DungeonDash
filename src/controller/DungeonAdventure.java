package controller;

import model.dungeon.Dungeon;

public class DungeonAdventure {
    public static void main(String[] args) {
        Dungeon d = new Dungeon(4);

        System.out.println("Testing Dungeon Generation");
        System.out.println(d);

        System.out.println("Testing Potion Generation");
        System.out.println(d.potionSpawns());

        System.out.println("Testing Trap Generation");
        System.out.println(d.trapSpawns());

        System.out.println("Testing Enemy Generation");
        System.out.println(d.enemySpawns());
    }
}
