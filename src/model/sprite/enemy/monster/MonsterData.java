package model.sprite.enemy.monster;

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
    private final String myName;
    private final int myHP;
    private final int myMinDamage;
    private final int myMaxDamage;
    private final int myAttackSpeed;
    private final double myHitChance;
    private final int myMinHeal;
    private final int myMaxHeal;
    private final double myHealChance;


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

    public String getName() {
        return myName;
    }

    public int getHP() {
        return myHP;
    }

    public int getMinDamage() {
        return myMinDamage;
    }

    public int getMaxDamage() {
        return myMaxDamage;
    }

    public int getAttackSpeed() {
        return myAttackSpeed;
    }

    public double getHitChance() {
        return myHitChance;
    }

    public int getMinHeal() {
        return myMinHeal;
    }

    public int getMaxHeal() {
        return myMaxHeal;
    }

    public double getHealChance() {
        return myHealChance;
    }
}
