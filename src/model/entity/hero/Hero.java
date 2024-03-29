package model.entity.hero;

import model.entity.battle.Battle;
import model.entity.enemy.Enemy;
import model.entity.enemy.Trap;
import model.util.Utility;
import model.dungeon.cell.passable.Passable;
import model.dungeon.cell.passable.Room;
import model.inventory.item.Item;
import model.inventory.item.potion.Potion;
import model.entity.DungeonCharacter;
import model.inventory.HeroInventory;
import model.entity.enemy.monster.Monster;
import view.console.pattern.Color;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Used as a base to represent a hero within the dungeon game.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public abstract class Hero extends DungeonCharacter implements Serializable {

    /** Hero's inventory. */
    private final HeroInventory myInventory;

    /** Current room position */
    private Passable myCurrentPassable;

    /** Set of passables this hero has visited. */
    private final Set<Passable> myVisited;

    /** Block chance. */
    private final double myBlockChance;

    /** Does the hero currently have extra visibility. */
    private boolean myExtraVisibility;

    /** Battle, if any, against a monster. */
    private Battle myBattle;


    /**
     * Create an instance of Hero.
     *
     * @param theName Name.
     * @param theHP Hit points.
     * @param theMinDamage Minimum damage.
     * @param theMaxDamage Maximum damage.
     * @param theAttackSpeed Attack speed.
     * @param theHitChance Hit chance.
     * @param theBlockChance Block chance.
     */
    public Hero(
            final String    theName,
            final int       theHP,
            final int       theMinDamage,
            final int       theMaxDamage,
            final int       theAttackSpeed,
            final double    theHitChance,
            final double    theBlockChance
    ) {

        super(
                theName,
                theHP,
                theMinDamage,
                theMaxDamage,
                theAttackSpeed,
                theHitChance
        );

        myInventory = new HeroInventory();
        myVisited = new HashSet<>();
        myBlockChance = theBlockChance;
        myExtraVisibility = true;
        myBattle = null;

    }


    /**
     * Attack a monster.
     *
     * @param theMonster Monster to attack.
     * @param theUseAbility Should the hero use its ability.
     */
    public abstract void attackMonster(
            final Monster theMonster,
            final boolean theUseAbility
    );

    /**
     * Attacks this hero.
     *
     * @param theDamage Damage to deal to the hero.
     */
    @Override
    public void receiveDamage(final int theDamage) {

        // If out of the range, no damage.
        if (Utility.RANDOM.nextDouble() < myBlockChance) return;

        // Otherwise, call super.
        super.receiveDamage(theDamage);

    }

    /**
     * Set the current passable.
     *
     * @param thePassable Passable.
     */
    public void setCurrentPassable(final Passable thePassable) {
        myVisited.add(myCurrentPassable = thePassable);
    }

    /**
     * Move the hero north.
     *
     * @return Result of the movement.
     */
    public MovementResult moveNorth() {
        return checkAndMove(myCurrentPassable.getNeighbors().getNorth());
    }

    /**
     * Move the hero east.
     *
     * @return Result of the movement.
     */
    public MovementResult moveEast() {
        return checkAndMove(myCurrentPassable.getNeighbors().getEast());
    }

    /**
     * Move the hero south.
     *
     * @return Result of the movement.
     */
    public MovementResult moveSouth() {
        return checkAndMove(myCurrentPassable.getNeighbors().getSouth());
    }

    /**
     * Move the hero west.
     *
     * @return Result of the movement.
     */
    public MovementResult moveWest() {
        return checkAndMove(myCurrentPassable.getNeighbors().getWest());
    }

    /**
     * Use an item based on inventory location (1-8).
     *
     * @param theLocation Location of item.
     * @return If the item was used.
     */
    public boolean useInventoryItem(final int theLocation) {

        // Gets item based on location.
        final Item item = myInventory.getItemAt(theLocation % 4 - 1, (theLocation + 4) / 4, true);

        // If item is a potion, use it.
        if (item instanceof Potion) {

            ((Potion) item).applyPotion(this);
            return true;

        }

        return false;

    }

    /**
     * Checks the passable if possible to move to.
     *
     * @return true if moved to the cell.
     */
    private MovementResult checkAndMove(final Passable thePassable) {

        // If it's a wall.
        if (thePassable == null) return MovementResult.INVALID;
        myCurrentPassable = thePassable;

        // Reset visibility.
        myExtraVisibility = false;

        // Add to the visited tiles.
        myVisited.add(myCurrentPassable);

        // Interact.
        thePassable.interactWith(this);

        // Checks if it is a room.
        if (!(myCurrentPassable instanceof final Room room))
            return MovementResult.NORMAL;

        // Keep track of result, defaulted to normal.
        MovementResult result = MovementResult.NORMAL;

        // Fight any enemies in this room.
        for (Enemy enemy : room.getEnemies()) {

            // If a trap, take the damage.
            if (enemy instanceof final Trap trap) {

                // Take damage and set result.
                trap.damageHero(this);
                if (result != MovementResult.MONSTER) result = MovementResult.TRAP;
                else result = MovementResult.TRAP_AND_MONSTER;

            // If monster, start a battle.
            } else if (enemy instanceof final Monster monster) {

                // Start a new battle with a monster.
                myBattle = new Battle(this, monster);
                if (result != MovementResult.TRAP) result = MovementResult.MONSTER;
                else result = MovementResult.TRAP_AND_MONSTER;

            }

        }

        // Clear enemies
        room.getEnemies().clear();

        // Return the result.
        return result;

    }


    /**
     * Gets the max HP of Warrior, Thief, and Priestess.
     *
     * @return Max HP.
     */
    public static int getAllHeroMaxHP() {
        return Math.max(Warrior.DEFAULT_HP,
                Math.max(Thief.DEFAULT_HP,
                        Priestess.DEFAULT_HP)
        );
    }

    /**
     * Gets the max damage of Warrior, Thief, and Priestess.
     *
     * @return Max damage.
     */
    public static int getAllHeroMaxDamage() {
        return Math.max(Warrior.DEFAULT_MAX_DAMAGE,
                Math.max(Thief.DEFAULT_MAX_DAMAGE,
                        Priestess.DEFAULT_MAX_DAMAGE)
        );
    }

    /**
     * Gets the max damage of Warrior, Thief, and Priestess.
     *
     * @return Max damage.
     */
    public static int getAllHeroMaxAttackSpeed() {
        return Math.max(Warrior.DEFAULT_ATTACK_SPEED,
                Math.max(Thief.DEFAULT_ATTACK_SPEED,
                        Priestess.DEFAULT_ATTACK_SPEED)
        );
    }

    /**
     * Get block chance.
     *
     * @return Block chance.
     */
    public final double getBlockChance() {
        return myBlockChance;
    }

    /**
     * Get the hero's inventory.
     *
     * @return Hero's inventory.
     */
    public final HeroInventory getInventory() {
        return myInventory;
    }

    /**
     * Gets the display char of the hero.
     *
     * @return Display char of the hero.
     */
    @Override
    public char getDisplayChar() {
        return 'H';
    }

    /**
     * Gets the colored display of the hero.
     *
     * @return Colored display of the hero.
     */
    @Override
    public String getColoredDisplay() {
        return Color.WHITE + getDisplayChar();
    }

    /**
     * Get the current room.
     *
     * @return Current room.
     */
    public Passable getCurrentPassable() {
        return myCurrentPassable;
    }

    public boolean hasDiscovered(final Passable passable) {
        return myVisited.contains(passable);
    }

    public void enableExtraVisibility() {
        myExtraVisibility = true;
    }

    public boolean isExtraVisibility() {
        return myExtraVisibility;
    }

    public Battle getBattle() {
        if (myBattle != null && myBattle.getMonster().getHP() == 0)
            myBattle = null;

        return myBattle;
    }

    public enum MovementResult{

        NORMAL, INVALID, TRAP, MONSTER, TRAP_AND_MONSTER

    }

}
