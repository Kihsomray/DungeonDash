package controller;

import model.Utility;
import model.dungeon.Dungeon;
import model.sprite.hero.Priestess;
import model.sprite.hero.Thief;
import view.DungeonGUI;
import view.console.ConsoleDisplay;

public class DungeonAdventure {

    private final DungeonGUI myGUI;
    private Dungeon myDungeon;

    public static void main(String[] args) {
        final DungeonAdventure game = new DungeonAdventure();
        game.initializeGUI();
    }

    private DungeonAdventure() {
        myGUI = new ConsoleDisplay(this);
    }

    /**
     * To be called from controller.
     */
    private void initializeGUI() {
        myGUI.display();
    }

    /**
     * To be called from view.
     */
    public void initializeDungeon(
            final char theHeroChar,
            final String theUsername
    ) {

        myDungeon = new Dungeon(
                this,
                14,
                8,
                Utility.generateHeroFromChar(theHeroChar, theUsername)
        );

    }

    public Dungeon getDungeon() {
        return myDungeon;
    }

    public DungeonGUI getGUI() {
        return myGUI;
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
