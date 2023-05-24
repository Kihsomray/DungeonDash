package model.dungeon.tile.room;

import model.Utility;
import model.dungeon.tile.Cell;
import model.entity.DungeonCharacterFactory;
import model.entity.enemy.Enemy;
import model.entity.enemy.Trap;
import model.inventory.RoomInventory;
import model.inventory.item.potion.HealthPotion;
import model.inventory.item.potion.VisionPotion;

import java.util.HashSet;
import java.util.Set;

public class Room extends Cell {

    /** Constant for monster spawn rate. */
    private static final double MONSTER_SPAWN_RATE = 0.5;

    /** Constant for trap spawn rate. */
    private static final double TRAP_SPAWN_RATE = 0.15;

    /** Constant for potion spawn rate. */
    private static final double POTION_SPAWN_RATE = 0.10;

    private final RoomInventory myInventory;

    private final Set<Enemy> myEnemies;

    private final Neighbors myNeighbors;

    private final boolean myShouldSpawn;

    /**
     * Creates a room.
     *
     * @param theShouldSpawn Should this room spawn items.
     */
    public Room(final boolean theShouldSpawn) {

        myInventory = new RoomInventory();
        myEnemies = new HashSet<>();
        myNeighbors = new Neighbors(this);
        myShouldSpawn = theShouldSpawn;

        // Randomize the spawns.
        randomizeSpawns();
    }

    public Neighbors getNeighbors() {
        return myNeighbors;
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

        // Should you not want to spawn enemies or items.
        if (!myShouldSpawn) return;

        // Given a chance, generate a monster.
        if (Utility.RANDOM.nextDouble() <= MONSTER_SPAWN_RATE)
            myEnemies.add(DungeonCharacterFactory.generateMonster());

        // Given a chance, generate a trap.
        if (Utility.RANDOM.nextDouble() <= TRAP_SPAWN_RATE)
            myEnemies.add(new Trap());

        // Given a chance, generate a potion.
        if (Utility.RANDOM.nextDouble() <= POTION_SPAWN_RATE)
            myInventory.addItem(new HealthPotion());

        // Given a chance, generate a potion.
        if (Utility.RANDOM.nextDouble() <= POTION_SPAWN_RATE)
            myInventory.addItem(new VisionPotion());

    }

}


