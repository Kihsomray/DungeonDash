package controller;

import model.dungeon.Dungeon;

public class DungeonAdventure {

    public static void main(String[] args) {
        Dungeon dungeonAdventure = new Dungeon(8);
        System.out.print(dungeonAdventure);
        //welcomeMessage();


        boolean gameOver = false;
        while (!gameOver) {

            break;
        }

//        System.out.println("Testing Dungeon Generation");
//        System.out.println(dungeonAdventure);
//        System.out.println("Hero location: " + d.getHeroRow() + " " + d.getHeroCol());
//        System.out.println();
//
//        System.out.println("Testing Potion Generation");
//        System.out.println(dungeonAdventure.potionSpawns());
//
//        System.out.println("Testing Trap Generation");
//        System.out.println(dungeonAdventure.trapSpawns());
//
//        System.out.println("Testing Enemy Generation");
//        System.out.println(dungeonAdventure.enemySpawns());
    }

    private static void welcomeMessage() {
        System.out.println( """
                "Why, hello there Stranger!" You stop and turn to see a
                strange man in dark clothing approaching you out of nowhere nowhere.
                
                "You look like you could use some adventure in your life!" he says,
                a wild look in his eye.
                
                "I have an exclusive deal just for you! You see, there's this
                crawlspace, some may venture to call it a "dungeon" but it's
                hardly that!" his voice giving away his intention.
                
                "Oh but I'd never go in there... trade secrets and honorable
                business and all that HAHA. It's quite the opportunity. You don't want
                to pass up the treasure that lies within."
                
                You mistakenly perk up at the promise of treasure. The stranger
                already has you right where he wants you.
                
                "Ah I knew you couldn't resist!
                Here's the key to the dungeon. Happy adventuring..."
                
                "Before you go, may I know who you are?"
                """);
    }
}
