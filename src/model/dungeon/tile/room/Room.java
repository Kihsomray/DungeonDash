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

public class Room implements Cell {

    /** Constant for monster spawn rate. */
    private static final double MONSTER_SPAWN_RATE = 0.5;

    /** Constant for trap spawn rate. */
    private static final double TRAP_SPAWN_RATE = 0.15;

    /** Constant for potion spawn rate. */
    private static final double POTION_SPAWN_RATE = 0.10;

    private final int myX;

    private final int myY;

    private final RoomInventory myInventory;

    private final Set<Enemy> myEnemies;

    private final Neighbors myNeighbors;

    private final boolean myIsDoor;

    /**
     * Creates a room.
     *
     * @param theIsDoor Is this room a door?
     */
    public Room(final int theX, final int theY, final boolean theIsDoor) {

        myX = theX;
        myY = theY;
        myInventory = new RoomInventory();
        myEnemies = new HashSet<>();
        myNeighbors = new Neighbors(this);
        myIsDoor = theIsDoor;

        // Randomize the spawns.
        randomizeSpawns();
    }

    public Neighbors getNeighbors() {
        return myNeighbors;
    }

    @Override
    public int getX() {
        return myX;
    }

    @Override
    public int getY() {
        return myY;
    }

    public RoomInventory getInventory() {
        return myInventory;
    }

    public boolean isDoor() {
        return myIsDoor;
    }

    /**
     * Randomizes the spawns of the monsters, traps, and potions.
     * Makes sure entrances and exits cannot spawn these secondary
     * items as well.
     */
    private void randomizeSpawns() {

        // Should you not want to spawn enemies or items.
        if (myIsDoor) return;

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

    @Override
    public String toString() {
        return super.toString();
    }
}


