package model.sprite.hero;

import model.Utility;
import model.dungeon.tile.Cell;
import model.dungeon.tile.passable.Passable;
import model.dungeon.tile.passable.Room;
import model.inventory.item.Item;
import model.inventory.item.potion.Potion;
import model.sprite.DungeonCharacter;
import model.inventory.HeroInventory;
import model.sprite.enemy.monster.Monster;

import java.util.HashSet;
import java.util.Set;

/**
 * Used as a base to represent a hero within the dungeon game.
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public abstract class Hero extends DungeonCharacter {

    //        FIELDS        //

    /** Hero's inventory. */
    private final HeroInventory myInventory;

    /** Current room position */
    private Passable myCurrentPassable;

    private Set<Passable> myVisited;


    /** Block chance. */
    private final double myBlockChance;

    private boolean myExtraVisibility;


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

    }


    //        ACCESSORS        //

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

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();

        // Top bar.
        appendBar(sb);

        // Hero name.
        sb.append(randomChar())
                .append(Utility.getColor('8'))
                .append(Utility.centerAndSpace(
                        "HERO-" + this.getClass().getSimpleName(), 31, true))
                .append(randomChar())
                .append('\n');

        // Player name.
        sb.append(Utility.generateSegment(3))
                .append(' ')
                .append(Utility.getColor('2'))
                .append(Utility.centerAndSpace(getName(), 25, true))
                .append(' ')
                .append(Utility.generateSegment(3))
                .append('\n');

        // Middle bar.
        appendBar(sb);

        // Empty line.
        sb.append(randomChar())
                .append(" ".repeat(31))
                .append(randomChar())
                .append('\n');

        // HP info.
        sb.append(randomChar())
                .append("   ")
                .append(Utility.getColor('8'))
                .append("HP:    [")
                .append(getHealthBar())
                .append(Utility.getColor('8'))
                .append("] ")
                .append(randomChar())
                .append('\n');

        // Ability info.
        sb.append(randomChar())
                .append(" ")
                .append(Utility.getColor('8'))
                .append("ABILITY: [")
                .append(Utility.createPointBar(
                        Utility.RANDOM.nextInt(0, 100), 100, 18)
                ) // TODO implement
                .append(Utility.getColor('8'))
                .append("] ")
                .append(randomChar())
                .append('\n');

        // Empty line.
        sb.append(randomChar())
                .append(" ".repeat(7))
                .append("_".repeat(17))
                .append(" ".repeat(7))
                .append(randomChar())
                .append('\n');

        // Coordinates.
        final int x = myCurrentPassable.getX();
        final int y = myCurrentPassable.getY();

        // Display coordinates.
        sb.append(Utility.generateSegment(3))
                .append("    |  ")
                .append(Utility.getColor('8'))
                .append("X:")
                .append(Utility.getColor('6'))
                .append(x > 8 ? "" : "0").append(x + 1)
                .append(Utility.getColor('7'))
                .append("  |  ")
                .append(Utility.getColor('8'))
                .append("Y:")
                .append(Utility.getColor('6'))
                .append(y > 8 ? "" : "0").append(y + 1)
                .append(Utility.getColor('7'))
                .append("  |    ")
                .append(Utility.generateSegment(3))
                .append('\n');

        // End bar.
        appendBar(sb);

        return sb.append(" ".repeat(33))
                .append('\n')
                .append(myInventory.toString()).toString();
    }

    public String getHealthBar() {

        return Utility.createPointBar(getHP(), getMaxHP(), 18);

    }

    private void appendBar(final StringBuilder theStringBuilder) {
        theStringBuilder.append(randomChar())
                .append(' ').append(Utility.generateSegment(29))
                .append(' ').append(randomChar()).append('\n');
    }

    private String randomChar() {
        return Utility.generateSegment(1);
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

    public boolean moveNorth() {
        return checkAndMove(myCurrentPassable.getNeighbors().getNorth());
    }

    public boolean moveEast() {
        return checkAndMove(myCurrentPassable.getNeighbors().getEast());
    }

    public boolean moveSouth() {
        return checkAndMove(myCurrentPassable.getNeighbors().getSouth());
    }

    public boolean moveWest() {
        return checkAndMove(myCurrentPassable.getNeighbors().getWest());
    }

    public boolean useInventoryItem(final int theLocation) {
        final Item item = myInventory.getItemAt(theLocation % 4 - 1, (theLocation + 4) / 4, true);

        if (item instanceof Potion) {
            ((Potion) item).usePotion(this);
            return true;
        }
        return false;
    }

    /**
     * Checks the passable if possible to move to.
     *
     * @return true if moved to the cell.
     */
    private boolean checkAndMove(final Passable thePassable) {
        if (thePassable == null) return false;
        myCurrentPassable = thePassable;

        // Reset visibility.
        myExtraVisibility = false;

        myVisited.add(myCurrentPassable);

        if (!(myCurrentPassable instanceof final Room room)) return true;

        // TODO fight monsters and traps.

        // Transfer the inventory.
        room.getInventory().addAllTo(myInventory);

        return true;
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


}
