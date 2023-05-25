package model.sprite.hero;

import model.Utility;
import model.dungeon.tile.passable.Passable;
import model.dungeon.tile.passable.Room;
import model.sprite.DungeonCharacter;
import model.inventory.HeroInventory;
import model.sprite.enemy.monster.Monster;

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


    /** Block chance. */
    private final double myBlockChance;


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
        myBlockChance = theBlockChance;

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
    public Passable getCurrentRoom() {
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
                .append(Utility.getColor('2'))
                .append(Utility.centerAndSpace(getName(), 27, true))
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
                .append(Utility.createPointBar(20, 100, 18)) // TODO implement
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
                .append(x > 9 ? "" : "0").append(x)
                .append(Utility.getColor('7'))
                .append("  |  ")
                .append(Utility.getColor('8'))
                .append("Y:")
                .append(Utility.getColor('6'))
                .append(y > 9 ? "" : "0").append(y)
                .append(Utility.getColor('7'))
                .append("  |    ")
                .append(Utility.generateSegment(3))
                .append('\n');

        // End bar.
        appendBar(sb);

        return sb.toString();
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
        myCurrentPassable = thePassable;
    }


}
