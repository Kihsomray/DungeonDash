package model.dungeon.cell.passable;

import model.Interactable;
import model.util.Utility;
import model.dungeon.cell.passable.info.Neighbors;
import model.entity.DungeonCharacterFactory;
import model.entity.enemy.Enemy;
import model.entity.enemy.Trap;
import model.inventory.RoomInventory;
import model.inventory.item.potion.HealthPotion;
import model.inventory.item.potion.VisionPotion;
import model.entity.hero.Hero;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * A type of passable that acts as a room containing items and enemies.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class Room implements Passable {

    /** Constant for monster spawn rate. */
    private static final double MONSTER_SPAWN_RATE = 0.5;

    /** Constant for trap spawn rate. */
    private static final double TRAP_SPAWN_RATE = 0.15;

    /** Constant for potion spawn rate. */
    private static final double POTION_SPAWN_RATE = 0.10;

    private static final String ART_PATH = "res" + File.separator + "GreyTile64.png";

    /** X coordinate of this cell. */
    private final int myX;

    /** Y coordinate of this cell. */
    private final int myY;

    /** Inventory of this room. */
    private final RoomInventory myInventory;

    /** Enemies contained in this room. */
    private final Set<Enemy> myEnemies;

    /** Surrounding neighbors of this passable. */
    private final Neighbors myNeighbors;


    /**
     * Creates an instance of a room.
     *
     * @param theX X coordinate of this cell.
     * @param theY Y coordinate of this cell.
     */
    public Room(final int theX, final int theY) {

        myX = theX;
        myY = theY;

        // Bind a new inventory.
        myInventory = new RoomInventory();

        // Empty set of enemies.
        myEnemies = new HashSet<>();

        //
        myNeighbors = new Neighbors(this);

        // Randomize the spawns.
        randomizeSpawns();
    }

    /**
     * Gets a string representation of the room.
     *
     * @return String representation of the room.
     */
    @Override
    public String toString() {

        // String builder.
        final StringBuilder sb = new StringBuilder();

        // Get all interactables in this room.
        final Set<Interactable> interactables = getInteractables();

        // Start counter at 1.
        int i = 1;

        // Loop through all interactables.
        for (final Interactable interactable : interactables) {

            // Append the char if needed, otherwise empty line.
            sb.append(interactable.getColoredDisplay())
                    .append(i++ != 3 ? ' ' : '\n');

        }

        // Fill the remainder with blank-space.
        for (int a = i; a <= 6; a++) {

            sb.append(' ').append(a % 3 == 0 ? a == 6 ? "" : '\n' : ' ');

        }

        // Return completed version of the string.
        return sb.toString();

    }

    /**
     * Used to capture when the hero has walked through.
     * Transfers all items from the room to the hero.
     *
     * @param theHero Hero in question.
     */
    @Override
    public void interactWith(final Hero theHero) {

        // Transfer the inventory.
        myInventory.addAllTo(theHero.getInventory());

    }

    /**
     * Randomizes the spawns of the monsters, traps, and potions.
     * Makes sure entrances and exits cannot spawn these secondary
     * items as well.
     */
    private void randomizeSpawns() {

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
    public String getArtPath() {
        return ART_PATH;
    }

    @Override
    public int getX() {
        return myX;
    }

    @Override
    public int getY() {
        return myY;
    }

    /**
     * Gets the room's inventory.
     *
     * @return Room's inventory.
     */
    public RoomInventory getInventory() {
        return myInventory;
    }

    /**
     * Gets all the entities in this room.
     *
     * @return Entities in this room.
     */
    public Set<Interactable> getInteractables() {

        // Creates a new set with the inventory.
        final Set<Interactable> interactables =
                new HashSet<>(myInventory.getInventory());

        // Adds all the enemies.
        interactables.addAll(myEnemies);

        // Returns the created set.
        return interactables;

    }

    /**
     * Gets all the enemies in this room.
     *
     * @return Enemies in this room.
     */
    public Set<Enemy> getEnemies() {
        return myEnemies;
    }

    @Override
    public Neighbors getNeighbors() {
        return myNeighbors;
    }

}
