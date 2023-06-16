package controller;

import model.util.Utility;
import model.dungeon.Dungeon;
import model.sprite.hero.Priestess;
import model.sprite.hero.Thief;
import view.DungeonGUI;
import view.console.ConsoleDisplay;

import java.io.Serializable;

/**
 * Main class of the Dungeon Adventure game.
 *
 * @author Kihsomray
 * @author Patrick Hern
 */
public final class DungeonAdventure implements Serializable {

    /** Instance of the GUI - view. */
    private final DungeonGUI myGUI;

    /** Instance of the Dungeon - model. */
    private Dungeon myDungeon;

    /**
     * Main method of the program. All execution begins here.
     *
     * @param args Command line arguments (non required).
     */
    public static void main(String[] args) {

        // Creates a new game.
        final DungeonAdventure game = new DungeonAdventure();

        // Initializes GUI. Will in turn call initializeDungeon when needed.
        game.initializeGUI();

    }

    /**
     * No one should be able to make this class.
     */
    private DungeonAdventure() {
        myGUI = new GUIDisplay(this);
    }

    /**
     * Initializes the GUI.
     * To be called from controller.
     */
    private void initializeGUI() {

        // Display the GUI.
        myGUI.display();

    }

    /**
     * Initializes the Dungeon based on character letter.
     * To be called from view.
     */
    public void initializeDungeon(
            final char theHeroChar,
            final String theUsername
    ) {

        // Initializes dungeon.
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

    public void setDungeon(final Dungeon theDungeon) {
        myDungeon = theDungeon;
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
