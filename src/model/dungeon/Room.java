package model.dungeon;

import java.util.Random;

public class Room {
    /** Random object used for room spawns. */
    private static final Random RAND = new Random();
    /** Constant for enemy spawn rate. */
    private static final double ENEMY_SPAWN_RATE = 0.5;
    /** Constant for trap spawn rate. */
    private static final double TRAP_SPAWN_RATE = 0.15;
    /** Constant for potion spawn rate. */
    private static final double POTION_SPAWN_RATE = 0.20;
    /** Primary contents of the room (I.E. Pillar or entrance/exit). */
    private final char myContents;
    /** The potion that spawned in this room. */
    private char myPotionSpawn;
    /** The enemy that spawned in this room. */
    private char myEnemySpawn;
    /** The trap that spawned in this room, */
    private char myTrapSpawn;

    /**
     * Package level constructor that only Dungeon can access.
     * @param theContents The main content to fill the Room.
     */
    Room(final char theContents) {
        myContents = theContents;
        myEnemySpawn = ' ';
        myPotionSpawn = ' ';
        myTrapSpawn = ' ';

        randomizeSpawns();
    }

    /**
     * Randomizes the spawns of the monsters, traps, and potions.
     * Makes sure entrances and exits cannot spawn these secondary
     * items as well.
     */
    private void randomizeSpawns() {
        // As long as the room isn't an entrance/exit, spawn stuff.
        // Doing it this way so that there can be multiple things
        // of interest per each room. So, a room could have an
        // enemy, trap, AND a potion. Along with primary content
        // like pillars.
        if (myContents != 'i' && myContents != 'O') {
            if (RAND.nextDouble() < ENEMY_SPAWN_RATE) {
                myEnemySpawn = 'm';
            }

            if (RAND.nextDouble() < TRAP_SPAWN_RATE) {
                myTrapSpawn = 'X';
            }

            if (RAND.nextDouble() < POTION_SPAWN_RATE) {
                myPotionSpawn = 'H';
            }
            else if (RAND.nextDouble() < POTION_SPAWN_RATE) {
                myPotionSpawn = 'V';
            }
        }
    }

    /**
     * Gets the primary contents of the room.
     * @return Returns a char of the contents.
     */
    public char getContents() {
        return myContents;
    }

    /**
     * Gets the potion spawned in the Room.
     * @return Returns the potion symbol ('H', 'V').
     */
    public char getPotionSpawns() {
        return myPotionSpawn;
    }

    /**
     * Gets the enemy spawn in the Room.
     * @return Returns the enemy symbol ('m').
     */
    public char getEnemySpawns() {
        return myEnemySpawn;
    }

    /**
     * Gets the trap spawn in the Room.
     * @return Returns the trap symbol ('X').
     */
    public char getTrapSpawns() {
        return myTrapSpawn;
    }

    /**
     * Setter used to set an enemy as "defeated" once
     * the player defeats the monster present.
     */
    public void setEnemyDefeated() {
        myEnemySpawn = ' ';
    }
}


