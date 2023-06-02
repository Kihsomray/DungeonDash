package model.dungeon.cell.passable;

import model.Interactable;
import model.Utility;
import model.dungeon.cell.passable.info.Neighbors;
import model.entity.DungeonCharacterFactory;
import model.entity.enemy.Enemy;
import model.entity.enemy.Trap;
import model.inventory.RoomInventory;
import model.inventory.item.potion.HealthPotion;
import model.inventory.item.potion.VisionPotion;
import model.entity.hero.Hero;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Room implements Passable, Serializable {

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

    /**
     * Creates a room.
     *
     *
     */
    public Room(final int theX, final int theY) {

        myX = theX;
        myY = theY;
        myInventory = new RoomInventory();
        myEnemies = new HashSet<>();
        myNeighbors = new Neighbors(this);

        // Randomize the spawns.
        randomizeSpawns();
    }

    @Override
    public Neighbors getNeighbors() {
        return myNeighbors;
    }

    @Override
    public void interactWith(Hero theHero) {
        // TODO interaction
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

    public Set<Interactable> getEntities() {
        final Set<Interactable> entities = new HashSet<>(myInventory.getInventory());
        entities.addAll(myEnemies);
        return entities;
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
    public String toString() {

        final StringBuilder sb = new StringBuilder();
        final Set<Interactable> entities = getEntities();
        int i = 1;
        for (final Interactable interactable : entities) {

            sb.append(interactable.getColoredDisplay())
                    .append(i++ != 3 ? ' ' : '\n');

        }
        for (int a = i; a <= 6; a++) {
            sb.append(' ').append(a % 3 == 0 ? a == 6 ? "" : '\n' : ' ');
        }
        return sb.toString();
    }

}


