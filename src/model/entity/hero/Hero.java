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

    //        FIELDS        //

    private static final String INVALID_MOVEMENT = "You can't move there!";

    /** Hero's inventory. */
    private final HeroInventory myInventory;

    /** Current room position */
    private Passable myCurrentPassable;

    private Set<Passable> myVisited;


    /** Block chance. */
    private final double myBlockChance;

    private boolean myExtraVisibility;

    private Battle myBattle;


    //        CONSTRUCTORS        //

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


    //        ACCESSORS        //

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
        return Utility.getColor('7') + getDisplayChar();
    }

    /**
     * Get the current room.
     *
     * @return Current room.
     */
    public Passable getCurrentPassable() {
        return myCurrentPassable;
    }


    //        MUTATORS        //

    /**
     * Attacks this hero.
     *
     * @param theDamage Damage to deal to the hero.
     */
    @Override
    public void receiveDamage(final int theDamage) {

        // If out of the range, no damage.
        if (Utility.RANDOM.nextDouble() >= myBlockChance) return;

        // Otherwise, call super.
        super.receiveDamage(theDamage);

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
     * Set the current passable.
     *
     * @param thePassable Passable.
     */
    public void setCurrentPassable(final Passable thePassable) {
        myVisited.add(myCurrentPassable = thePassable);
    }

    public MovementResult moveNorth() {
        return checkAndMove(myCurrentPassable.getNeighbors().getNorth());
    }

    public MovementResult moveEast() {
        return checkAndMove(myCurrentPassable.getNeighbors().getEast());
    }

    public MovementResult moveSouth() {
        return checkAndMove(myCurrentPassable.getNeighbors().getSouth());
    }

    public MovementResult moveWest() {
        return checkAndMove(myCurrentPassable.getNeighbors().getWest());
    }

    public boolean useInventoryItem(final int theLocation) {
        final Item item = myInventory.getItemAt(theLocation % 4 - 1, (theLocation + 4) / 4, true);

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
                result = MovementResult.TRAP;

            // If monster, start a battle.
            } else if (enemy instanceof final Monster monster) {

                // Start a new battle with a monster.
                myBattle = new Battle(this, monster);

            }

        }

        // Transfer the inventory.
        room.getInventory().addAllTo(myInventory);

        // Return the result.
        return result;

    }

    public boolean hasDiscovered(final Passable passable) {
        return myVisited.contains(passable);
    }

    public void addDiscovery(final Passable passable) {
        myVisited.add(passable);
    }

    public void enableExtraVisibility() {
        myExtraVisibility = true;
    }

    public boolean isExtraVisibility() {
        return myExtraVisibility;
    }

    public Battle getBattle() {
        return myBattle;
    }

    public enum MovementResult{

        NORMAL, INVALID, TRAP

    }

}
