package controller;

import model.dungeon.Dungeon;

public class mainTesting {

    public static void main(String[] args) {
        System.out.println("Testing Out Dungeon Generation");
        Dungeon d;

        for (int i = 0; i < 20; i++) {
            d = new Dungeon(10);
            System.out.println(d);
        }
    }
}
