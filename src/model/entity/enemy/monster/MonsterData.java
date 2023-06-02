package model.entity.enemy.monster;

/**
 * Monster data is used to store data retrieved from the
 * SQLite database. Created to resolve the bug created by
 * creating Monsters as their data is read in and then storing
 * them in an ArrayList<Monster>. By using this, Monsters can
 * be truly unique to a room.
 *
 * @version 1.0.0
 * @author Patrick Hern
 */
public class MonsterData {
    /** The Monster's name. */
    private final String myName;
    /** The Monster's HP. */
    private final int myHP;
    /** The Monster's minimum damage. */
    private final int myMinDamage;
    /** The Monster's maximum damage. */
    private final int myMaxDamage;
    /** The Monster's attack speed. */
    private final int myAttackSpeed;
    /** The Monster's hit chance. */
    private final double myHitChance;
    /** The Monster's minimum heal amount. */
    private final int myMinHeal;
    /** The Monster's maximum heal amount. */
    private final int myMaxHeal;
    /** The Monster's heal chance. */
    private final double myHealChance;

    /**
     * Public constructor that initializes a standardized
     * Monster for generic storage of data.
     *
     * @param theName Name.
     * @param theHP HP at full health.
     * @param theMinDamage Minimum damage.
     * @param theMaxDamage Maximum damage.
     * @param theAttackSpeed Attack speed.
     * @param theHitChance Hit chance.
     * @param theMinHeal Minimum heal.
     * @param theMaxHeal Maximum heal.
     * @param theHealChance Heal chance.
     */
    public MonsterData(

            final String  theName,
            final int     theHP,
            final int     theMinDamage,
            final int     theMaxDamage,
            final int     theAttackSpeed,
            final double  theHitChance,
            final int     theMinHeal,
            final int     theMaxHeal,
            final double  theHealChance

    ) {

        myName = theName;
        myHP = theHP;
        myMinDamage = theMinDamage;
        myMaxDamage = theMaxDamage;
        myAttackSpeed = theAttackSpeed;
        myHitChance = theHitChance;
        myMinHeal = theMinHeal;
        myMaxHeal = theMaxHeal;
        myHealChance = theHealChance;

    }

    /**
     * Get Monster name.
     * @return Name.
     */
    public String getName() {
        return myName;
    }

    /**
     * Get Monster max HP.
     * @return Max HP..
     */
    public int getHP() {
        return myHP;
    }

    /**
     * Get Monster minimum damage.
     * @return Minimum damage.
     */
    public int getMinDamage() {
        return myMinDamage;
    }

    /**
     * Get Monster maximum damage.
     * @return Maximum damage.
     */
    public int getMaxDamage() {
        return myMaxDamage;
    }

    /**
     * Get attack speed.
     * @return Attack speed.
     */
    public int getAttackSpeed() {
        return myAttackSpeed;
    }

    /**
     * Get hit chance
     * @return Hit Chance.
     */
    public double getHitChance() {
        return myHitChance;
    }

    /**
     * Get minimum heal amount.
     * @return Minimum heal amount.
     */
    public int getMinHeal() {
        return myMinHeal;
    }

    /**
     * Get maximum heal amount.
     * @return Maximum heal amount.
     */
    public int getMaxHeal() {
        return myMaxHeal;
    }

    /**
     * Get heal chance.
     * @return Heal chance.
     */
    public double getHealChance() {
        return myHealChance;
    }
}
